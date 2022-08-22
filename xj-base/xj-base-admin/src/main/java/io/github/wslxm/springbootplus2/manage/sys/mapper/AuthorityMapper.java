package io.github.wslxm.springbootplus2.manage.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.Authority;
import io.github.wslxm.springbootplus2.manage.sys.model.query.AuthorityByUserIdQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wangsong
 */
public interface AuthorityMapper extends BaseMapper<Authority> {


    /**
     *  根据用户id + 禁用状态(角色+权限被禁用的都不查询), 查询到角色id，通过多个角色id在查询到权限id，在获取当前用户的所有权限数据
     *
     * @param query
     * @return java.util.List<io.github.wslxm.adminconsole.entity.RoleAuth>
     * @date 2019/11/25 0025 11:54
     */
    List<Authority> list(@Param("query") AuthorityByUserIdQuery query);


}
