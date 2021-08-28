package com.ws.ldy.manage.admin.service.impl;

import com.ws.ldy.manage.admin.mapper.AdminRoleMenuMapper;
import com.ws.ldy.manage.admin.model.entity.AdminRoleMenu;
import com.ws.ldy.manage.admin.service.AdminRoleMenuService;
import com.ws.ldy.core.base.service.impl.BaseIServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminRoleMenuServiceImpl extends BaseIServiceImpl<AdminRoleMenuMapper, AdminRoleMenu> implements AdminRoleMenuService {


    @Override
    public boolean insert(String roleId, List<String> menuIds) {
        if (menuIds != null && menuIds.size() > 0) {
            List<AdminRoleMenu> roleMenus = new ArrayList<>();
            menuIds.forEach(menuId ->{
                roleMenus.add(new AdminRoleMenu(roleId, menuId));
            });
            return this.saveBatch(roleMenus);
        }
        return false;

    }
}
