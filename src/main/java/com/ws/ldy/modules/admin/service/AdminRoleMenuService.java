package com.ws.ldy.modules.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.modules.admin.model.entity.AdminRoleMenu;

import java.util.List;

/**
 *   角色+菜单管理
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 15:10
 */
public interface AdminRoleMenuService extends IService<AdminRoleMenu> {
    /**
     *     查询指定角色所有菜单权限
     *
     * @param roleId
     * @return java.util.List<com.ws.ldy.adminconsole.entity.AdminRoleMenu>
     * @date 2019/11/16 0016 23:00
     */
    List<AdminRoleMenu> findRoleId(String roleId);

    /**
     *    角色菜单权限分配
     *
     * @param roleId  角色id
     * @param menuIds 菜单id集
     * @return void
     * @date 2019/11/17 0017 2:08
     */
    void roleMenuAuth(String roleId, String[] menuIds);
}