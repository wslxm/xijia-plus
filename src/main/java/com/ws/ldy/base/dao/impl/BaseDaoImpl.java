package com.ws.ldy.base.dao.impl;

import com.ws.ldy.base.dao.BaseDao;
import com.ws.ldy.base.query.DeleteCriteria;
import com.ws.ldy.base.query.UpdateCriteria;
import com.ws.ldy.common.annotation.Delete;
import com.ws.ldy.common.error.ErrorException;
import lombok.SneakyThrows;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;

/**
 * TODO 通用dao 方法, BaseDaoImpl，不能再接口层定义方法，接口层只能定义jpa规范相关的方法，此类对应的方法通过下方说明定义使用
 * <p>
 * hql 方法使用方法：BaseDaoImpl<RoUser,Long> dao = new BaseDaoImpl(RoUser.class,entityManager);
 * hql 方法使用 --> 已定义在通用service层，使用不必关心如何实现，直接调用方法即可
 *
 * @author ws
 * @mail 1720696548@qq.com
 * @date 2020/4/5 0005 19:19
 * @return
 */
@SuppressWarnings("all")
@NoRepositoryBean
public  class BaseDaoImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseDao<T, ID> {

    /**
     * HQL 查询使用( 手动写原生查询 sql，复杂查询必备)
     */
    private final EntityManager entityManager;
    /**
     * 当前调用dao成对应的实体类class
     */
    private Class<T> domainClass;

