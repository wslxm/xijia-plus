package com.ws.ldy.adminconsole.controller.base;


import com.ws.ldy.adminconsole.factory.DaoAdminConsoleFactory;
import com.ws.ldy.adminconsole.factory.ServiceAdminConsoleFactory;
import com.ws.ldy.admincore.controller.BaseControllerApi;

import javax.annotation.Resource;

public class BaseAdminConsoleController extends BaseControllerApi {

    @Resource
    protected DaoAdminConsoleFactory dao;
    @Resource
    protected ServiceAdminConsoleFactory service;
}
