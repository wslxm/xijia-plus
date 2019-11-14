package com.ws.ldy.adminconsole.service.base;

import com.ws.ldy.adminconsole.factory.DaoFactory;
import com.ws.ldy.adminconsole.factory.ServiceFactory;
import com.ws.ldy.admincore.service.impl.BaseServiceApiImpl;

import javax.annotation.Resource;
import java.io.Serializable;

public class BaseServiceImpl<T, ID extends Serializable> extends BaseServiceApiImpl<T, ID> {
    @Resource
    protected DaoFactory dao;
    @Resource
    protected ServiceFactory service;
}
