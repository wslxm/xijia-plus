package com.ws.ldy.admincore.service;


import cn.hutool.core.util.StrUtil;
import com.ws.ldy.admincore.dao.BaseDao;
import com.ws.ldy.admincore.utils.SpringContextUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;


/**
 * TODO  通用 service，自定义通用方法 Api
 * @author 王松
 * @WX-QQ 1720696548
 * @date  2019/10/31 21:12
 */
@SuppressWarnings("ALL")
 public interface  BaseServiceApi<T,ID extends Serializable>{


    /** 查询所有  */
     List<T> findAll();

    /** 添加  */
     T save(T t);

    /** 添加  */
     Boolean saveAll(List<T> ts);

    /** 修改 */
     T update(T t);

    /** id查询 */
     T get(ID id);

    /** id删除 */
     Boolean deleteById(ID id) ;

    /** id批量删除 */
     Boolean deleteByIds(ID[] ids);

    /** 对象批量删除 */
     Boolean deleteInBatch(List<T> ts) ;

    /** 分页+条件+排序查询，如有特殊条件使用 service子类重写该实现方法  */
     Page<T> fingPage( int page, int size, Map<Integer, Map<String, Object>> param, Sort sort);

    /** 分页+条件+排序查询，如有特殊条件使用 service子类重写该实现方法  */
     Page<T> page(int page, int size, Map<String, Object> param, Sort sort);



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