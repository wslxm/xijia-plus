package com.ws.lay.adminconsole.service.base;

import com.ws.lay.adminconsole.config.DaoFactory;
import com.ws.lay.adminconsole.config.ServiceFactory;
import com.ws.ldy.admincore.service.impl.BaseServiceApiImpl;

import java.io.Serializable;

public class BaseServiceImpl<T, ID extends Serializable> extends BaseServiceApiImpl<T, ID> {

    protected DaoFactory dao = new DaoFactory();
    protected ServiceFactory Service = new ServiceFactory();
}
