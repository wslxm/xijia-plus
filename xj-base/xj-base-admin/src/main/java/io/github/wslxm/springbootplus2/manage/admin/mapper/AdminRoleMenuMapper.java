package io.github.wslxm.springbootplus2.manage.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.wslxm.springbootplus2.manage.admin.model.entity.AdminRoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @author wangsong
 */
public interface AdminRoleMenuMapper extends BaseMapper<AdminRoleMenu> {

    /**
     *   查询指定角色所有菜单id
     *
     * @param roleId
     * @return java.util.List<io.github.wslxm.adminconsole.entity.AdminRoleMenu>
     * @date 2019/11/16 0016 23:00
     */
    List<AdminRoleMenu> findRoleId(@Param("roleId") String roleId);



    /**
     * 查询用户所有角色(非禁用角色)，在查询角色下所有菜单Id
     *
     * @param userId userId
     * @param disable disable
     * @return java.util.List<io.github.wslxm.springbootplus2.manage.admin.model.entity.AdminRoleMenu>
     * @version 1.0.0
     */
    List<AdminRoleMenu> findByUserIdAndDisableFetchMenu(@Param("userId") String userId, @Param("disable") Integer disable);


}
