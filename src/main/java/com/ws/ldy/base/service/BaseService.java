package com.ws.ldy.base.service;


import com.ws.ldy.base.query.DeleteCriteria;
import com.ws.ldy.base.query.IPage;
import com.ws.ldy.base.query.QueryCriteria;
import com.ws.ldy.base.query.UpdateCriteria;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;


/**
 * TODO  通用 service，自定义通用方法 Api
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/10/31 21:12
 */
public interface BaseService<T, ID extends Serializable> { //, Dao extends BaseDao

    // TODO id查询
    T selectById(ID id);

    // TODO  查询所有
    List<T> selectList();

    // TODO 排序，条件搜索
    List<T> selectList(QueryCriteria queryCriteria);


    // TODO 分页，排序，条件搜索 （ 支持时间段，模糊，各种条件，分页(page,size)，排序（sort）等查询）
    Page<T> selectPage(IPage iPage, QueryCriteria queryCriteria);


    // TODO  单-添加/修改
    T save(T t);

    // TODO  多-添加/修改
    boolean saveAll(List<T> ts);

    // TODO  生成动态修改sql（hql 方式） ==>  UPDATE 表名称 SET 列名称 = 新值 WHERE 列名称 = 某值
    int update(UpdateCriteria<T> updateCriteria);

    // TODO   id删除 ==>> 物理删除
    boolean deleteById(ID id);

    // TODO  多id删除 ==>> 物理删除
    boolean deleteByIds(ID[] ids);

    // TODO  根据实体对象删除  ==>> 物理删除
    boolean deleteInBatch(List<T> ts);

    // TODO  生成动态删除sql（hql 方式) ==>> 物理删除
    int delete(DeleteCriteria<T> deleteCriteria);

    // TODO  生成动态删除sql（hql 方式) ==>> 逻辑删除
    int deleted(DeleteCriteria<T> deleteCriteria);

    // TODO 分页+条件+排序查询，如有特殊条件使用 service子类重写该实现方法
    // Page<T> page(int page, int size, Map<String, Object> param, Sort sort);


//     void delete(BaseDao dao,T t);
//
//     boolean exists(BaseDao dao,String id) ;
//
//     long count(BaseDao dao);
//
//     List<T> findAll(BaseDao dao,Sort sort) ;
//
//     List<T> findAll(BaseDao dao,Specification<T> specification) ;
//
//     Page<T> findAll(BaseDao dao,Pageable pageable) ;
//
//     Page<T> findPage(BaseDao dao,Specification<T> specification, Pageable pageable);
//
//     Page<T> findAll(BaseDao dao,Specification<T> specification, Pageable pageable) ;
}