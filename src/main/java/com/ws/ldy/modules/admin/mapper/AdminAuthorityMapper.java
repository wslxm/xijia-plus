package com.ws.ldy.modules.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ws.ldy.modules.admin.model.entity.AdminAuthority;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AdminAuthorityMapper extends BaseMapper<AdminAuthority> {

    /**
     * 根据用户id查询到角色，在查询到权限id，在获取当前角色所有权限数据
     *
     * @param userId
     * @return java.util.List<com.ws.ldy.adminconsole.entity.AdminRoleAuth>
     * @date 2019/11/25 0025 11:54
     */
    @Select(value = "SELECT * FROM  t_admin_authority where id in" +
            " (SELECT auth_id FROM t_admin_role_auth  where role_id in (" +
            "SELECT role_id FROM t_admin_role_user where user_id=#{userId}))")
    List<AdminAuthority> findUserIdRoleAuthority(@Param("userId") String userId);


    /**
     *  根据用户id查询到角色（未禁用的角色），在查询到权限id，在获取当前角色所有权限数据
     *
     * @param userId
     * @return java.util.List<com.ws.ldy.adminconsole.entity.AdminRoleAuth>
     * @date 2019/11/25 0025 11:54
     */
    @Select(value = "SELECT * FROM  t_admin_authority where id in" +
            " (SELECT auth_id FROM t_admin_role_auth  where role_id in (" +
            "SELECT role_id FROM t_admin_role_user ru,t_admin_role r where ru.role_id=r.id and disable=0 and user_id=#{userId}))")
    List<AdminAuthority> findUserIdRoleAuthorityNoDisable(@Param("userId") String userId);
}
