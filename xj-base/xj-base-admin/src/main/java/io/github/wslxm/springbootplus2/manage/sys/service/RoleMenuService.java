package io.github.wslxm.springbootplus2.manage.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.RoleMenu;

import java.util.List;

/**
 * 角色+菜单管理
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 15:10
 */
public interface RoleMenuService extends IService<RoleMenu> {

    /**
     * 增加角色的菜单
     *
     * @param roleId 角色id
     * @param menuId 菜单id
     * @return boolean
     */
    boolean addRoleMenu(String roleId, String menuId);

    /**
     * 分配角色菜单
     * @param roleId
     * @param menuIds
     * @return boolean
     */
    boolean updRoleMenus(String roleId, List<String> menuIds);


    /**
     * 删除菜单 关联的角色 (批量)
     *
     * @param menuIds
     * @return boolean
     */
    boolean delBatchByMenuIds(List<String> menuIds);


    /**
     * 删除角色 关联的菜单
     *
     * @param roleId
     * @return boolean
     */
    boolean delByRoleId(String roleId);


}