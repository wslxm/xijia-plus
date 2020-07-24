package com.ws.ldy.modules.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.modules.admin.mapper.UserAdminMapper;
import com.ws.ldy.modules.admin.model.entity.UserAdmin;
import com.ws.ldy.modules.admin.service.UserAdminService;
import com.ws.ldy.others.base.service.impl.BaseIServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserAdminServiceImpl extends BaseIServiceImpl<UserAdminMapper, UserAdmin> implements UserAdminService {


    @Override
    public UserAdmin findByAccount(String username) {
        UserAdmin user = this.getOne(new LambdaQueryWrapper<UserAdmin>().eq(UserAdmin::getUsername, username));
        return user;
    }
}