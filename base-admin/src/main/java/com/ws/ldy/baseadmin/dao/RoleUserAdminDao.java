package com.ws.ldy.baseadmin.dao;

import com.ws.ldy.baseadmin.entity.RoleUserAdmin;
import com.ws.ldy.admincore.dao.BaseDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleUserAdminDao extends BaseDao<RoleUserAdmin, Integer> {

    /**
     * TODO    查询角色所有用户
     * @param roleId
     * @date  2019/11/16 0016 23:00
     * @return java.util.List<com.ws.ldy.adminconsole.entity.RoleMenuAdmin>
     */
    @Query(value = "select * from t_admin_role_user where role_id = ?1",nativeQuery = true)
    List<RoleUserAdmin> findRoleId(Integer roleId);
}
