package com.ws.ldy.modules.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ws.ldy.modules.admin.model.entity.AdminRoleMenu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AdminRoleMenuMapper extends BaseMapper<AdminRoleMenu> {

    /**
     *   查询角色所有菜单权限
     *
     * @param roleId
     * @return java.util.List<com.ws.ldy.adminconsole.entity.AdminRoleMenu>
     * @date 2019/11/16 0016 23:00
     */
    @Select(value = "select * from t_admin_role_menu where role_id = #{roleId}")
    List<AdminRoleMenu> findRoleId(@Param("roleId") String roleId);


    /**
     *     查询用户所有角色，在查询角色下所有菜单权限
     *
     * @param userId
     * @return java.util.List<com.ws.ldy.adminconsole.entity.AdminRoleMenu>
     * @date 2019/11/16 0016 23:00
     */
    @Select(value = "SELECT * FROM t_admin_role_menu where role_id in(SELECT role_id FROM t_admin_role_user where user_id= #{userId})")
    List<AdminRoleMenu> findUserIdRoleMenus(@Param("userId") String userId);


    /**
     *  查询用户所有角色(非禁用角色)，在查询角色下所有菜单权限
     *
     * @param userId
     * @return java.util.List<com.ws.ldy.adminconsole.entity.AdminRoleMenu>
     * @date 2019/11/16 0016 23:00
     */
    @Select(value = "SELECT * FROM t_admin_role_menu where role_id in(SELECT role_id FROM t_admin_role_user ru,t_admin_role r where ru.role_id=r.id and disable=0 and user_id= #{userId})")
    List<AdminRoleMenu> findUserIdRoleMenusNoDisable(@Param("userId") String userId);

}
