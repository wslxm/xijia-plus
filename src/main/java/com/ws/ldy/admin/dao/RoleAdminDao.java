package com.ws.ldy.admin.dao;

import com.ws.ldy.admin.entity.RoleAdmin;
import com.ws.ldy.base.dao.BaseDao;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleAdminDao extends BaseDao<RoleAdmin, Integer>, QuerydslPredicateExecutor<RoleAdmin> {

}
