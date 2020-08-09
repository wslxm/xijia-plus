package com.ws.ldy.modules.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ws.ldy.modules.admin.model.entity.AdminUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AdminUserMapper extends BaseMapper<AdminUser> {




    /**
     * 根据角色Id查询指定用户信息
     * @author wangsong
     * @param roleId
     * @date 2020/8/9 0009 10:17
     * @return java.util.List<com.ws.ldy.modules.admin.model.entity.AdminUser>
     * @version 1.0.0
     */
    @Select(value = "select * from t_admin_user where id in (select user_id from t_admin_role_user where role_id = #{roleId})")
    List<AdminUser> findByRoleId(@Param("roleId") String roleId);
}
