package com.ws.ldy.modules.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ws.ldy.modules.admin.model.entity.RoleUserAdmin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleUserAdminMapper extends BaseMapper<RoleUserAdmin> {

    /**
     * TODO    查询角色所有用户
     *
     * @param roleId
     * @return java.util.List<com.ws.ldy.adminconsole.entity.RoleMenuAdmin>
     * @date 2019/11/16 0016 23:00
     */
    @Select(value = "select * from t_admin_role_user where role_id = #{roleId}")
    List<RoleUserAdmin> findRoleId(@Param("roleId") Integer roleId);
}
