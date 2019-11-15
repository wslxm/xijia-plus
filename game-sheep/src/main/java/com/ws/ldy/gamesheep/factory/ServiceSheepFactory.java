package com.ws.ldy.gamesheep.factory;

import com.ws.ldy.gamesheep.service.impl.UserSheepServiceImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ServiceSheepFactory {

    @Resource
    public UserSheepServiceImpl userService;
}
