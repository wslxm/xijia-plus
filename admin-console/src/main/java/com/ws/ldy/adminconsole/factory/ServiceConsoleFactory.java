package com.ws.ldy.adminconsole.factory;

import com.ws.ldy.adminconsole.service.impl.MenuAdminServiceImpl;
import com.ws.ldy.adminconsole.service.impl.RoleAdminServiceImpl;
import com.ws.ldy.adminconsole.service.impl.RoleMenuAdminServiceImpl;
import com.ws.ldy.adminconsole.service.impl.UserAdminServiceImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ServiceConsoleFactory {

    @Resource
    public UserAdminServiceImpl userServiceImpl;

    @Resource
    public MenuAdminServiceImpl menuServiceImpl;

    @Resource
    public RoleAdminServiceImpl roleServiceImpl;

    @Resource
    public RoleMenuAdminServiceImpl roleMenuServiceImpl;
}
