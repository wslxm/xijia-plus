package com.ws.ldy.adminconsole.service.impl;

import com.ws.ldy.adminconsole.entity.UserAdmin;
import com.ws.ldy.adminconsole.service.UserAdminService;
import com.ws.ldy.adminconsole.service.base.BaseAdminServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserAdminServiceImpl extends BaseAdminServiceImpl<UserAdmin,Integer> implements UserAdminService {

    @Override
    public UserAdmin findAccountPwd(String account, String password) {
        UserAdmin user = dao.userDao.findAccountPwd(account, password);
        return user;
    }
}