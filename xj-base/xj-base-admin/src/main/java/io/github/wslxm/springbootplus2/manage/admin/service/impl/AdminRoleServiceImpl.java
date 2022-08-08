package io.github.wslxm.springbootplus2.manage.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.wslxm.springbootplus2.common.auth.util.JwtUtil;
import io.github.wslxm.springbootplus2.common.cache.CacheKey;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseIServiceImpl;
import io.github.wslxm.springbootplus2.core.enums.Base;
import io.github.wslxm.springbootplus2.manage.admin.mapper.AdminRoleMapper;
import io.github.wslxm.springbootplus2.manage.admin.model.dto.AdminRoleDTO;
import io.github.wslxm.springbootplus2.manage.admin.model.dto.role.RoleAuthDTO;
import io.github.wslxm.springbootplus2.manage.admin.model.dto.role.RoleMenuDTO;
import io.github.wslxm.springbootplus2.manage.admin.model.dto.role.UserRoleDTO;
import io.github.wslxm.springbootplus2.manage.admin.model.entity.*;
import io.github.wslxm.springbootplus2.manage.admin.model.query.AdminRoleQuery;
import io.github.wslxm.springbootplus2.manage.admin.model.vo.AdminRoleVO;
import io.github.wslxm.springbootplus2.manage.admin.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wangsong
 */
@Service
public class AdminRoleServiceImpl extends BaseIServiceImpl<AdminRoleMapper, AdminRole> implements AdminRoleService {

    /**
     * 超级管理员角色 code（勿修改）
     */
    private final String ROLE_SYS = "SYS";

    @Autowired
    private AdminAuthorityService adminAuthorityService;
    @Autowired
    private AdminRoleService adminRoleService;
    @Autowired
    private AdminRoleUserService roleUserAdminService;
    @Autowired
    private AdminRoleMenuService adminRoleMenuService;
    @Autowired
    private AdminRoleAuthService adminRoleAuthService;


    @Override
    public IPage<AdminRoleVO> list(AdminRoleQuery query) {
        if (query.getIsUserIdChecked() == null) {
            query.setIsUserIdChecked(false);
        }
        if (query.getIsLoginUser() == null) {
            query.setIsLoginUser(false);
        }
        // 是否只查询当前登录人创建的角色
        String createUserId = query.getIsLoginUser() ? JwtUtil.getJwtUser(request).getUserId() : null;
        // 是否不屏蔽用户id查询数据,只用 IsChecked 标记，将queryUserId单独存放起来
        String queryUserId = query.getUserId();
        if (query.getIsUserIdChecked()) {
            query.setUserId(null);
        }

        // 查询
        IPage<AdminRoleVO> page = null;
        if (query.getCurrent() <= 0) {
            // list
            page = new Page<>();
            page = page.setRecords(baseMapper.list(null, query, createUserId));
        } else {
            // page
            page = new Page<>(query.getCurrent(), query.getSize());
            page = page.setRecords(baseMapper.list(page, query, createUserId));
        }

        // 根据用户标记 IsChecked
        if (query.getIsUserIdChecked() && StringUtils.isNotBlank(queryUserId)) {
            // 查询用户当前角色
            List<AdminRoleUser> roleUsers = roleUserAdminService.list(new LambdaQueryWrapper<AdminRoleUser>().eq(AdminRoleUser::getUserId, queryUserId));
            Map<String, AdminRoleUser> roleUserMap = roleUsers.stream().collect(Collectors.toMap(AdminRoleUser::getRoleId, p -> p));
            page.getRecords().forEach(p -> {
                if (roleUserMap.containsKey(p.getId())) {
                    p.setIsChecked(true);
                } else {
                    p.setIsChecked(false);
                }
            });
        }
        return page;
    }


