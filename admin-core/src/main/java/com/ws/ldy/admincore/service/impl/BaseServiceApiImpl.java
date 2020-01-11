package com.ws.ldy.admincore.service.impl;


import cn.hutool.core.util.StrUtil;
import com.ws.ldy.admincore.dao.BaseDao;
import com.ws.ldy.admincore.service.BaseServiceApi;
import com.ws.ldy.admincore.utils.SpringContextUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * TODO  通用service
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/10/31 21:12
 */
@SuppressWarnings("ALL")
public class BaseServiceApiImpl<T, ID extends Serializable> implements BaseServiceApi<T, ID> {

    /**
     * 通过泛型T获取出bengId ，在通过bengId获取到对应dao类的实例
     */
    private  BaseDao getDao(){
        //获取类上的泛型
        Type types = this.getClass().getGenericSuperclass();
        Type[] genericType = ((ParameterizedType) types).getActualTypeArguments();
        // 获取到entity 的类路径
        String entityCalss = genericType[0].getTypeName();
        // 获取到Dao类名=bend名称
        String  daoCalssName = entityCalss.substring(entityCalss.lastIndexOf(".")+1,entityCalss.length())+"Dao" ;
        // 通过beng 获取到dao实例（首字母小写：hutool 文档: https://apidoc.gitee.com/loolly/hutool/）,
        BaseDao  dao = (BaseDao) SpringContextUtil.getBean(StrUtil.lowerFirst(daoCalssName));
        return dao;
    }


    @Override
    public List<T> findAll() {
        return getDao().findAll();
    }

    @Override
    public T save( T t) {
        return (T) getDao().save(t);
    }

    @Override
    public Boolean saveAll( List<T> ts) {
        if (getDao().saveAll(ts) != null) {
            return true;
        }
        return false;
    }

    @Override
    public T update( T t) {
        return (T) getDao().saveAndFlush(t);
    }

    @Override
    public T get( ID id) {
        return (T) getDao().getOne(id);
    }

