package com.ws.ldy.adminconsole.service.base;

import com.ws.ldy.adminconsole.factory.DaoConsoleFactory;
import com.ws.ldy.adminconsole.factory.ServiceConsoleFactory;
import com.ws.ldy.admincore.service.impl.BaseServiceApiImpl;

import javax.annotation.Resource;
import java.io.Serializable;

public class BaseAdminServiceImpl<T, ID extends Serializable> extends BaseServiceApiImpl<T, ID> {
    @Resource
    protected DaoConsoleFactory dao;
    @Resource
    protected ServiceConsoleFactory service;
}
