package com.ws.ldy.adminconsole.controller.base;


import com.ws.ldy.adminconsole.factory.DaoConsoleFactory;
import com.ws.ldy.adminconsole.factory.ServiceConsoleFactory;
import com.ws.ldy.admincore.controller.BaseControllerApi;

import javax.annotation.Resource;

public class BaseAdminController extends BaseControllerApi {

    @Resource
    protected DaoConsoleFactory dao;
    @Resource
    protected ServiceConsoleFactory service;
}
