package com.ws.ldy.adminconsole.dao.impl;

import com.ws.ldy.adminconsole.dao.DataBaseDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Repository
public class DataBaseDaoImpl implements DataBaseDao {


    @PersistenceContext
    private EntityManager entityManager;

    /**
     * TODO 查询数据库所有表
     * 指定数据库 hql="select table_name tb from information_schema.tables where table_schema='spring-boot-plus2';";
     *
     * @return java.util.List<java.lang.String>
     * @date 2019/11/20 11:52
     */
    @Override
    public List<String> findTable() {
        //当前数据库
        String hql = ("show tables");
        Query query = entityManager.createNativeQuery(hql);
        return query.getResultList();
    }


    @Override
    public List<Map<String, String>> findTableField(String table) {
        //数据库库名称
        String databaseName = getDatabaseName();
        //当前数据库指定表
        String hql = ("select column_name,data_type,column_comment "
                + " from information_schema.columns "
                + " where table_name = '" + table + "'"
                + " and table_schema='" + databaseName + "'");
        Query query = entityManager.createNativeQuery(hql);
        List<Object[]> resultList = query.getResultList();
        //数据处理
        List<Map<String, String>> resultListMap = new ArrayList<>();
        Map<String, String> resultMap = null;
        for (Object[] obj : resultList) {
            resultMap = new HashMap<>();
            resultMap.put("name", obj[0].toString());
            resultMap.put("type", obj[1].toString());
            resultMap.put("desc", obj[2].toString());
            resultListMap.add(resultMap);
        }
        return resultListMap;
    }


    /** TODO  获取bean的属性,数据库连接url*/
    @Value("#{dataSource.url}")
    private String jdbcUrl;

    /**
     * TODO  获取当前连接的数据库名称
     * @date  2019/11/20 15:37
     * @return java.lang.String
     */
    private String getDatabaseName(){
        int startIndex= jdbcUrl.lastIndexOf("/" );   //后往前,第一次
        int endIndex = jdbcUrl.lastIndexOf("?" );    //后往前,第一次
        String databaseName = jdbcUrl.substring(startIndex+1,endIndex);
        return databaseName;
    }
}
