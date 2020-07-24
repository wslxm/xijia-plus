package com.ws.ldy.others.base.service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;


/**
 * TODO  通用 service，自定义通用方法 Api
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/10/31 21:12
 */
public interface BaseIService<M extends BaseMapper<T>, T> { //, Dao extends BaseMapperApi

    boolean save(T entity);

    boolean saveBatch(Collection<T> entityList);

    boolean saveBatch(Collection<T> entityList, int batchSize);


    boolean saveOrUpdateBatch(Collection<T> entityList);

    boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize);


    boolean removeById(Serializable id);

    boolean removeByMap(Map<String, Object> columnMap);

    boolean remove(Wrapper<T> queryWrapper);

    boolean removeByIds(Collection<? extends Serializable> idList);

    boolean updateById(T entity);

    boolean update(Wrapper<T> updateWrapper);

    boolean update(T entity, Wrapper<T> updateWrapper);

    boolean updateBatchById(Collection<T> entityList);

    boolean updateBatchById(Collection<T> entityList, int batchSize);

    boolean saveOrUpdate(T entity);

    T getById(Serializable id);

    List<T> listByIds(Collection<? extends Serializable> idList);

    List<T> listByMap(Map<String, Object> columnMap);

    T getOne(Wrapper<T> queryWrapper);

    T getOne(Wrapper<T> queryWrapper, boolean throwEx);

    Map<String, Object> getMap(Wrapper<T> queryWrapper);

    <V> V getObj(Wrapper<T> queryWrapper, Function<? super Object, V> mapper);

    int count();

    int count(Wrapper<T> queryWrapper);


    List<T> list(Wrapper<T> queryWrapper);


    List<T> list();


    <E extends IPage<T>> E page(E page, Wrapper<T> queryWrapper);


    <E extends IPage<T>> E page(E page);


    List<Map<String, Object>> listMaps(Wrapper<T> queryWrapper);


    List<Map<String, Object>> listMaps();


    List<Object> listObjs();


    <V> List<V> listObjs(Function<? super Object, V> mapper);


    List<Object> listObjs(Wrapper<T> queryWrapper);


    <V> List<V> listObjs(Wrapper<T> queryWrapper, Function<? super Object, V> mapper);


    <E extends IPage<Map<String, Object>>> E pageMaps(E page, Wrapper<T> queryWrapper);


    <E extends IPage<Map<String, Object>>> E pageMaps(E page);


    BaseMapper<T> getBaseMapper();


    QueryChainWrapper<T> query();


    LambdaQueryChainWrapper<T> lambdaQuery();


    UpdateChainWrapper<T> update();


    LambdaUpdateChainWrapper<T> lambdaUpdate();


    boolean saveOrUpdate(T entity, Wrapper<T> updateWrapper);
}