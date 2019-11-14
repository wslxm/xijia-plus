package com.ws.ldy.adminconsole.factory;

import com.ws.ldy.adminconsole.service.impl.MenuServiceImpl;
import com.ws.ldy.adminconsole.service.impl.RoleServiceImpl;
import com.ws.ldy.adminconsole.service.impl.UserServiceImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ServiceFactory {

    @Resource
    public UserServiceImpl userServiceImpl;

    @Resource
    public MenuServiceImpl menuServiceImpl;

    @Resource
    public RoleServiceImpl roleServiceImpl;
}
