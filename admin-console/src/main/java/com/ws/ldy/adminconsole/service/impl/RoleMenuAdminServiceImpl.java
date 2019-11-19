package com.ws.ldy.adminconsole.service.impl;

import com.ws.ldy.adminconsole.entity.MenuAdmin;
import com.ws.ldy.adminconsole.entity.RoleMenuAdmin;
import com.ws.ldy.adminconsole.service.RoleMenuAdminService;
import com.ws.ldy.adminconsole.service.base.BaseAdminServiceImpl;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleMenuAdminServiceImpl extends BaseAdminServiceImpl<RoleMenuAdmin,Integer> implements RoleMenuAdminService {


    @Override
    public List<RoleMenuAdmin> findRoleId(Integer roleId) {
        return dao.roleMenuDao.findRoleId(roleId);
    }

    @Override
    public void roleMenuAuth(Integer roleId, Integer[] menuIds, Integer pid) {
        //计算添加（遍历前台数据，查看后台是否存在角色权限，不存在添加）
        Map<Integer, RoleMenuAdmin> roleMenuMap = new HashMap<>(8); //后台角色菜单数据，判断添加
        Map<Integer, Integer> roleMenuIdMap = new HashMap<>(8);     //前台传入台角色菜单数据，判断删除
        List<RoleMenuAdmin> rms = service.roleMenuServiceImpl.findRoleId(roleId);
        rms.forEach(item -> roleMenuMap.put(item.getMenuId(), item));
        List<RoleMenuAdmin> addRoleMenu = new LinkedList<>();
        if(menuIds != null){
            for (Integer menuId : menuIds){
                if(!roleMenuMap.containsKey(menuId)){
                    addRoleMenu.add( new RoleMenuAdmin(roleId,menuId));
                }
                roleMenuIdMap.put(menuId,roleId);
            }
        }
        //计算删除（遍历后台角色菜单数据，查看前台传入是否存在，不存在删除）
        List<MenuAdmin>  menus = null;
        if(pid == 0){
            //全部菜单
            menus = service.menuServiceImpl.getIdNodeMenu(pid,roleId,1);
        }else{
            //获取指定系统级的菜单
            menus = service.menuServiceImpl.getIdNodeMenu(pid,roleId,2);
        }
        List<RoleMenuAdmin> deleteRoleMenu = new ArrayList<>();
        for (MenuAdmin menu:  menus){
            if(!roleMenuIdMap.containsKey(menu.getId())){
                deleteRoleMenu.add(roleMenuMap.get(menu.getId()));
            }
        }
        //添加菜单权限
        if(addRoleMenu.size() > 0){
            service.roleMenuServiceImpl.saveAll(dao.roleMenuDao, addRoleMenu);
        }
        //删除菜单权限
        if(deleteRoleMenu.size() > 0){
            service.roleMenuServiceImpl.deleteInBatch(dao.roleMenuDao ,deleteRoleMenu);
        }
    }
}
