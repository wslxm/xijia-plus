package com.ws.ldy.admincore.dao.impl;

import com.ws.ldy.admincore.dao.BaseDao;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import java.io.Serializable;

@NoRepositoryBean
public class BaseDaoImpl<T, ID extends Serializable> extends SimpleJpaRepository<T,ID> implements BaseDao<T,ID> {

    /** HQL 查询使用( 手动写原生查询 sql，复杂查询必备) */
    private final EntityManager entityManager;

    public BaseDaoImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }

    /** 扩展jpa未提供的方法 */
    public String testHQL(){
        return "testHQL";
    }
}
