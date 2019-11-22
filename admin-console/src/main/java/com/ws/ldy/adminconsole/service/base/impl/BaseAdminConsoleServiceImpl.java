package com.ws.ldy.adminconsole.service.base.impl;

import com.ws.ldy.adminconsole.factory.DaoAdminConsoleFactory;
import com.ws.ldy.adminconsole.factory.ServiceAdminConsoleFactory;
import com.ws.ldy.admincore.service.impl.BaseServiceApiImpl;

import javax.annotation.Resource;
import java.io.Serializable;

public class BaseAdminConsoleServiceImpl<T, ID extends Serializable> extends BaseServiceApiImpl<T, ID> {

    @Resource
    protected DaoAdminConsoleFactory dao;
    @Resource
    protected ServiceAdminConsoleFactory service;
}
