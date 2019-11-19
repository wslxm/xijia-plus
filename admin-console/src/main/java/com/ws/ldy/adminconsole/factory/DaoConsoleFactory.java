package com.ws.ldy.adminconsole.factory;

import com.ws.ldy.adminconsole.dao.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DaoConsoleFactory {

    @Resource
    public UserAdminDao userDao;
    @Resource
    public MenuAdminDao menuDao;
    @Resource
    public RoleAdminDao roleDao;
    @Resource
    public RoleMenuAdminDao roleMenuDao;
    @Resource
    public RoleUserAdminDao roleUserDao;


}
