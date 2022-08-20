package io.github.wslxm.springbootplus2.manage.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.wslxm.springbootplus2.manage.admin.model.entity.AdminAuthority;
import io.github.wslxm.springbootplus2.manage.admin.model.query.AuthorityByUserIdQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wangsong
 */
public interface AdminAuthorityMapper extends BaseMapper<AdminAuthority> {


    /**
     *  根据用户id + 禁用状态(角色+权限被禁用的都不查询), 查询到角色id，通过多个角色id在查询到权限id，在获取当前用户的所有权限数据
     *
     * @return java.util.List<io.github.wslxm.adminconsole.entity.AdminRoleAuth>
     * @date 2019/11/25 0025 11:54
     */
    List<AdminAuthority> list(@Param("query") AuthorityByUserIdQuery query);


}
