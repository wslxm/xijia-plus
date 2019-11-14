package com.ws.lay.adminconsole.factory;

import com.ws.lay.adminconsole.service.impl.MenuServiceImpl;
import com.ws.lay.adminconsole.service.impl.UserServiceImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ServiceFactory {

    @Resource
    public UserServiceImpl userServiceImpl;

    @Resource
    public MenuServiceImpl menuServiceImpl;

}
