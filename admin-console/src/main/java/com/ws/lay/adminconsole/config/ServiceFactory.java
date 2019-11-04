package com.ws.lay.adminconsole.config;

import com.ws.lay.adminconsole.service.impl.UserServiceImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ServiceFactory {

    @Resource
    public UserServiceImpl userServiceImpl;

}
