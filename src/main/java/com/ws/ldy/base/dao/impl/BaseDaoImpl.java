package com.ws.ldy.base.dao.impl;

import com.ws.ldy.base.dao.BaseDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import java.io.Serializable;

@NoRepositoryBean
public class BaseDaoImpl<T, ID extends Serializable> extends SimpleJpaRepository<T,ID> implements BaseDao<T,ID> {

    /**
     * TODO  获取bean的属性,数据库连接url
     * @date  2019/11/20 15:19
     * @return
     */
    @Value("#{dataSource.url}")
    private String jdbcUrl;


    /** HQL 查询使用( 手动写原生查询 sql，复杂查询必备) */
    private final EntityManager entityManager;

    public BaseDaoImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }


    /**
     * TODO  获取当前连接的数据库名称
     * @date  2019/11/20 15:37
     * @return java.lang.String
     */
    public String getDatabaseName(){
        int startIndex= jdbcUrl.lastIndexOf("/" );   //后往前,第一次
        int endIndex = jdbcUrl.lastIndexOf("?" );   //后往前,第一次
        String databaseName = jdbcUrl.substring(startIndex+1,endIndex);
        return databaseName;
    }
}
