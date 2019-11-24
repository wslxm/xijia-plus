package com.ws.ldy.adminconsole.factory;

import com.ws.ldy.adminconsole.dao.*;
import com.ws.ldy.adminconsole.dao.impl.DataBaseDaoImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DaoAdminConsoleFactory {

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
    @Resource
    public DataBaseDaoImpl dataBaseDao;
    @Resource
    public DictionaryAdminDao dictionaryAdminDao;  //此为代码生成
}




























