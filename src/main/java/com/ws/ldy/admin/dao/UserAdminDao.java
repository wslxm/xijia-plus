package com.ws.ldy.admin.dao;

import com.ws.ldy.admin.entity.UserAdmin;
import com.ws.ldy.base.dao.BaseDao;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAdminDao extends BaseDao<UserAdmin, Integer> , QuerydslPredicateExecutor<UserAdmin> {

    /**
     * TODO  根据账号查询用户信息
     *
     * @param account
     * @return com.ws.ldy.adminconsole.entity.UserAdmin
     * @date 2019/11/18 10:29
     */
    //@Query(value = "select * from t_admin_user where account=?1 and password=?2", nativeQuery = true)
    public UserAdmin findByAccount(String account);
}