    /**
     * 添加角色-默认有所有URL 权限
     *
     * @param dto
     * @return java.lang.Boolean
     * @author wangsong
     * @date 2020/9/19 0019 10:56
     * @version 1.0.1
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String insert(AdminRoleDTO dto) {
        AdminRole role = dto.convert(AdminRole.class);
        role.setCreateUser(JwtUtil.getJwtUser(request).getUserId());
        this.save(role);
        // 给角色分配菜单权限
        adminRoleMenuService.insert(role.getId(), dto.getMenuIds());

        // 默认有所有url权限
        List<AdminAuthority> authorityList = adminAuthorityService.list(new LambdaQueryWrapper<AdminAuthority>().select(AdminAuthority::getId));
        List<AdminRoleAuth> roleAuthList = new ArrayList<>();
        for (AdminAuthority authority : authorityList) {
            roleAuthList.add(new AdminRoleAuth(authority.getId(), role.getId()));
        }
        boolean b = adminRoleAuthService.saveBatch(roleAuthList);
        return role.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean upd(String id, AdminRoleDTO dto) {
        AdminRole role = dto.convert(AdminRole.class);
        role.setId(id);
        boolean b = this.updateById(role);

        // 编辑入口必传菜单，如果没传可能是在操作启用禁用等操作,不对菜单做处理
        if (dto.getMenuIds() != null && dto.getMenuIds().size() > 0) {
            // 给角色分配菜单权限(先删除后添加)
            adminRoleMenuService.remove(new LambdaQueryWrapper<AdminRoleMenu>().eq(AdminRoleMenu::getRoleId, role.getId()));
            adminRoleMenuService.insert(role.getId(), dto.getMenuIds());
        }
        return b;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updUserRole(String userId, List<String> roleIds) {
        //删除原角色所有权限数据
        boolean result = roleUserAdminService.remove(new QueryWrapper<AdminRoleUser>().eq("user_id", userId));
        if (roleIds == null || roleIds.size() <= 0) {
            return true;
        }
        List<AdminRoleUser> roleUserList = new ArrayList<>();
        for (int i = 0; i < roleIds.size(); i++) {
            roleUserList.add(new AdminRoleUser(roleIds.get(i), userId));
        }
        return roleUserAdminService.saveBatch(roleUserList, 1024);
    }


    /**
     * 分配用户的角色
     *
     * @return boolean
     * @author wangsong
     * @date 2020/8/9 0009 9:41
     * @version 1.0.1
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updUserRole(UserRoleDTO dto) {
        // 先删除原角色所有权限数据
        boolean result = roleUserAdminService.remove(new QueryWrapper<AdminRoleUser>().eq("user_id", dto.getUserId()));
        if (dto.getRoleIds() == null || dto.getRoleIds().size() <= 0) {
            return true;
        }
        List<AdminRoleUser> roleUserList = new ArrayList<>();
        for (int i = 0; i < dto.getRoleIds().size(); i++) {
            roleUserList.add(new AdminRoleUser(dto.getRoleIds().get(i), dto.getUserId()));
        }
        return roleUserAdminService.saveBatch(roleUserList, 1024);
    }


    /**
     * 分配角色菜单权限
     *
     * @return void
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/4/6 0006 17:47
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean roleMenuAuth(RoleMenuDTO dto) {
        // 删除当前角色所有菜单权限
        boolean result = adminRoleMenuService.remove(new LambdaQueryWrapper<AdminRoleMenu>().eq(AdminRoleMenu::getRoleId, dto.getRoleId()));
        if (dto.getMenuIds() == null || dto.getMenuIds().size() <= 0) {
            return true;
        }
        List<AdminRoleMenu> addRoleMenu = new LinkedList<>();
        for (int i = 0; i < dto.getMenuIds().size(); i++) {
            addRoleMenu.add(new AdminRoleMenu(dto.getRoleId(), dto.getMenuIds().get(i)));
        }
        return adminRoleMenuService.saveBatch(addRoleMenu, 1024);
    }


    /**
     * 所有角色拥有所有权限
     *
     * @return boolean
     * @author wangsong
     * @date 2020/10/9 0009 15:50
     * @version 1.0.1
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CacheKey.LOGIN_AUTH_USER_ID, allEntries = true)
    public boolean roleAuthAll() {
        List<AdminRole> roleList = adminRoleService.list();
        List<AdminAuthority> authList = adminAuthorityService.list(new LambdaQueryWrapper<AdminAuthority>()
                .select(AdminAuthority::getId)
                .eq(AdminAuthority::getType, Base.AuthorityType.V0.getValue())
        );
        //
        List<AdminRoleAuth> addRoleAuthList = new ArrayList<>();
        for (AdminRole role : roleList) {
            for (AdminAuthority auth : authList) {
                addRoleAuthList.add(new AdminRoleAuth(auth.getId(), role.getId()));
            }
        }
        //删除所有
        adminRoleAuthService.remove(null);
        //更新权限
        return adminRoleAuthService.saveBatch(addRoleAuthList, 1024);
    }

    /**
     * 分配角色url权限
     *
     * @return void
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/4/6 0006 17:47
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CacheKey.LOGIN_AUTH_USER_ID, allEntries = true)
    public boolean roleUrlAuth(RoleAuthDTO dto) {
        //删除原数据
        boolean result = adminRoleAuthService.remove(new LambdaQueryWrapper<AdminRoleAuth>().eq(AdminRoleAuth::getRoleId, dto.getRoleId()));
        if (dto.getAuthIds() == null || dto.getAuthIds().size() <= 0) {
            return true;
        }
        List<AdminRoleAuth> roleAuthList = new ArrayList<>();
        for (int i = 0; i < dto.getAuthIds().size(); i++) {
            roleAuthList.add(new AdminRoleAuth(dto.getAuthIds().get(i), dto.getRoleId()));
        }
        return adminRoleAuthService.saveBatch(roleAuthList, 1024);
    }


    @Override
    public AdminRole findSysRole() {
        AdminRole role = this.getOne(new LambdaQueryWrapper<AdminRole>()
                .eq(AdminRole::getCode, ROLE_SYS));
        return role;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean del(String roleId) {
        // 删除角色和角色相关的关系表
        roleUserAdminService.remove(new LambdaQueryWrapper<AdminRoleUser>().eq(AdminRoleUser::getRoleId, roleId));
        adminRoleMenuService.remove(new LambdaQueryWrapper<AdminRoleMenu>().eq(AdminRoleMenu::getRoleId, roleId));
        adminRoleAuthService.remove(new LambdaQueryWrapper<AdminRoleAuth>().eq(AdminRoleAuth::getRoleId, roleId));
        return this.removeById(roleId);
    }
}