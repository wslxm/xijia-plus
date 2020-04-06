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
@SuppressWarnings("ALL")
public interface BaseService<T, ID extends Serializable> { //, Dao extends BaseDao


    /**
     * 查询所有
     */
    List<T> findAll();

    /**
     * 查询所有
     */
    public List<T> list();

    /**
     * 条件查询 （queryCriteria 构造条件）
     */
    public List<T> list(QueryCriteria queryCriteria);

    /**
     * 添加
     */
    T save(T t);

    /**
     * 添加
     */
    Boolean saveAll(List<T> ts);

    /**
     * 修改
     */
    T update(T t);


    /**
     * TODO  生成动态条件修改sql（hql 方式） -->  表名称 SET 列名称 = 新值 WHERE 列名称 = 某值
     */
    int update(UpdateCriteria updateCriteria);

    /**
     * id查询
     */
    T get(ID id);

    /**
     * id删除
     */
    Boolean deleteById(ID id);

    /**
     * id批量删除
     */
    Boolean deleteByIds(ID[] ids);

    /**
     * TODO  生成动态条件删除sql（hql 方式） ===>  删除sql： DELETE FROM 表名称 WHERE 列名称 = 值
     */
    int delete(DeleteCriteria deleteCriteria);

    /**
     * TODO  生成动态条件删除sql（hql 方式） ===>  删除sql： DELETE FROM 表名称 WHERE 列名称 = 值  ==>> 逻辑删除
     */
    public int deleted(DeleteCriteria deleteCriteria);

    /**
     * 对象批量删除
     */
    Boolean deleteInBatch(List<T> ts);

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
    Page<T> page(IPage iPage, QueryCriteria queryCriteria);


    /**
     * 分页+条件+排序查询，如有特殊条件使用 service子类重写该实现方法
     */
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