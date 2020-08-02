package com.ws.ldy.modules.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.modules.admin.model.entity.UserAdmin;

/**
 *   用户
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 15:10
 */
public interface UserAdminService extends IService<UserAdmin> {

    /**
     * 账号登录
     */
    public UserAdmin findByAccount(String account);
}