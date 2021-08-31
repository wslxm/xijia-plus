package com.ws.ldy.manage.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ws.ldy.manage.admin.model.entity.AdminRole;
import com.ws.ldy.manage.admin.model.query.AdminRoleQuery;
import com.ws.ldy.manage.admin.model.vo.AdminRoleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminRoleMapper extends BaseMapper<AdminRole> {

    /**
     *
     * @param page
     * @param query 前端查询条件
     * @param createUserId 当前登录人用户id/角色创建人Id
     * @return
     */
    List<AdminRoleVO> list(IPage page,
                           @Param("query") AdminRoleQuery query,
                           @Param("createUserId") String createUserId);

}
