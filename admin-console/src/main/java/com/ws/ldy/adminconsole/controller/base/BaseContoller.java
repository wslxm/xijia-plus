package com.ws.ldy.adminconsole.controller.base;


import com.ws.ldy.adminconsole.factory.DaoFactory;
import com.ws.ldy.adminconsole.factory.ServiceFactory;
import com.ws.ldy.admincore.controller.BaseControllerApi;

import javax.annotation.Resource;

public class BaseContoller extends BaseControllerApi {

    @Resource
    protected DaoFactory dao;

    @Resource
    protected ServiceFactory service;
}
