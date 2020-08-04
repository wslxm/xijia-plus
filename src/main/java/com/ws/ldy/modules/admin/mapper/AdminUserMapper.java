package com.ws.ldy.modules.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ws.ldy.modules.admin.model.entity.AdminUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface AdminUserMapper extends BaseMapper<AdminUser> {

    /**
     *   根据账号查询用户信息
     *
     * @param account
     * @return com.ws.ldy.adminconsole.entity.AdminUser
     * @date 2019/11/18 10:29
     */
    @Select(value = "select * from t_admin_user where account=#{account}")
    public AdminUser findByAccount(@Param("account") String account);
}
