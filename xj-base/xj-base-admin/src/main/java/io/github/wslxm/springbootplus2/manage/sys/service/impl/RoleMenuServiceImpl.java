package io.github.wslxm.springbootplus2.manage.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseIServiceImpl;
import io.github.wslxm.springbootplus2.manage.sys.mapper.RoleMenuMapper;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.RoleMenu;
import io.github.wslxm.springbootplus2.manage.sys.service.RoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
/**
 * @author wangsong
 */
@Service
public class RoleMenuServiceImpl extends BaseIServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updRoleMenus(String roleId, List<String> menuIds) {
        this.remove(new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getRoleId, roleId));
        if (menuIds == null || menuIds.size() <= 0) {
            return true;
        }
        List<RoleMenu> roleMenus = new ArrayList<>();
        menuIds.forEach(menuId -> roleMenus.add(new RoleMenu(roleId, menuId)));
        return this.saveBatch(roleMenus);
    }


    @Override
    public boolean delBatchByMenuIds(List<String> menuIds) {
        return this.remove(new LambdaQueryWrapper<RoleMenu>().in(RoleMenu::getMenuId, menuIds));
    }

    @Override
    public boolean delByRoleId(String roleId) {
        return this.remove(new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getRoleId, roleId));
    }
}
