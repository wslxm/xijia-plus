package com.ws.ldy.admin.dao;

import com.ws.ldy.admin.entity.RoleMenuAdmin;
import com.ws.ldy.base.dao.BaseDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMenuAdminDao extends BaseDao<RoleMenuAdmin, Integer> {

    /**
     * TODO    查询角色所有权限
     *
     * @param roleId
     * @return java.util.List<com.ws.ldy.adminconsole.entity.RoleMenuAdmin>
     * @date 2019/11/16 0016 23:00
     */
    @Query(value = "select * from t_admin_role_menu where role_id = ?1", nativeQuery = true)
    List<RoleMenuAdmin> findRoleId(Integer roleId);


    /**
     * TODO    查询用户所有角色，在查询角色下所有菜单id
     *
     * @param userId
     * @return java.util.List<com.ws.ldy.adminconsole.entity.RoleMenuAdmin>
     * @date 2019/11/16 0016 23:00
     */
    @Query(value = "SELECT * FROM t_admin_role_menu where role_id in(SELECT role_id FROM t_admin_role_user where user_id=?1)", nativeQuery = true)
    List<RoleMenuAdmin> findUserIdRoleMenus(Integer userId);

}
