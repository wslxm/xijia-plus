package com.ws.ldy.adminconsole.dao;

import com.ws.ldy.adminconsole.entity.RoleMenuAdmin;
import com.ws.ldy.admincore.dao.BaseDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMenuAdminDao extends BaseDao<RoleMenuAdmin, Integer> {

    @Query(value = "select * from t_admin_role_menu where role_id = ?1",nativeQuery = true)
    List<RoleMenuAdmin> findRoleId(Integer roleId);
}
