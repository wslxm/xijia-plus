package com.ws.ldy.admincore.controller.impl;


import com.ws.ldy.admincore.controller.BaseApi;
import com.ws.ldy.admincore.dao.BaseDao;
import com.ws.ldy.admincore.service.BaseServiceApi;

import java.io.Serializable;
import java.util.List;

public class BaseApiImpl<T,ID extends Serializable> implements BaseApi<T,ID> {

    @Override
    public List<T> findAll(BaseServiceApi service, BaseDao dao){
       return service.findAll(dao);
    }

    @Override
    public T save(BaseServiceApi service, BaseDao dao, T t) {
        return (T) service.save(dao,t);
    }

    @Override
    public T update(BaseServiceApi service, BaseDao dao, T t) {
        return (T) service.update(dao,t);
    }

    @Override
    public T get(BaseServiceApi service, BaseDao dao, ID id) {
        return (T) service.get(dao,id);
    }

    @Override
    public boolean delete(BaseServiceApi service, BaseDao dao, ID id) {
        return service.delete(dao,id);
    }
}
