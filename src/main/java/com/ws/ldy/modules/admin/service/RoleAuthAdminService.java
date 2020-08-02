package com.ws.ldy.modules.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.modules.admin.model.entity.RoleAuthAdmin;
import com.ws.ldy.modules.admin.model.vo.AuthorityAdminVo;

import java.util.List;

/**
 *   角色+Url权限
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Mon Nov 25 11:38:29 CST 2019
 */
public interface RoleAuthAdminService  extends IService<RoleAuthAdmin> {

    /**
     *     根据用户Id 查询查询角色Id 在通过角色Id 获取 URL权限列表
     *
     * @param userId
     * @return List<RoleAuthAdmin>
     * @date 2019/11/25 0025 18:23
     */
    List<RoleAuthAdmin> findUserIdRoleAuthority(String userId);

    /**
     *     获取url权限列表，给指定角色的有的权限数据赋予选中状态
     *
     * @param userId 用户id
     * @return void
     * @date 2019/11/25 0025 11:55
     */
    List<AuthorityAdminVo> findRoleAuthorityChecked(String userId);

    /***
     *   角色用户分配
     * @param roleId
     * @param userIds
     * @date 2019/11/18 15:45
     * @return void
     */
    public void roleUrlAuth(String roleId, String[] userIds);
}
