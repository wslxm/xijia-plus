package com.ws.ldy.manage.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ws.ldy.manage.admin.model.entity.AdminAuthority;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminAuthorityMapper extends BaseMapper<AdminAuthority> {


    /**
     *  根据用户id + 禁用状态(角色+权限被禁用的都不查询), 查询到角色id，通过多个角色id在查询到权限id，在获取当前用户的所有权限数据
     *
     * @param userId
     * @return java.util.List<com.ws.ldy.adminconsole.entity.AdminRoleAuth>
     * @date 2019/11/25 0025 11:54
     */
    List<AdminAuthority> findByUserIdaAndDisableFetchAuthority(@Param("userId") String userId, @Param("disable") Integer disable, @Param("state") Integer state);


}
