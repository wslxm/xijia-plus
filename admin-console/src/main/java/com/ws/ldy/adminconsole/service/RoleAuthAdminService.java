package com.ws.ldy.adminconsole.service;


import com.ws.ldy.adminconsole.entity.AuthorityAdmin;
import com.ws.ldy.adminconsole.entity.RoleAuthAdmin;
import com.ws.ldy.admincore.service.BaseServiceApi;

import java.util.List;

/**
 * TODO  代码生成器自动生成，请自定义描叙
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Mon Nov 25 11:38:29 CST 2019
 */
public interface RoleAuthAdminService extends BaseServiceApi<RoleAuthAdmin, Integer> {

    /**
     * TODO    根据用户Id 查询查询角色Id 在通过角色Id 获取 URL权限列表
     *
     * @param userId
     * @return List<RoleAuthAdmin>
     * @date 2019/11/25 0025 18:23
     */
    List<RoleAuthAdmin> findUserIdRoleAuthority(Integer userId);

    /**
     * TODO    获取url权限列表，给指定角色的有的权限数据赋予选中状态
     * @param userId 用户id
     * @date  2019/11/25 0025 11:55
     * @return void
     */
    List<AuthorityAdmin> findRoleAuthorityChecked(Integer userId);
    /***
     * TODO  角色用户分配
     * @param roleId
     * @param userIds
     * @date 2019/11/18 15:45
     * @return void
     */
    public void roleUrlAuth(Integer roleId, Integer[] userIds);
}
