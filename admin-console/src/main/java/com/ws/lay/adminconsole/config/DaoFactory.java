package com.ws.lay.adminconsole.config;

import com.ws.lay.adminconsole.dao.UserDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DaoFactory {

    @Resource
    public UserDao  userDao;
}
