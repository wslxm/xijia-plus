package com.ws.ldy.adminconsole.service.impl;

import com.ws.ldy.adminconsole.dao.UserAdminDao;
import com.ws.ldy.adminconsole.entity.UserAdmin;
import com.ws.ldy.adminconsole.service.UserAdminService;
import com.ws.ldy.admincore.service.impl.BaseServiceApiImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAdminServiceImpl extends BaseServiceApiImpl<UserAdmin,Integer> implements UserAdminService {

    @Autowired
    private UserAdminDao userDao;


    @Override
    public UserAdmin findAccountPwd(String account, String password) {
        UserAdmin user = userDao.findAccountPwd(account, password);
        return user;
    }
}