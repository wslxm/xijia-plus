package com.ws.ldy.modules.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ws.ldy.modules.admin.model.entity.RoleMenuAdmin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMenuAdminMapper extends BaseMapper<RoleMenuAdmin> {

    /**
     * TODO    查询角色所有权限
     *
     * @param roleId
     * @return java.util.List<com.ws.ldy.adminconsole.entity.RoleMenuAdmin>
     * @date 2019/11/16 0016 23:00
     */
    @Select(value = "select * from t_admin_role_menu where role_id = #{roleId}")
    List<RoleMenuAdmin> findRoleId(@Param("roleId") Integer roleId);


    /**
     * TODO    查询用户所有角色，在查询角色下所有菜单id
     *
     * @param userId
     * @return java.util.List<com.ws.ldy.adminconsole.entity.RoleMenuAdmin>
     * @date 2019/11/16 0016 23:00
     */
    @Select(value = "SELECT * FROM t_admin_role_menu where role_id in(SELECT role_id FROM t_admin_role_user where user_id= #{userId})")
    List<RoleMenuAdmin> findUserIdRoleMenus(@Param("userId") Integer userId);

}
