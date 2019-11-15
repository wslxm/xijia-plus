package com.ws.ldy.gamesheep.factory;

import com.ws.ldy.gamesheep.dao.UserSheepDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DaoSheepFactory {

    @Resource
    public UserSheepDao userDao;

}
