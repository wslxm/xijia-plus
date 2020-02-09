package com.ws.ldy.baseadmin.service;

import com.ws.ldy.baseadmin.entity.AuthorityAdmin;
import com.ws.ldy.admincore.service.BaseServiceApi;

import java.util.List;

/**
 * TODO  Url权限
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Mon Nov 25 08:02:49 CST 2019
 */
public interface AuthorityAdminService extends BaseServiceApi<AuthorityAdmin, Integer> {

    /**
     * TODO    刷新所有权限列表数据
     *
     * @return void
     * @date 2019/11/25 0025 11:55
     */
    void putClass();

    /**
     * TODO    获取指定用户有url权限列表
     *
     * @param userId 用户id
     * @return void
     * @date 2019/11/25 0025 11:55
     */
    List<AuthorityAdmin> findUserIdRoleAuthority(Integer userId);

}
