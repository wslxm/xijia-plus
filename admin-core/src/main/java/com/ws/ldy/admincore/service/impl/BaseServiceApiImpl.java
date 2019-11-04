package com.ws.ldy.admincore.service.impl;


import com.ws.ldy.admincore.dao.BaseDao;
import com.ws.ldy.admincore.service.BaseServiceApi;

import java.io.Serializable;
import java.util.List;


/**
 * TODO  通用service
 * @author 王松
 * @WX-QQ 1720696548
 * @date  2019/10/31 21:12
 */
@SuppressWarnings("ALL")
public abstract class BaseServiceApiImpl<T,ID extends Serializable> implements BaseServiceApi<T,ID> {

    @Override
    public List<T> findAll(BaseDao dao) {
        return dao.findAll();
    }
    @Override
    public T save(BaseDao dao,T t) {
        return (T) dao.save(t);
    }

    @Override
    public T update(BaseDao dao,T t) {
        return (T) dao.saveAndFlush(t);
    }

    @Override
    public T get(BaseDao dao, ID id) {
        return (T) dao.getOne(id);
    }

    @Override
    public Boolean delete(BaseDao dao,ID id) {
        try{
            dao.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }


//
//    @Override
//    public List<T> findAll() {
//        return baseRepository.findAll();
//    }
//
//    @Override
//    public void delete(T t) {
//        baseRepository.delete(t);
//    }
//
////    @Override
////    public boolean exists(String id) {
////        return baseRepository.exists(id);
////    }
//
//    @Override
//    public long count() {
//        return baseRepository.count();
//    }
//
//    @Override
//    public List<T> findAll() {
//        return baseRepository.findAll();
//    }
//
//    @Override
//    public List<T> findAll(Sort sort) {
//        return baseRepository.findAll(sort);
//    }
//
//    @Override
//    public List<T> findAll(Specification<T> specification) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Page<T> findAll(Pageable pageable) {
//        return baseRepository.findAll(pageable);
//    }
//
//    @Override
//    public Page<T> findPage(Specification<T> specification, Pageable pageable) {
//        return null;
//    }

//    @Override
//    public Page<T> findAll(Specification<T> specification, Pageable pageable) {
//        // TODO Auto-generated method stub
//        return null;
//    }
}