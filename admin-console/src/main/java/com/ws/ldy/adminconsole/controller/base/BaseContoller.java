package com.ws.lay.adminconsole.controller.base;


import com.ws.lay.adminconsole.factory.DaoFactory;
import com.ws.lay.adminconsole.factory.ServiceFactory;

import javax.annotation.Resource;

public class BaseContoller {

    @Resource
    protected DaoFactory dao;

    @Resource
    protected ServiceFactory service;
}
