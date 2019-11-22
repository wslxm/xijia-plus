package com.ws.ldy.admindemoweb.service.base.impl;


import com.ws.ldy.admincore.service.impl.BaseServiceApiImpl;
import com.ws.ldy.admindemoweb.factory.DaoGameSheepFactory;
import com.ws.ldy.admindemoweb.factory.ServiceGameSheepFactory;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * TODO   DaoFactory/ServiceFactory 添加自己项目下的（非core的依赖全部使用自己项目下，如自己项目表没有，则自行添加）
 * @author 王松
 * @WX-QQ 1720696548
 * @date  2019/11/15 10:23
 */
public class BaseGameSheepServiceImpl<T, ID extends Serializable> extends BaseServiceApiImpl<T, ID> {
    @Resource
    protected DaoGameSheepFactory dao;
    @Resource
    protected ServiceGameSheepFactory service;
}
