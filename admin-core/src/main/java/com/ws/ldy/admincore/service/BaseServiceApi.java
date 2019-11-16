package com.ws.ldy.admincore.service;


import com.ws.ldy.admincore.dao.BaseDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * TODO  通用 service，自定义通用方法 Api
 * @author 王松
 * @WX-QQ 1720696548
 * @date  2019/10/31 21:12
 */
@SuppressWarnings("ALL")
public  interface  BaseServiceApi<T,ID extends Serializable>{

    /** 查询所有  */
    public List<T> findAll(BaseDao dao);

    /** 添加  */
    public T save(BaseDao dao,T t);

    /** 添加  */
    public Boolean saveAll(BaseDao dao,List<T> ts);

    /** 修改 */
    public T update(BaseDao dao,T t);

    /** id查询 */
    public T get(BaseDao dao,ID id);

    /** id删除 */
    public Boolean deleteById(BaseDao dao,ID id) ;

    /** id批量删除 */
    public Boolean deleteByIds(BaseDao dao,ID[] ids);

    /** 对象批量删除 */
    public Boolean deleteInBatch(BaseDao dao, List<T> ts) ;

    /** 分页+条件+排序查询，如有特殊条件使用 service子类重写该实现方法  */
    public Page<T> page(BaseDao dao,int page, int size, Map<String, Object> param, Sort sort);



//    public void delete(BaseDao dao,T t);
//
//    public boolean exists(BaseDao dao,String id) ;
//
//    public long count(BaseDao dao);
//
//    public List<T> findAll(BaseDao dao,Sort sort) ;
//
//    public List<T> findAll(BaseDao dao,Specification<T> specification) ;
//
//    public Page<T> findAll(BaseDao dao,Pageable pageable) ;
//
//    public Page<T> findPage(BaseDao dao,Specification<T> specification, Pageable pageable);
//
//    public Page<T> findAll(BaseDao dao,Specification<T> specification, Pageable pageable) ;
}