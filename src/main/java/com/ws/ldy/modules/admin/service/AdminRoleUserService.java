package com.ws.ldy.modules.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.modules.admin.model.entity.AdminRoleUser;
import com.ws.ldy.modules.admin.model.entity.AdminUser;
import com.ws.ldy.modules.admin.model.vo.AdminUserVO;

import java.util.List;

/**
 *   角色+用户管理
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 15:10
 */
public interface AdminRoleUserService extends IService<AdminRoleUser> {
    /**
     *     查询指定角色当前用户列表
     *
     * @param roleId
     * @return java.util.List<com.ws.ldy.adminconsole.entity.AdminRoleUser>
     * @date 2019/11/16 0016 23:00
     */
    List<AdminRoleUser> findRoleId(String roleId);


    /***
     *   赋与角色的用户选中状态
     * @param users
     * @date 2019/11/18 15:09
     * @return java.util.List<AdminUser>
     */
    public List<AdminUserVO> roleUserChecked(List<AdminUser> users, String roleId);

    /***
     *   角色用户分配
     * @param roleId
     * @param userIds
     * @date 2019/11/18 15:45
     * @return void
     */
    public void updRoleUser(String roleId, String[] userIds);
}