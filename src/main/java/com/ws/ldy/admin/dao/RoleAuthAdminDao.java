package com.ws.ldy.admin.dao;

import com.ws.ldy.admin.entity.RoleAuthAdmin;
import com.ws.ldy.base.dao.BaseDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleAuthAdminDao extends BaseDao<RoleAuthAdmin, Integer> , QuerydslPredicateExecutor<RoleAuthAdmin> {
    /**
     * TODO    查询用户所有角色，在查询角色下所有URL 权限
     *
     * @param userId
     * @return java.util.List<com.ws.ldy.adminconsole.entity.RoleMenuAdmin>
     * @date 2019/11/16 0016 23:00
     */
    @Query(value = "SELECT * FROM t_admin_role_auth where role_id in(SELECT role_id FROM t_admin_role_user where user_id=?1)", nativeQuery = true)
    List<RoleAuthAdmin> findUserIdRoleAuthority(Integer userId);

    /**
     * TODO    查询角色所有Url权限
     * @param roleId
     * @date  2019/11/16 0016 23:00
     * @return java.util.List<com.ws.ldy.adminconsole.entity.RoleMenuAdmin>
     */
    @Query(value = "select * from t_admin_role_auth where role_id = ?1",nativeQuery = true)
    List<RoleAuthAdmin> findRoleId(Integer roleId);
}
