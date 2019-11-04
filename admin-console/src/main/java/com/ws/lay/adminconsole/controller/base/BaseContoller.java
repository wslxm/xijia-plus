package com.ws.lay.adminconsole.controller.base;


import com.ws.lay.adminconsole.config.DaoFactory;
import com.ws.lay.adminconsole.config.ServiceFactory;
import com.ws.ldy.admincore.controller.impl.BaseApiImpl;

import javax.annotation.Resource;
import java.io.Serializable;

public class BaseContoller<T, ID extends Serializable> extends BaseApiImpl<T, ID> {

     @Resource
     protected DaoFactory dao;

     @Resource
     protected ServiceFactory service;
}
