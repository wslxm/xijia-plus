package com.ws.lay.adminconsole.factory;

import com.ws.lay.adminconsole.dao.MenuDao;
import com.ws.lay.adminconsole.dao.UserDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DaoFactory {

    @Resource
    public UserDao  userDao;
    @Resource
    public MenuDao menuDao;

}
