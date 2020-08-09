package com.ws.ldy.modules.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.modules.admin.model.entity.AdminUser;

import java.util.List;

/**
 *   用户
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 15:10
 */
public interface AdminUserService extends IService<AdminUser> {


    /**
     * 根据角色Id查询指定用户
     * @author wangsong
     * @param roleId
     * @date 2020/8/9 0009 10:08
     * @return void
     * @version 1.0.0
     */
    public List<AdminUser> findByRoleId(String roleId);
}