package com.ws.ldy.adminconsole.service;


import com.ws.ldy.adminconsole.entity.RoleMenuAdmin;
import com.ws.ldy.admincore.service.BaseServiceApi;

import java.util.List;

/**
 * TODO  角色菜单管理
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 15:10
 */
public interface RoleMenuAdminService extends BaseServiceApi<RoleMenuAdmin, Integer> {
    /**
     * TODO    查询指定角色所有菜单权限
     *
     * @param roleId
     * @return java.util.List<com.ws.ldy.adminconsole.entity.RoleMenuAdmin>
     * @date 2019/11/16 0016 23:00
     */
    List<RoleMenuAdmin> findRoleId(Integer roleId);

    /**
     * TODO   角色菜单权限分配
     *
     * @param roleId  角色id
     * @param menuIds 菜单id集
     * @param pid     菜单id(系统级)
     * @return void
     * @date 2019/11/17 0017 2:08
     */
    void roleMenuAuth(Integer roleId, Integer[] menuIds, Integer pid);
}