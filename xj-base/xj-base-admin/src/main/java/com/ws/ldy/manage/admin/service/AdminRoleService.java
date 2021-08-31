package com.ws.ldy.manage.admin.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.manage.admin.model.dto.AdminRoleDTO;
import com.ws.ldy.manage.admin.model.dto.role.RoleAuthDTO;
import com.ws.ldy.manage.admin.model.dto.role.RoleMenuDTO;
import com.ws.ldy.manage.admin.model.dto.role.UserRoleDTO;
import com.ws.ldy.manage.admin.model.entity.AdminRole;
import com.ws.ldy.manage.admin.model.query.AdminRoleQuery;
import com.ws.ldy.manage.admin.model.vo.AdminRoleVO;

import java.util.List;

/**
 * 角色
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 15:10
 */
public interface AdminRoleService extends IService<AdminRole> {


    IPage<AdminRoleVO> list(AdminRoleQuery query);

    /**
     * 添加角色-默认有所有URL 权限
     * @author wangsong
     * @param dto
     */
    String insert(AdminRoleDTO dto);

    /**
     * 编辑角色信息
     * @param dto
     */
    Boolean upd(String id, AdminRoleDTO dto);

    /**
     * 删除角色并删除角色关联的  (权限+菜单+用户) 的关系数据
     */
    boolean del(String roleId);

    /**
     * 查询用户角色| 用户当前拥有角色赋予 isChecked=true
     * @param userId
     */
   // List<AdminRoleVO> findByUserIdRoleChecked(String userId);

    /**
     * 修改用户角色
     * @param userId
     * @param roleIds
     */
    boolean updUserRole(String userId, List<String> roleIds);

    /**
     * 分配用户角色
     */
    boolean updUserRole(UserRoleDTO dto);

    /**
     *  分配角色菜单权限
     */
    boolean roleMenuAuth(RoleMenuDTO dto);


    /***
     *  分配所有角色拥有所有权限
     */
    boolean roleAuthAll();

    /***
     *  分配角色url权限
     */
    boolean roleUrlAuth(RoleAuthDTO dto);

    /**
     * 获取超管角色
     */
    AdminRole findSysRole();

}