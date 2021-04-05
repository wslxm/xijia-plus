package com.ws.ldy.modules.sys.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ws.ldy.modules.sys.admin.mapper.AdminRoleMenuMapper;
import com.ws.ldy.modules.sys.admin.model.entity.AdminRoleMenu;
import com.ws.ldy.modules.sys.admin.service.AdminRoleMenuService;
import com.ws.ldy.modules.sys.base.service.impl.BaseIServiceImpl;
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
