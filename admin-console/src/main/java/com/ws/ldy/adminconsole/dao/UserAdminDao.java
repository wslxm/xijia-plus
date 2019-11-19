package com.ws.ldy.adminconsole.dao;

import com.ws.ldy.adminconsole.entity.UserAdmin;
import com.ws.ldy.admincore.dao.BaseDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAdminDao extends BaseDao<UserAdmin, Integer> {

    /***
     * TODO  根据账号密码查询用户信息
 * @param account
 * @param password
     * @date 2019/11/18 10:29
     * @return com.ws.ldy.adminconsole.entity.UserAdmin
     */
    @Query(value = "select * from t_admin_user where account=?1 and password=?2", nativeQuery = true)
    public UserAdmin findAccountPwd(String account, String password);
}
