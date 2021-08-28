package com.ws.ldy.manage.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.core.base.service.impl.BaseIServiceImpl;
import com.ws.ldy.core.enums.Admin;
import com.ws.ldy.core.utils.BeanDtoVoUtil;
import com.ws.ldy.manage.admin.mapper.AdminRoleMapper;
import com.ws.ldy.manage.admin.model.dto.AdminRoleDTO;
import com.ws.ldy.manage.admin.model.dto.role.RoleAuthDTO;
import com.ws.ldy.manage.admin.model.dto.role.RoleMenuDTO;
import com.ws.ldy.manage.admin.model.dto.role.UserRoleDTO;
import com.ws.ldy.manage.admin.model.entity.*;
import com.ws.ldy.manage.admin.model.query.AdminRoleQuery;
import com.ws.ldy.manage.admin.model.vo.AdminRoleVO;
import com.ws.ldy.manage.admin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


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
        LambdaQueryWrapper<AdminRole> queryWrapper = new LambdaQueryWrapper<AdminRole>()
                .eq(query.getTerminal() != null, AdminRole::getTerminal, query.getTerminal())
                .orderByAsc(AdminRole::getId)
                .like(StringUtils.isNotBlank(query.getName()), AdminRole::getName, query.getName());
        if (query.getCurrent() <= 0) {
            // list
            IPage<AdminRoleVO> page = new Page<>();
            return page.setRecords(BeanDtoVoUtil.listVo(this.list(queryWrapper), AdminRoleVO.class));
        } else {
            // page
            return BeanDtoVoUtil.pageVo(this.page(new Page<>(query.getCurrent(), query.getSize()), queryWrapper), AdminRoleVO.class);
        }
    }

    /**
     * 添加角色-默认有所有URL 权限
     * @author wangsong
     * @param dto
     * @date 2020/9/19 0019 10:56
     * @return java.lang.Boolean
     * @version 1.0.0
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insert(AdminRoleDTO dto) {
        AdminRole role = dto.convert(AdminRole.class);
        this.save(role);
        // 给角色分配菜单权限
        adminRoleMenuService.insert(role.getId(), dto.getMenuIds());

        // 默认有所有url权限
        List<AdminAuthority> authorityList = adminAuthorityService.list(new LambdaQueryWrapper<AdminAuthority>().select(AdminAuthority::getId));
        List<AdminRoleAuth> roleAuthList = new ArrayList<>();
        for (AdminAuthority authority : authorityList) {
            roleAuthList.add(new AdminRoleAuth(authority.getId(), role.getId()));
        }
        return adminRoleAuthService.saveBatch(roleAuthList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean upd(String id, AdminRoleDTO dto) {
        AdminRole role = dto.convert(AdminRole.class);
        role.setId(id);
        this.updateById(role);
        // 给角色分配菜单权限(先删除后添加)
        adminRoleMenuService.remove(new LambdaUpdateWrapper<AdminRoleMenu>().eq(AdminRoleMenu::getRoleId, role.getId()));
        return adminRoleMenuService.insert(role.getId(), dto.getMenuIds());
    }

    /**
     * 查询用户角色| 用户当前拥有角色赋予 isChecked=true
     * @author wangsong
     * @mail 1720696548@qq.com
     * @date 2020/8/9 0009 9:44
     * @version 1.0.0
     */
    @Override
    public List<AdminRoleVO> findByUserIdRoleChecked(String userId) {
        //查询所有角色
        List<AdminRole> roles = this.list();
        //查询用户当前角色
        List<AdminRoleUser> roleUsers = roleUserAdminService.list(new LambdaQueryWrapper<AdminRoleUser>().eq(AdminRoleUser::getUserId, userId));
        Map<String, String> roleUserMap = new HashMap<>();
        roleUsers.forEach(item -> roleUserMap.put(item.getRoleId(), "0"));
        //返回数据
        List<AdminRoleVO> adminRoleVOList = new ArrayList<>();
        roles.forEach(role -> {
            AdminRoleVO roleVo = role.convert(AdminRoleVO.class);
            if (roleUserMap.containsKey(role.getId())) {
                roleVo.setIsChecked(true);
            } else {
                roleVo.setIsChecked(false);
            }
            adminRoleVOList.add(roleVo);
        });
        return adminRoleVOList;
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
     * @author wangsong
     * @date 2020/8/9 0009 9:41
     * @return boolean
     * @version 1.0.0
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
     *  分配角色菜单权限
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
     * @author wangsong
     * @date 2020/10/9 0009 15:50
     * @return boolean
     * @version 1.0.0
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean roleAuthAll() {
        List<AdminRole> roleList = adminRoleService.list();
        List<AdminAuthority> authList = adminAuthorityService.list(new LambdaQueryWrapper<AdminAuthority>()
                .select(AdminAuthority::getId)
                .eq(AdminAuthority::getType, Admin.AuthorityType.V0.getValue())
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
     *  分配角色url权限
     *
     * @return void
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/4/6 0006 17:47
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
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
        roleUserAdminService.remove(new LambdaUpdateWrapper<AdminRoleUser>().eq(AdminRoleUser::getRoleId, roleId));
        adminRoleMenuService.remove(new LambdaUpdateWrapper<AdminRoleMenu>().eq(AdminRoleMenu::getRoleId, roleId));
        adminRoleAuthService.remove(new LambdaUpdateWrapper<AdminRoleAuth>().eq(AdminRoleAuth::getRoleId, roleId));
        return this.removeById(roleId);
    }
}