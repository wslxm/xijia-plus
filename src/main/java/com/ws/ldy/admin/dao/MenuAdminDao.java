package com.ws.ldy.admin.dao;

import com.ws.ldy.admin.entity.MenuAdmin;
import com.ws.ldy.base.dao.BaseDao;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuAdminDao extends BaseDao<MenuAdmin, Integer>, QuerydslPredicateExecutor<MenuAdmin> {

}