    public BaseDaoImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.domainClass = domainClass;
        this.entityManager = entityManager;
    }


    /**
     * TODO  生成动态修改sql（hql 方式）  ===>  编辑sql： UPDATE 表名称 SET 列名称 = 新值 WHERE 列名称 = 某值
     *
     * @param updateCriteria T 等于传递调用方法时的任意实体类， domainClass 为service层对应的实体类， 严格意义上：T = domainClass（也不排除不传递对应的实体对象 new UpdateCriteria<DictionaryAdmin>）
     * @return int
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/4/4 0004 22:59
     */
    @SneakyThrows
    public int update(UpdateCriteria<T> updateCriteria) {
        // 获取实体类对应注解的--> mysql关键字(value=注解name值)/ 版本号/ 逻辑删除
        Map<String, Object> converts = this.getConverts();
        Map<String, String> fieldConvert = (Map<String, String>) converts.get("fieldConvert");
        List<String> versionConvert = (List<String>) converts.get("versionConvert");
        List<String> deleteConvert = (List<String>) converts.get("deleteConvert");
        // 修改条件eq
        Map<String, Object> eqs = updateCriteria.getEq();
        // 修改条件in
        Map<String, Object> ins = updateCriteria.getIn();
        // 修改内容
        Map<String, Object> sets = updateCriteria.getSet();
        if ((eqs.size() == 0 && ins.size() == 0) || sets.size() == 0) {
            return 0;
        }
        // 获取实体类类上的Table注解
        Table table = domainClass.getAnnotation(Table.class);
        if (table == null) {
            throw new ErrorException(500, "entity类上没有对应数据库表名的Table注解");
        }
        // 初始化sql +数据库表名
        StringBuffer hql = new StringBuffer("update " + table.name());
        // 添加set 修改内容, getField --> 字符串添加单引号
        int setNum = 0;
        for (String key : sets.keySet()) {
            String newKey = key;
            if (fieldConvert != null && fieldConvert.containsKey(key)) {
                newKey = fieldConvert.get(key);
            }
            if (0 == setNum++) {
                hql.append(" set " + newKey + "=" + getField(sets.get(key)));
            } else {
                hql.append("," + newKey + "=" + getField(sets.get(key)));
            }
        }
        // 添加eq 修改条件
        int updNum = 0;
        for (String key : eqs.keySet()) {
            String newKey = key;
            if (fieldConvert.containsKey(key)) {
                newKey = fieldConvert.get(key);
            }
            if (0 == updNum++) {
                hql.append(" where " + newKey + "=" + getField(eqs.get(key)));
            } else {
                hql.append(" and " + newKey + "=" + getField(eqs.get(key)));
            }
        }
        // 添加in 修改条件
        for (String key : ins.keySet()) {
            String newKey = key;
            if (fieldConvert.containsKey(key)) {
                newKey = fieldConvert.get(key);
            }
            if (0 == updNum++) {
                hql.append(" where " + newKey + " in(" + ins.get(key) + ")");
            } else {
                hql.append(" and " + newKey + " in(" + ins.get(key) + ")");
            }
        }
        Query nativeQuery = entityManager.createNativeQuery(hql.toString());
        System.out.print("=========>>> hql：" + hql.toString());
        int result = nativeQuery.executeUpdate();
        System.out.println("  result：" + result);
        return result;
    }


    /**
     * TODO  生成动态删除sql（hql 方式） ===>  删除sql： DELETE FROM 表名称 WHERE 列名称 = 值
     *
     * @param deleteCriteria
     * @return int
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/4/4 0004 22:59
     */
    @SneakyThrows
    public int delete(DeleteCriteria<T> deleteCriteria) {
        // 拼接数据的sql进行特殊符号转换（mysql关键字）及版本号，逻辑删除做处理
        Map<String, Object> converts = this.getConverts();
        Map<String, String> fieldConvert = (Map<String, String>) converts.get("fieldConvert");
        // 删除条件
        Map<String, Object> eqs = deleteCriteria.getEqs();
        Map<String, Object> ins = deleteCriteria.getIns();
        if (eqs.size() == 0 && ins.size() == 0) {
            return 0;
        }
        // 获取类上的Table注解
        Table table = domainClass.getAnnotation(Table.class);
        if (table == null) {
            throw new ErrorException(500, "entity类上没有对应数据库表名的Table注解");
        }
        // 数据库表名
        String tableName = table.name();
        // sql拼接
        StringBuffer hql = new StringBuffer("delete from " + tableName);
        // 添加eq 删除条件
        int updNum = 0;
        for (String key : eqs.keySet()) {
            String newKey = key;
            if (fieldConvert != null && fieldConvert.containsKey(key)) {
                newKey = fieldConvert.get(key);
            }
            if (0 == updNum++) {
                hql.append(" where " + newKey + "=" + getField(eqs.get(key)));
            } else {
                hql.append(" and " + newKey + "=" + getField(eqs.get(key)));
            }
        }
        // 添加in 删除条件
        for (String key : ins.keySet()) {
            String newKey = key;
            if (fieldConvert.containsKey(key)) {
                newKey = fieldConvert.get(key);
            }
            if (0 == updNum++) {
                hql.append(" where " + newKey + " in(" + ins.get(key) + ")");
            } else {
                hql.append(" and " + newKey + " in(" + ins.get(key) + ")");
            }
        }
        Query nativeQuery = entityManager.createNativeQuery(hql.toString());
        System.out.print("=========>>> hql：" + hql.toString());
        int result = nativeQuery.executeUpdate();
        System.out.println("  result：" + result);
        return result;
    }


    /**
     * TODO  生成动态逻辑删除sql（hql 方式） ===>  逻辑删除sql： UPDATE 表名称 SET 逻辑删除字段=1  WHERE 列名称 = 某值
     *
     * @param deleteCriteria
     * @return int
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/4/4 0004 22:59
     */
    @SneakyThrows
    public int deleted(DeleteCriteria<T> deleteCriteria) {
        // 拼接数据的sql进行特殊符号转换（mysql关键字）及版本号，逻辑删除做处理
        Map<String, Object> converts = this.getConverts();
        Map<String, String> fieldConvert = (Map<String, String>) converts.get("fieldConvert");
        List<String> deleteConvert = (List<String>) converts.get("deleteConvert");
        // 删除条件
        Map<String, Object> eqs = deleteCriteria.getEqs();
        Map<String, Object> ins = deleteCriteria.getIns();
        if (eqs.size() == 0 && ins.size() == 0) {
            return 0;
        }
        // 获取类上的Table注解
        Table table = domainClass.getAnnotation(Table.class);
        if (table == null) {
            throw new ErrorException(500, "entity类上没有对应数据库表名的Table注解");
        }
        // 添加set 修改内容, getField --> 字符串添加单引号
        if (deleteConvert == null) {
            throw new ErrorException(500, "entity类上没有对应的逻辑删除字段 @Delete");
        }
        String deleted = deleteConvert.get(0);
        // 初始化sql +数据库表名
        StringBuffer hql = new StringBuffer("update " + table.name());
        hql.append(" set " + deleted + "=" + 1);
        // 添加eq 删除条件
        int updNum = 0;
        for (String key : eqs.keySet()) {
            String newKey = key;
            if (fieldConvert != null && fieldConvert.containsKey(key)) {
                newKey = fieldConvert.get(key);
            }
            if (0 == updNum++) {
                hql.append(" where " + newKey + "=" + getField(eqs.get(key)));
            } else {
                hql.append(" and " + newKey + "=" + getField(eqs.get(key)));
            }
        }
        // 添加in 删除条件
        for (String key : ins.keySet()) {
            String newKey = key;
            if (fieldConvert.containsKey(key)) {
                newKey = fieldConvert.get(key);
            }
            if (0 == updNum++) {
                hql.append(" where " + newKey + " in(" + ins.get(key) + ")");
            } else {
                hql.append(" and " + newKey + " in(" + ins.get(key) + ")");
            }
        }
        Query nativeQuery = entityManager.createNativeQuery(hql.toString());
        System.out.print("=========>>> hql：" + hql.toString());
        int result = nativeQuery.executeUpdate();
        System.out.println("  result：" + result);
        return result;
    }


    /**
     * TODO  字符串/时间-字段添加双引号
     *
     * @param param
     * @return java.lang.String
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/4/5 0005 1:31
     */
    private String getField(Object param) {
        if (param instanceof Integer) {
            return param + "";
        } else if (param instanceof String) {
            return "'" + param + "'";
        } else if (param instanceof Double) {
            return param + "";
        } else if (param instanceof Float) {
            return param + "";
        } else if (param instanceof Long) {
            return param + "";
        } else if (param instanceof Boolean) {
            return param + "";
        } else if (param instanceof Date) {
            return "'" + param + "'";
        }
        return param + "";
    }


    /**
     * TODO  拼接数据的sql进行特殊符号转换（mysql关键字）及版本号，逻辑删除做处理
     * 版本号乐观锁：https://blog.csdn.net/topdeveloperr/article/details/86496113
     * 逻辑删除参考：https://yq.aliyun.com/articles/653009
     * 逻辑删除参考：https://www.cnblogs.com/watson-ljf/p/10283823.htmlhttps://www.cnblogs.com/watson-ljf/p/10283823.html
     *
     * @return void
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/4/5 0005 20:02
     */
    private Map<String, Object> getConverts() {
        Map<String, Object> converts = new HashMap<>();
        Map<String, String> fieldConvert = null;      // mysql 关键字
        List<String> versionConvert = null;    // 版本号
        List<String> deleteConvert = null;      // 逻辑删除字段(保留字段，暂不开发)
        //Method[] methods = domainClass.getDeclaredMethods();
        //获取本身和第一级的父级对象的所有私有属性
        Field[] fields = (Field[]) ArrayUtils.addAll(domainClass.getDeclaredFields(), domainClass.getSuperclass().getDeclaredFields());
        //循环添加方法级权限
        for (Field field : fields) {
            //  mysql 关键字
            Column column = field.getAnnotation(Column.class);
            if (column != null) {
                if (fieldConvert == null) {
                    fieldConvert = new HashMap<>();
                }
                fieldConvert.put(field.getName(), column.name());
            }
            // 版本号/乐观锁
            Version version = field.getAnnotation(Version.class);
            if (version != null) {
                if (versionConvert == null) {
                    versionConvert = new ArrayList<>();
                }
                versionConvert.add(field.getName());
            }
            // 逻辑删除,定义在属性上的Where 注解，可添加任意where 条件
            Delete where = field.getAnnotation(Delete.class);
            if (where != null) {
                if (deleteConvert == null) {
                    deleteConvert = new ArrayList<>();
                }
                deleteConvert.add(field.getName());
            }
        }
        converts.put("fieldConvert", fieldConvert);
        converts.put("versionConvert", versionConvert);
        converts.put("deleteConvert", deleteConvert);
        return converts;
    }
}
