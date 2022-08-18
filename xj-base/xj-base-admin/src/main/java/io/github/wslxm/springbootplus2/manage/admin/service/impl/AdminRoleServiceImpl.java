package io.github.wslxm.springbootplus2.manage.admin.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.wslxm.springbootplus2.common.auth.util.JwtUtil;
import io.github.wslxm.springbootplus2.common.cache.CacheKey;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseIServiceImpl;
import io.github.wslxm.springbootplus2.core.enums.Base;
import io.github.wslxm.springbootplus2.manage.admin.mapper.AdminRoleMapper;
import io.github.wslxm.springbootplus2.manage.admin.model.dto.AdminRoleDTO;
import io.github.wslxm.springbootplus2.manage.admin.model.dto.role.RoleAuthDTO;
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
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangsong
 */
@Service
public class AdminRoleServiceImpl extends BaseIServiceImpl<AdminRoleMapper, AdminRole> implements AdminRoleService {

    /**
     * 超级管理员角色 code（勿修改）
     */
    private final static String ROLE_SYS = "SYS";

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
    public IPage<AdminRoleVO> findPage(AdminRoleQuery query) {

        // 是否只查询当前登录人创建的角色
        String loginUserId = ObjectUtil.defaultIfNull(query.getIsLoginUser(), () -> JwtUtil.getJwtUser(request).getUserId(), null);
        Boolean isUserIdChecked = ObjectUtil.defaultIfNull(query.getIsUserIdChecked(), false);
        String userId = query.getUserId();

        // 是否查询所有数据,使用 IsChecked 标记，将queryUserId单独存放起来
        if (isUserIdChecked) {
            query.setUserId(null);
        }
        // 查询
        IPage<AdminRoleVO> page = new Page<>(query.getCurrent(), query.getSize());
        page = page.setRecords(baseMapper.list(page, query, loginUserId));
        if (page.getRecords() == null || page.getRecords().size() == 0) {
            return page;
        }

        // 处理指定用户当前拥有的角色
        if (isUserIdChecked && StringUtils.isNotBlank(userId)) {
            List<AdminRoleUser> roleUsers = roleUserAdminService.list(new LambdaQueryWrapper<AdminRoleUser>().eq(AdminRoleUser::getUserId, userId));
            if (roleUsers != null && roleUsers.size() > 0) {
                List<String> userRoleIds = roleUsers.stream().map(AdminRoleUser::getRoleId).collect(Collectors.toList());
                page.getRecords().forEach((p) -> p.setIsChecked(userRoleIds.contains(p.getId())));
            }
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
        adminRoleMenuService.updRoleMenus(role.getId(), dto.getMenuIds());

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
            adminRoleMenuService.updRoleMenus(role.getId(), dto.getMenuIds());
        }
        return b;
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
        return this.getOne(new LambdaQueryWrapper<AdminRole>().eq(AdminRole::getCode, ROLE_SYS));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean del(String roleId) {
        // 删除角色和角色相关的关系表
        roleUserAdminService.delByRoleId(roleId);
        adminRoleMenuService.delByRoleId(roleId);
        adminRoleAuthService.delByRoleId(roleId);
        return this.removeById(roleId);
    }
}