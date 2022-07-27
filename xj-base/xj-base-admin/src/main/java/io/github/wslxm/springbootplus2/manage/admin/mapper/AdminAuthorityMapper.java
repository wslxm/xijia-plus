package io.github.wslxm.springbootplus2.manage.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.wslxm.springbootplus2.manage.admin.model.entity.AdminAuthority;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wangsong
 */
public interface AdminAuthorityMapper extends BaseMapper<AdminAuthority> {


    /**
     *  根据用户id + 禁用状态(角色+权限被禁用的都不查询), 查询到角色id，通过多个角色id在查询到权限id，在获取当前用户的所有权限数据
     *
     * @param userId  用户id查询当前存在权限
     * @param disable 是否查询禁用数据
     * @param state  是否只查询需登录/需登录+授权等设计
     * @param pid  pid
     * @param type  type
     * @return java.util.List<io.github.wslxm.adminconsole.entity.AdminRoleAuth>
     * @date 2019/11/25 0025 11:54
     */
    List<AdminAuthority> findByUserIdAuthority(@Param("userId") String userId,
                                               @Param("pid") String pid ,
                                               @Param("type") Integer type ,
                                               @Param("state") Integer state,
                                               @Param("disable") Integer disable,
                                               @Param("asc") String asc,
                                               @Param("desc") String desc

    );


}
