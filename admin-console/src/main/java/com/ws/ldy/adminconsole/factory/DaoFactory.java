package com.ws.ldy.adminconsole.factory;

import com.ws.ldy.adminconsole.dao.MenuDao;
import com.ws.ldy.adminconsole.dao.RoleDao;
import com.ws.ldy.adminconsole.dao.UserDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DaoFactory {

    @Resource
    public UserDao userDao;
    @Resource
    public MenuDao menuDao;
    @Resource
    public RoleDao roleDao;
}
