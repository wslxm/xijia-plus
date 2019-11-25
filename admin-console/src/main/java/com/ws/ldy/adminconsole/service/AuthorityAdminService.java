package com.ws.ldy.adminconsole.service;


import com.ws.ldy.adminconsole.entity.AuthorityAdmin;
import com.ws.ldy.adminconsole.service.base.BaseAdminConsoleService;

import java.util.List;

/**
 * TODO  权限管理
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Mon Nov 25 08:02:49 CST 2019
 */
public interface AuthorityAdminService extends BaseAdminConsoleService<AuthorityAdmin, Integer> {

    /**
     * TODO    刷新所有权限列表数据
     * @param classByPackageName
     * @date  2019/11/25 0025 11:55
     * @return void
     */
    void putClass(List<Class<?>> classByPackageName);

    /**
     * TODO    获取指定用户有url权限列表
     * @param userId 用户id
     * @date  2019/11/25 0025 11:55
     * @return void
     */
    List<AuthorityAdmin> findUserIdRoleAuthority(Integer userId);



}
