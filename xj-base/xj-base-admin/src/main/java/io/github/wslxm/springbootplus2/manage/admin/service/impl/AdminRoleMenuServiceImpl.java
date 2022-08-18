package io.github.wslxm.springbootplus2.manage.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseIServiceImpl;
import io.github.wslxm.springbootplus2.manage.admin.mapper.AdminRoleMenuMapper;
import io.github.wslxm.springbootplus2.manage.admin.model.entity.AdminRoleMenu;
import io.github.wslxm.springbootplus2.manage.admin.service.AdminRoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
/**
 * @author wangsong
 */
@Service
public class AdminRoleMenuServiceImpl extends BaseIServiceImpl<AdminRoleMenuMapper, AdminRoleMenu> implements AdminRoleMenuService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updRoleMenus(String roleId, List<String> menuIds) {
        this.remove(new LambdaQueryWrapper<AdminRoleMenu>().eq(AdminRoleMenu::getRoleId, roleId));
        if (menuIds == null || menuIds.size() <= 0) {
            return true;
        }
        List<AdminRoleMenu> roleMenus = new ArrayList<>();
        menuIds.forEach(menuId -> roleMenus.add(new AdminRoleMenu(roleId, menuId)));
        return this.saveBatch(roleMenus);
    }


    @Override
    public boolean delBatchByMenuIds(List<String> menuIds) {
        return this.remove(new LambdaQueryWrapper<AdminRoleMenu>().in(AdminRoleMenu::getMenuId, menuIds));
    }

    @Override
    public boolean delByRoleId(String roleId) {
        return this.remove(new LambdaQueryWrapper<AdminRoleMenu>().eq(AdminRoleMenu::getRoleId, roleId));
    }
}
