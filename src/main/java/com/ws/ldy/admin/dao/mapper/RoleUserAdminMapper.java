package com.ws.ldy.admin.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ws.ldy.admin.model.entity.RoleUserAdmin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//@Mapper
public interface RoleUserAdminMapper extends BaseMapper<RoleUserAdmin> {

    /**
     * TODO    查询角色所有用户
     * @param roleId
     * @date  2019/11/16 0016 23:00
     * @return java.util.List<com.ws.ldy.adminconsole.entity.RoleMenuAdmin>
     */
    @Select(value = "select * from t_admin_role_user where role_id = ${roleId}")
    List<RoleUserAdmin> findRoleId(@Param("roleId") Integer roleId);
}
