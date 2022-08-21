package io.github.wslxm.springbootplus2.manage.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.RoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @author wangsong
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    /**
     *   查询指定角色所有菜单id
     *
     * @param roleId
     * @return java.util.List<io.github.wslxm.adminconsole.entity.RoleMenu>
     * @date 2019/11/16 0016 23:00
     */
    List<RoleMenu> findRoleId(@Param("roleId") String roleId);



    /**
     * 查询用户所有角色(非禁用角色)，在查询角色下所有菜单Id
     *
     * @param userId userId
     * @param disable disable
     * @return java.util.List<io.github.wslxm.springbootplus2.manage.admin.model.entity.RoleMenu>
     * @version 1.0.0
     */
    List<RoleMenu> findByUserIdAndDisableFetchMenu(@Param("userId") String userId, @Param("disable") Integer disable);


}
