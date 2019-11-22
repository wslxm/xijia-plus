package com.ws.ldy.admindemoweb.controller.base;


import com.ws.ldy.admincore.controller.BaseControllerApi;
import com.ws.ldy.admindemoweb.factory.DaoAdminDemoFactory;
import com.ws.ldy.admindemoweb.factory.ServiceAdminDemoFactory;

import javax.annotation.Resource;
/**
 * TODO  DaoFactory/ServiceFactory 添加自己项目下的（非core的依赖全部使用自己项目下，如自己项目表没有，则自行添加）  
 * @author 王松
 * @WX-QQ 1720696548
 * @date  2019/11/15 10:35
 */
public class BaseAdminDemoController extends BaseControllerApi {

    @Resource
    protected DaoAdminDemoFactory dao;

    @Resource
    protected ServiceAdminDemoFactory service;
}
