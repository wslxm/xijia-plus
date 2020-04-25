package com.ws.ldy.admin.service;


import com.ws.ldy.admin.mapper.RoleUserAdminMapper;
import com.ws.ldy.admin.model.entity.RoleUserAdmin;
import com.ws.ldy.admin.model.entity.UserAdmin;
import com.ws.ldy.admin.model.vo.UserAdminVo;
import com.ws.ldy.base.service.BaseIService;

import java.util.List;

/**
 * TODO  角色+用户管理
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 15:10
 */
public interface RoleUserAdminService extends BaseIService<RoleUserAdminMapper, RoleUserAdmin> {
    /**
     * TODO    查询指定角色当前用户列表
     *
     * @param roleId
     * @return java.util.List<com.ws.ldy.adminconsole.entity.RoleUserAdmin>
     * @date 2019/11/16 0016 23:00
     */
    List<RoleUserAdmin> findRoleId(Integer roleId);


    /***
     * TODO  赋与角色的用户选中状态
     * @param users
     * @date 2019/11/18 15:09
     * @return java.util.List<UserAdmin>
     */
    public List<UserAdminVo> roleUserChecked(List<UserAdmin> users, Integer roleId);

    /***
     * TODO  角色用户分配
     * @param roleId
     * @param userIds
     * @date 2019/11/18 15:45
     * @return void
     */
    public void updRoleUser(Integer roleId, Integer[] userIds);
}