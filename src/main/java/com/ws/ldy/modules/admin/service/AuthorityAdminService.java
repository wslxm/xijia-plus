package com.ws.ldy.modules.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.modules.admin.model.entity.AuthorityAdmin;

import java.util.List;

/**
 * TODO  Url权限
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Mon Nov 25 08:02:49 CST 2019
 */
public interface AuthorityAdminService  extends IService<AuthorityAdmin> {

    /**
     * TODO  刷新所有权限列表数据
     *
     * @return void
     * @date 2019/11/25 0025 11:55
     */
    void refreshAuthority();

    /**
     * TODO    获取指定用户有url权限列表
     *
     * @param userId 用户id
     * @return void
     * @date 2019/11/25 0025 11:55
     */
    List<AuthorityAdmin> findUserIdRoleAuthority(Integer userId);

}
