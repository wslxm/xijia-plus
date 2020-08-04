package com.ws.ldy.modules.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.modules.admin.mapper.AdminRoleMenuMapper;
import com.ws.ldy.modules.admin.model.entity.AdminRoleMenu;
import com.ws.ldy.modules.admin.service.AdminRoleMenuService;
import com.ws.ldy.others.base.service.impl.BaseIServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class AdminRoleMenuServiceImpl extends BaseIServiceImpl<AdminRoleMenuMapper, AdminRoleMenu> implements AdminRoleMenuService {


    @Override
    public List<AdminRoleMenu> findRoleId(String roleId) {
        return baseMapper.findRoleId(roleId);
    }


    /**
     *   计算添加（遍历前台数据，查看后台是否存在角色权限，不存在添加）
     *
     * @param roleId
     * @param menuIds
     * @return void
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/4/6 0006 17:47
     */
    @Override
    public void roleMenuAuth(String roleId, String[] menuIds) {
        // 删除当前角色所有菜单权限
        this.remove(new LambdaQueryWrapper<AdminRoleMenu>().eq(AdminRoleMenu::getRoleId, roleId));
        // 添加新权限
        List<AdminRoleMenu> addRoleMenu = new LinkedList<>();
        if (menuIds != null && menuIds.length > 0) {
            for (int i = 0; i < menuIds.length; i++) {
                addRoleMenu.add(new AdminRoleMenu(roleId, menuIds[i]));
            }
            this.saveBatch(addRoleMenu, 1024);
        }
    }
}
