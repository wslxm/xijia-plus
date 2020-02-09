package com.ws.ldy.baseadmin.service;


import com.ws.ldy.baseadmin.entity.UserAdmin;
import com.ws.ldy.admincore.service.BaseServiceApi;

/**
 * TODO  用户
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 15:10
 */
public interface UserAdminService extends BaseServiceApi<UserAdmin, Integer> {


    /**
     * 账号登录
     */
    public UserAdmin findAccountPwd(String account, String password);
}