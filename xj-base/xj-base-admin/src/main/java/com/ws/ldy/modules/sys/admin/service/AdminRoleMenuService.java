package com.ws.ldy.modules.sys.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.modules.sys.admin.model.entity.AdminRoleMenu;

import java.util.List;

/**
 *   角色+菜单管理
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 15:10
 */
public interface AdminRoleMenuService extends IService<AdminRoleMenu> {


    /**
     * 分配角色菜单
     * @param roleId
     * @param menuId
     * @return
     */
    boolean insert(String roleId, List<String> menuId);
}