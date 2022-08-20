package io.github.wslxm.springbootplus2.manage.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import io.github.wslxm.springbootplus2.manage.admin.model.entity.AdminRoleMenu;

import java.util.List;

/**
 * 角色+菜单管理
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 15:10
 */
public interface AdminRoleMenuService extends IService<AdminRoleMenu> {


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