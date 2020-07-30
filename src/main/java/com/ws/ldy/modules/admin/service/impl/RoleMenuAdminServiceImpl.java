package com.ws.ldy.modules.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.modules.admin.mapper.RoleMenuAdminMapper;
import com.ws.ldy.modules.admin.model.entity.RoleMenuAdmin;
import com.ws.ldy.modules.admin.service.RoleMenuAdminService;
import com.ws.ldy.others.base.service.impl.BaseIServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class RoleMenuAdminServiceImpl extends BaseIServiceImpl<RoleMenuAdminMapper, RoleMenuAdmin> implements RoleMenuAdminService {


    @Override
    public List<RoleMenuAdmin> findRoleId(String roleId) {
        return baseMapper.findRoleId(roleId);
    }


    /**
     * TODO  计算添加（遍历前台数据，查看后台是否存在角色权限，不存在添加）
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
        this.remove(new LambdaQueryWrapper<RoleMenuAdmin>().eq(RoleMenuAdmin::getRoleId, roleId));
        // 添加新权限
        List<RoleMenuAdmin> addRoleMenu = new LinkedList<>();
        if (menuIds != null && menuIds.length > 0) {
            for (int i = 0; i < menuIds.length; i++) {
                addRoleMenu.add(new RoleMenuAdmin(roleId, menuIds[i]));
            }
            this.saveBatch(addRoleMenu, 1024);
        }
    }
}