    @Override
    public Boolean deleteById( ID id) {
        try {
            getDao().deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean deleteByIds( ID[] ids) {
        // List<ID> ids1 = Arrays.asList(ids);
        try {
            for (ID id : ids) {
                getDao().deleteById(id);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean deleteInBatch( List<T> ts) {
        try {
            getDao().deleteInBatch(ts);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 支持时间段，模糊，各种条件，分页(page,size)，排序（sort）等查询
     * <p>
     * sort 参数说明：
     * -----排序规则：ASC,DESC
     * -----单字段排序：        Sort sort = new Sort(Sort.Direction.ASC, "id");
     * -----多字段排序同规则：   Sort sort = new Sort(Sort.Direction.ASC, "id","openId");
     * -----多字段排序不同规则： Sort sort = new Sort(Sort.Direction.ASC, "id").and(new Sort(Sort.Direction.DESC, "openId"));
     * <p>
     * param 参数外层 Map说明：
     * ----- key = 1   字符串/数字/时间 精准搜索
     * ----- key = 2   字符串/数字/时间 模糊搜索
     * ----- key = 3   两者之间， key ：字段名，start：开始 , ent :结束  (数字/时间)
     * ----- key = 4   大于或等于传入值（字符串/数字/时间）
     * ----- key = 5   小于或等于传入值（字符串/数字/时间）
     * ----- key = 6   大于传入值（字符串/数字/时间）
     * ----- key = 7   小于传入值（字符串/数字/时间）
     * <p>
     * param 内参数内层 Map说明：
     * ----- key = 查询字段，对应实体类字段
     * ----- value = 查询条件，值为null，为空串（""）时排除该查询条件，查询所有或满足其他条件的
     * <p>
     * 两者之间查询说明， 外层Map key = 3
     * ----- 支持时间段查询
     * ----- 数子范围查询
     * ----- 内Object 内还有一次 Map。包含固定字段 start,ent，必须添加 start,ent，否则不添加该查询条件
     * ----- 当单向时间段查询时，使用当外层Map key =  4| 5| 6 |7 可满足查询
     *
     * @param dao
     * @param page  页数
     * @param size  记录数
     * @param param 查询条件集
     * @param sort  排序
     * @return
     */
    @Override
    public Page<T> fingPage( int page, int size, Map<Integer, Map<String, Object>> param, Sort sort) {
        return getDao().findAll(new Specification<T>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                // TODO Auto-generated method stub
                //条件api集
                List<Predicate> list = new ArrayList<>();
                for (Integer keyType : param.keySet()) {
                    Map<String, Object> queryMap = param.get(keyType);
                    if (keyType == 1) {
                        //精准搜索条件拼接
                        for (String key : queryMap.keySet()) {
                            if (queryMap.get(key) != null && !"".equals(queryMap.get(key).toString())) {
                                list.add(cb.equal(root.get(key).as(String.class), queryMap.get(key).toString()));
                            }
                        }
                    } else if (keyType == 2) {
                        //模糊搜索条件拼接
                        for (String key : queryMap.keySet()) {
                            if (queryMap.get(key) != null && !"".equals(queryMap.get(key).toString())) {
                                list.add(cb.like(root.get(key).as(String.class), ("%" + queryMap.get(key) + "%").toString()));
                            }
                        }
                    } else if (keyType == 3) {
                        // 两者之间条件拼接， key ：字段名，start：开始 , ent :结束
                        for (String key : queryMap.keySet()) {
                            Map<String, Object> betweenMap = (Map<String, Object>) queryMap.get(key);
                            if (betweenMap.get("start") != null && betweenMap.get("ent") != null) {
                                String startTime = betweenMap.get("start").toString();
                                String entTime = betweenMap.get("ent").toString();
                                if (!"".equals(key) && !"".equals(startTime) && !"".equals(entTime)) {
                                    list.add(cb.between(root.get(key).as(String.class), startTime, entTime));
                                }
                            }
                        }
                    } else if (keyType == 4) {
                        //大于或等于传入值（支持字符串/数字/时间）
                        for (String key : queryMap.keySet()) {
                            if (queryMap.get(key) != null && !"".equals(queryMap.get(key).toString())) {
                                list.add(cb.greaterThanOrEqualTo(root.get(key).as(String.class), queryMap.get(key).toString()));
                            }
                        }
                    } else if (keyType == 5) {
                        //小于或等于传入值（支持字符串/数字/时间）
                        for (String key : queryMap.keySet()) {
                            if (queryMap.get(key) != null && !"".equals(queryMap.get(key).toString())) {
                                list.add(cb.lessThanOrEqualTo(root.get(key).as(String.class), queryMap.get(key).toString()));
                            }
                        }
                    } else if (keyType == 6) {
                        //  大于传入值（支持字符串/数字/时间）
                        for (String key : queryMap.keySet()) {
                            if (queryMap.get(key) != null && !"".equals(queryMap.get(key).toString())) {
                                list.add(cb.greaterThan(root.get(key).as(String.class), queryMap.get(key).toString()));
                            }
                        }
                    } else if (keyType == 7) {
                        //  小于传入值（字符串/数字/时间）
                        for (String key : queryMap.keySet()) {
                            if (queryMap.get(key) != null && !"".equals(queryMap.get(key).toString())) {
                                list.add(cb.lessThan(root.get(key).as(String.class), queryMap.get(key).toString()));
                            }
                        }
                    }
                }
                Predicate[] p = list.toArray(new Predicate[0]);
                return cb.and(p);
            }
        }, PageRequest.of(page - 1, size, sort));
    }


    @Override
    public Page<T> page( int page, int size, Map<String, Object> param, Sort sort) {
        return getDao().findAll(new Specification<T>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                // TODO Auto-generated method stub
                List<Predicate> list = new ArrayList<>();
                for (String key : param.keySet()) {
                    if (param.get(key) != null && !"".equals(param.get(key).toString())) {
                        list.add(cb.equal(root.get(key).as(String.class), (param.get(key)).toString()));
                    }
                }
                Predicate[] p = list.toArray(new Predicate[0]);
                return cb.and(p);
            }
        }, PageRequest.of(page - 1, size, sort));
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