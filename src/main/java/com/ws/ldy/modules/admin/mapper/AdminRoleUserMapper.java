package com.ws.ldy.modules.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ws.ldy.modules.admin.model.entity.AdminRoleUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AdminRoleUserMapper extends BaseMapper<AdminRoleUser> {

    /**
     *     查询角色所有用户
     *
     * @param roleId
     * @return java.util.List<com.ws.ldy.adminconsole.entity.AdminRoleMenu>
     * @date 2019/11/16 0016 23:00
     */
    @Select(value = "select * from t_admin_role_user where role_id = #{roleId}")
    List<AdminRoleUser> findRoleId(@Param("roleId") String roleId);
}
