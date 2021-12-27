package io.github.wslxm.springbootplus2.manage.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.wslxm.springbootplus2.manage.admin.model.entity.AdminRole;
import io.github.wslxm.springbootplus2.manage.admin.model.query.AdminRoleQuery;
import io.github.wslxm.springbootplus2.manage.admin.model.vo.AdminRoleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @author wangsong
 */
public interface AdminRoleMapper extends BaseMapper<AdminRole> {


    /**
     * 列表查询
     *
     * @param page         page
     * @param query        前端查询条件
     * @param createUserId 当前登录人用户id/角色创建人Id
     * @return java.util.List<io.github.wslxm.springbootplus2.manage.admin.model.vo.AdminRoleVO>
     * @version 1.0.0
     */
    List<AdminRoleVO> list(IPage page,
                           @Param("query") AdminRoleQuery query,
                           @Param("createUserId") String createUserId);

}
