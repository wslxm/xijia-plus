package com.ws.ldy.admincore.service.impl;


import com.ws.ldy.admincore.dao.BaseDao;
import com.ws.ldy.admincore.service.BaseServiceApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
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
public abstract class BaseServiceApiImpl<T, ID extends Serializable> implements BaseServiceApi<T, ID> {

    @Override
    public List<T> findAll(BaseDao dao) {
        return dao.findAll();
    }

    @Override
    public T save(BaseDao dao, T t) {
        return (T) dao.save(t);
    }

    @Override
    public Boolean saveAll(BaseDao dao, List<T> ts) {
        if (dao.saveAll(ts) != null) {
            return true;
        }
        return false;
    }

    @Override
    public T update(BaseDao dao, T t) {
        return (T) dao.saveAndFlush(t);
    }

    @Override
    public T get(BaseDao dao, ID id) {
        return (T) dao.getOne(id);
    }

    @Override
    public Boolean deleteById(BaseDao dao, ID id) {
        try {
            dao.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean deleteByIds(BaseDao dao, ID[] ids) {
        // List<ID> ids1 = Arrays.asList(ids);
        try {
            for (ID id : ids) {
                dao.deleteById(id);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean deleteInBatch(BaseDao dao, List<T> ts) {
        try {
           dao.deleteInBatch(ts);
           return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 支持时间段，模糊，各种条件，分页(page,size)，排序（sort）等查询
     *
     * @param dao
     * @param page  页数
     * @param size  每页记录数
     * @param param 查询条件集
     * @param sort  排序
     * @return
     */
    @Override
    public Page<T> page(BaseDao dao, int page, int size, Map<String, Object> param, Sort sort) {
        return dao.findAll(new Specification<T>() {
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
//                if("title".equals(key) || "content".equals(key)) {
//                    if(param.get(key) != null && !"".equals(param.get(key).toString()) && !"-1".equals(param.get(key).toString())) {
//                        list.add(cb.like(root.get(key).as(String.class), ("%"+param.get(key))+"%".toString()));
//                    }
//                }else if("releasetime".equals(key) && param.get(key) != null && !"".equals(param.get(key).toString())) {
//                    list.add(cb.lessThanOrEqualTo(root.get("releasetime"),(Date)param.get(key)));
//                }else {
//                    if(param.get(key) != null && !"".equals(param.get(key).toString()) && !"-1".equals(param.get(key).toString())) {
//                        list.add(cb.equal(root.get(key).as(String.class), (param.get(key)).toString()));
//                    }
//                }
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