package com.ws.ldy.modules.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.modules.admin.mapper.AdminUserMapper;
import com.ws.ldy.modules.admin.model.entity.AdminUser;
import com.ws.ldy.modules.admin.service.AdminUserService;
import com.ws.ldy.others.base.service.impl.BaseIServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AdminUserServiceImpl extends BaseIServiceImpl<AdminUserMapper, AdminUser> implements AdminUserService {


    @Override
    public AdminUser findByAccount(String username) {
        AdminUser user = this.getOne(new LambdaQueryWrapper<AdminUser>().eq(AdminUser::getUsername, username));
        return user;
    }
}