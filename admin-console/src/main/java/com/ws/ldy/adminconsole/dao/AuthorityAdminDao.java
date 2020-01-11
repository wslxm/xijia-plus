package com.ws.ldy.adminconsole.dao;

import com.ws.ldy.adminconsole.entity.AuthorityAdmin;
import com.ws.ldy.admincore.dao.BaseDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityAdminDao extends BaseDao<AuthorityAdmin, Integer> {

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
