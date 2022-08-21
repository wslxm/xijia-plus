package io.github.wslxm.springbootplus2.manage.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.Role;
import io.github.wslxm.springbootplus2.manage.sys.model.query.RoleQuery;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.RoleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @author wangsong
 */
public interface RoleMapper extends BaseMapper<Role> {


    /**
     * 列表查询
     *
     * @param page         page
     * @param query        前端查询条件
     * @param createUserId 当前登录人用户id/角色创建人Id，只查询指定创建人的角色
     * @return java.util.List<io.github.wslxm.springbootplus2.manage.admin.model.vo.RoleVO>
     * @version 1.0.0
     */
    List<RoleVO> list(IPage page,
                           @Param("query") RoleQuery query,
                           @Param("createUserId") String createUserId);

}
