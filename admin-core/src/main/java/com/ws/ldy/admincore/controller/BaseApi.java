package com.ws.ldy.admincore.controller;

import com.ws.ldy.admincore.dao.BaseDao;
import com.ws.ldy.admincore.service.BaseServiceApi;

import java.io.Serializable;
import java.util.List;
/**
 * TODO  ，自定义通用 controller层 Api，不介意使用
 * @author 王松
 * @WX-QQ 1720696548
 * @date  2019/10/31 21:12
 */
public interface BaseApi <T,ID extends Serializable> {

    /**  查询所有  */
    public List<T> findAll(BaseServiceApi service, BaseDao dao);

    /** 添加  */
    public T save(BaseServiceApi service,BaseDao dao,T t);

    /** 修改 */
    public T update(BaseServiceApi service,BaseDao dao,T t);

    /** id查询 */
    public T get(BaseServiceApi service,BaseDao dao, ID id);

    /** id删除 */
    public boolean delete(BaseServiceApi service,BaseDao dao,ID id) ;
}
