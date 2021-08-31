package com.ws.ldy.manage.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ws.ldy.manage.admin.model.entity.AdminUser;
import com.ws.ldy.manage.admin.model.query.AdminUserQuery;
import com.ws.ldy.manage.admin.model.vo.AdminUserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminUserMapper extends BaseMapper<AdminUser> {

    List<AdminUserVO> list(IPage<AdminUserVO> page, AdminUserQuery query,String createUserId);

    /**
     * 根据角色Id查询指定用户信息
     * @author wangsong
     * @param roleId
     * @date 2020/8/9 0009 10:17
     * @return java.util.List<com.ws.ldy.modules.admin.model.entity.AdminUser>
     * @version 1.0.0
     */
    List<AdminUser> findByRoleId(@Param("roleId") String roleId);

}
