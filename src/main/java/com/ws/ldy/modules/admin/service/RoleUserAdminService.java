package com.ws.ldy.modules.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.modules.admin.model.entity.RoleUserAdmin;
import com.ws.ldy.modules.admin.model.entity.UserAdmin;
import com.ws.ldy.modules.admin.model.vo.UserAdminVo;

import java.util.List;

/**
 * TODO  角色+用户管理
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 15:10
 */
public interface RoleUserAdminService extends IService<RoleUserAdmin> {
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