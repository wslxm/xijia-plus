package com.ws.ldy.admin.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ws.ldy.admin.model.entity.UserAdmin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

//@Mapper
public interface UserAdminMapper extends BaseMapper<UserAdmin> {

    /**
     * TODO  根据账号查询用户信息
     *
     * @param account
     * @return com.ws.ldy.adminconsole.entity.UserAdmin
     * @date 2019/11/18 10:29
     */
    @Select(value = "select * from t_admin_user where account=${account}")
    public UserAdmin findByAccount(@Param("account") String account);
}
