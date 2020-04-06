package com.ws.ldy.base.service.impl;

import com.ws.ldy.base.dao.impl.BaseDaoImpl;
import com.ws.ldy.base.service.BaseService;
import com.ws.ldy.base.query.DeleteCriteria;
import com.ws.ldy.base.query.IPage;
import com.ws.ldy.base.query.QueryCriteria;
import com.ws.ldy.base.query.UpdateCriteria;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
@Slf4j
//public class BaseServiceImpl<T, ID extends Serializable, Dao extends BaseDao> implements BaseService<T, ID, Dao> {
public class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * TODO   通过泛型Dao 获取出bengId ，在通过bengId获取到对应dao层的实例
     *
     * @return com.ws.ldy.base.dao.BaseDao
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/4/3 0003 18:21
     */
//    private BaseDao getDao() {
//        //获取类上的泛型集
//        Type types = this.getClass().getGenericSuperclass();
//        Type[] genericType = ((ParameterizedType) types).getActualTypeArguments();
//        // 获取到dao的类路径, 获取泛型的第3个参数
//        String daoCalss = genericType[2].getTypeName();
//        // 获取到Dao类名=bend名称
//        String daoCalssName = daoCalss.substring(daoCalss.lastIndexOf(".") + 1, daoCalss.length());
//        // 通过beng 获取到dao实例（首字母小写：hutool 文档: https://apidoc.gitee.com/loolly/hutool/）,
//        BaseDao dao = (BaseDao) SpringContextUtil.getBean(StrUtil.lowerFirst(daoCalssName));
//        return dao;
//    }

    /**
     * TODO   获取BaseDaoImpl（通过泛型T 获取对象class，在获取传递到对应实体类到 BaseDaoImpl）
     *
     * @return com.ws.ldy.base.dao.BaseDao
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/4/3 0003 18:21
     */
    @SneakyThrows
    private BaseDaoImpl<T, ID> getDao() {
        // 获取类上的泛型集
        Type[] genericType = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();
        // 获取泛型的第1个参数=实体类路径，在反射获取类对象
        Class<?> aClass = Class.forName(genericType[0].getTypeName());
        // 获取baseDao实体类
        BaseDaoImpl<T, ID> dao = new BaseDaoImpl(aClass, entityManager);
        return dao;
    }

    /**
     * TODO   获取dao (传递dao class对象)
     *
     * @return com.ws.ldy.base.dao.BaseDao
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/4/3 0003 18:21
     */
    @SneakyThrows
    private BaseDaoImpl<T, ID> getDao(Class<?> aClass) {
        // 获取baseDao实体类
        BaseDaoImpl<T, ID> dao = new BaseDaoImpl(aClass, entityManager);
        return dao;
    }


    @Override
    public List<T> findAll() {
        return getDao().findAll();
    }

    @Override
    public List<T> list() {
        return list(null);
    }

    /**
     * TODO 排序，条件搜索
     * 支持时间段，模糊，各种条件，分页(page,size)，排序（sort）等查询
     * <p>
     * sort 参数说明：
     * -----排序规则：ASC,DESC
     * -----单字段排序：        Sort sort = new Sort(Sort.Direction.ASC, "id");
     * -----多字段排序同规则：   Sort sort = new Sort(Sort.Direction.ASC, "id","openId");
     * -----多字段排序不同规则： Sort sort = new Sort(Sort.Direction.ASC, "id").and(new Sort(Sort.Direction.DESC, "openId"));
     * <p>
     * param 参数外层 Map说明：
     * ----- key = equal                精准搜索 字符串/数字/时间
     * ----- key = like                 模糊搜索 字符串/数字/时间
     * ----- key = between              两者之间， key ：字段名，start：开始 , ent :结束  (数字/时间)
     * ----- key = greaterThanOrEqualTo 大于或等于传入值（字符串/数字/时间）
     * ----- key = lessThanOrEqualTo    小于或等于传入值（字符串/数字/时间）
     * ----- key = greaterThan          大于传入值（字符串/数字/时间）
     * ----- key = lessThan             小于传入值（字符串/数字/时间）
     * <p>
     * param 内参数内层 Map 说明：
     * ----- key = 查询字段，对应实体类字段
     * ----- value = 查询条件，值为null，为空串（""）时排除该查询条件，查询所有或满足其他条件的
     * <p>
     * <p>
     * 两者之间 between 查询说明， 外层Map key = between
     * ----- 支持时间段查询
     * ----- 支持数字范围查询
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
    public List<T> list(QueryCriteria queryCriteria) {
        if (queryCriteria == null) {
            // 查询所有
            return getDao().findAll();
        }
        //
        Sort sort = queryCriteria.getSort();
        Map<String, Map<String, Object>> param = queryCriteria.build();
        //
        if (sort != null && param.size() > 0) {
            //排序+条件搜索
            return getDao().findAll(this.getSpecification(param), sort);
        } else if (sort != null) {
            //排序
            return getDao().findAll(sort);
        } else if (param.size() > 0) {
            //条件搜索
            return getDao().findAll(this.getSpecification(param));
        }
        return getDao().findAll();
    }


    @Override
    public T save(T t) {
        return (T) getDao().save(t);
    }


    @Override
    public Boolean saveAll(List<T> ts) {
        if (getDao().saveAll(ts) != null) {
            return true;
        }
        return false;
    }

    @Override
    public T update(T t) {
        return (T) getDao().saveAndFlush(t);
    }


    /**
     * TODO  生成动态修改sql（hql 方式）
     * UPDATE 表名称 SET 列名称 = 新值 WHERE 列名称 = 某值
     *
     * @param updateCriteria
     * @return int
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/4/4 0004 22:59
     */
    @Override
    @SneakyThrows
    @Transactional
    public int update(UpdateCriteria updateCriteria) {
        int result = this.getDao().update(updateCriteria);
        return result;
    }


    @Override
    public T get(ID id) {
        return (T) getDao().getOne(id);
    }

    /**
     * TODO  id删除 ==>> 物理删除
     */
    @Override
    public Boolean deleteById(ID id) {
        try {
            getDao().deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * TODO  多id删除 ==>> 物理删除
     */
    @Override
    public Boolean deleteByIds(ID[] ids) {
        try {
            for (ID id : ids) {
                getDao().deleteById(id);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * TODO  生成动态删除sql（hql 方式) ==>> 物理删除
     */
    @Override
    @Transactional
    public int delete(DeleteCriteria deleteCriteria) {
        int result = this.getDao().delete(deleteCriteria);
        return result;
    }

    /**
     * TODO  生成动态逻辑删除sql（hql 方式) ==>> 逻辑删除
     */
    @Override
    @Transactional
    public int deleted(DeleteCriteria deleteCriteria) {
        int result = this.getDao().deleted(deleteCriteria);
        return result;
    }


    @Override
    public Boolean deleteInBatch(List<T> ts) {
        try {
            getDao().deleteInBatch(ts);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * TODO 分页，排序，条件搜索
     * 支持时间段，模糊，各种条件，分页(page,size)，排序（sort）等查询
     * <p>
     * sort 参数说明：
     * -----排序规则：ASC,DESC
     * -----单字段排序：        Sort sort = new Sort(Sort.Direction.ASC, "id");
     * -----多字段排序同规则：   Sort sort = new Sort(Sort.Direction.ASC, "id","openId");
     * -----多字段排序不同规则： Sort sort = new Sort(Sort.Direction.ASC, "id").and(new Sort(Sort.Direction.DESC, "openId"));
     * <p>
     * param 参数外层 Map说明：
     * ----- key = equal                精准搜索 字符串/数字/时间
     * ----- key = like                 模糊搜索 字符串/数字/时间
     * ----- key = between              两者之间， key ：字段名，start：开始 , ent :结束  (数字/时间)
     * ----- key = greaterThanOrEqualTo 大于或等于传入值（字符串/数字/时间）
     * ----- key = lessThanOrEqualTo    小于或等于传入值（字符串/数字/时间）
     * ----- key = greaterThan          大于传入值（字符串/数字/时间）
     * ----- key = lessThan             小于传入值（字符串/数字/时间）
     * <p>
     * param 内参数内层 Map 说明：
     * ----- key = 查询字段，对应实体类字段
     * ----- value = 查询条件，值为null，为空串（""）时排除该查询条件，查询所有或满足其他条件的
     * <p>
     * <p>
     * 两者之间 between 查询说明， 外层Map key = between
     * ----- 支持时间段查询
     * ----- 支持数字范围查询
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
    public Page<T> page(IPage iPage, QueryCriteria queryCriteria) {
        Sort sort = queryCriteria.getSort();
        Map<String, Map<String, Object>> param = queryCriteria.build();
        //
        if (sort != null && param.size() > 0) {
            //分页+排序+条件搜索
            return getDao().findAll(this.getSpecification(param), PageRequest.of(iPage.getPage() - 1, iPage.getLimit(), sort));
        } else if (sort != null) {
            //分页+排序
            return getDao().findAll(PageRequest.of(iPage.getPage() - 1, iPage.getLimit(), sort));
        } else if (param.size() > 0) {
            //分页+条件搜索
            return getDao().findAll(this.getSpecification(param), PageRequest.of(iPage.getPage() - 1, iPage.getLimit()));
        } else {
            //只分页
            return getDao().findAll(PageRequest.of(iPage.getPage() - 1, iPage.getLimit()));
        }
    }


    /**
     * TODO Specification搜索条件拼接方法
     *
     * @param param
     * @return void
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/4/3 0003 17:24
     */
    private Specification getSpecification(Map<String, Map<String, Object>> param) {
        return new Specification<T>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                // TODO Auto-generated method stub
                //条件api集
                List<Predicate> list = new ArrayList<>();
                for (String keyType : param.keySet()) {
                    Map<String, Object> queryMap = param.get(keyType);
                    if (keyType.equals("equal")) {
                        //精准搜索条件拼接
                        for (String key : queryMap.keySet()) {
                            if (queryMap.get(key) != null && !"".equals(queryMap.get(key).toString())) {
                                list.add(cb.equal(root.get(key).as(String.class), queryMap.get(key).toString()));
                            }
                        }
                    } else if (keyType.equals("like")) {
                        //模糊搜索条件拼接
                        for (String key : queryMap.keySet()) {
                            if (queryMap.get(key) != null && !"".equals(queryMap.get(key).toString())) {
                                list.add(cb.like(root.get(key).as(String.class), ("%" + queryMap.get(key) + "%").toString()));
                            }
                        }
                    } else if (keyType.equals("between")) {
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
                    } else if (keyType.equals("greaterThanOrEqualTo")) {
                        //大于或等于传入值（支持字符串/数字/时间）
                        for (String key : queryMap.keySet()) {
                            if (queryMap.get(key) != null && !"".equals(queryMap.get(key).toString())) {
                                list.add(cb.greaterThanOrEqualTo(root.get(key).as(String.class), queryMap.get(key).toString()));
                            }
                        }
                    } else if (keyType.equals("lessThanOrEqualTo")) {
                        //小于或等于传入值（支持字符串/数字/时间）
                        for (String key : queryMap.keySet()) {
                            if (queryMap.get(key) != null && !"".equals(queryMap.get(key).toString())) {
                                list.add(cb.lessThanOrEqualTo(root.get(key).as(String.class), queryMap.get(key).toString()));
                            }
                        }
                    } else if (keyType.equals("greaterThan")) {
                        //  大于传入值（支持字符串/数字/时间）
                        for (String key : queryMap.keySet()) {
                            if (queryMap.get(key) != null && !"".equals(queryMap.get(key).toString())) {
                                list.add(cb.greaterThan(root.get(key).as(String.class), queryMap.get(key).toString()));
                            }
                        }
                    } else if (keyType.equals("lessThan")) {
                        //  小于传入值（字符串/数字/时间）
                        for (String key : queryMap.keySet()) {
                            if (queryMap.get(key) != null && !"".equals(queryMap.get(key).toString())) {
                                list.add(cb.lessThan(root.get(key).as(String.class), queryMap.get(key).toString()));
                            }
                        }
                    } else if (keyType.equals("in")) {
                        for (String key : queryMap.keySet()) {
                            if (queryMap.get(key) != null && !"".equals(queryMap.get(key).toString())) {
                                CriteriaBuilder.In<Object> inId = cb.in(root.get(key));
                                List<Object> idList = (List<Object>) queryMap.get(key);
                                idList.forEach(item -> inId.value(item));
                                list.add(inId);
                            }
                        }
                    }

                }
                Predicate[] p = list.toArray(new Predicate[0]);
                return cb.and(p);
            }
        };
    }
}

//    @Override
//    public Page<T> page(int page, int size, Map<String, Object> param) {
//        return getDao().findAll(new Specification<T>() {
//            private static final long serialVersionUID = 1L;
//
//            @Override
//            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//                // TODO Auto-generated method stub
//                List<Predicate> list = new ArrayList<>();
//                for (String key : param.keySet()) {
//                    if (param.get(key) != null && !"".equals(param.get(key).toString())) {
//                        list.add(cb.equal(root.get(key).as(String.class), (param.get(key)).toString()));
//                    }
//                }
//                Predicate[] p = list.toArray(new Predicate[0]);
//                return cb.and(p);
//            }
//        }, PageRequest.of(page - 1, size, sort));
//    }

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





