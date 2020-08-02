package com.ws.ldy.modules.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ws.ldy.modules.admin.model.entity.RoleAuthAdmin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleAuthAdminMapper extends BaseMapper<RoleAuthAdmin> {
    /**
     *     查询用户所有角色，在查询角色下所有URL权限
     *
     * @param userId
     * @return java.util.List<com.ws.ldy.adminconsole.entity.RoleMenuAdmin>
     * @date 2019/11/16 0016 23:00
     */
    @Select(value = "SELECT * FROM t_admin_role_auth where role_id in(SELECT role_id FROM t_admin_role_user where user_id=#{userId})")
    List<RoleAuthAdmin> findUserIdRoleAuthority(@Param("userId") String userId);

    /**
     *     查询角色所有Url权限
     *
     * @param roleId
     * @return java.util.List<com.ws.ldy.adminconsole.entity.RoleMenuAdmin>
     * @date 2019/11/16 0016 23:00
     */
    @Select(value = "select * from t_admin_role_auth where role_id = #{roleId}")
    List<RoleAuthAdmin> findRoleId(@Param("roleId") String roleId);
}
