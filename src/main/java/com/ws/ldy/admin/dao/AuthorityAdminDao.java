package com.ws.ldy.admin.dao;

import com.ws.ldy.admin.entity.AuthorityAdmin;
import com.ws.ldy.base.dao.BaseDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityAdminDao extends BaseDao<AuthorityAdmin, Integer>, QuerydslPredicateExecutor<AuthorityAdmin> {

    /**
     * TODO   根据用户id查询到角色，在查询到权限id，在获取使用权限数据
     * @param userId
     * @date  2019/11/25 0025 11:54
     * @return java.util.List<com.ws.ldy.adminconsole.entity.RoleAuthAdmin>
     */
    @Query(value = "SELECT * FROM  t_admin_authority where id in" +
            " (SELECT auth_id FROM t_admin_role_auth  where role_id in (" +
            "SELECT role_id FROM t_admin_role_user where user_id=?1))", nativeQuery = true)
    List<AuthorityAdmin> findUserIdRoleAuthority(Integer userId);
}
