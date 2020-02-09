package com.ws.ldy.baseadmin.service.impl;

import com.ws.ldy.baseadmin.dao.RoleMenuAdminDao;
import com.ws.ldy.baseadmin.entity.MenuAdmin;
import com.ws.ldy.baseadmin.entity.RoleMenuAdmin;
import com.ws.ldy.baseadmin.service.RoleMenuAdminService;
import com.ws.ldy.admincore.service.impl.BaseServiceApiImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleMenuAdminServiceImpl extends BaseServiceApiImpl<RoleMenuAdmin,Integer> implements RoleMenuAdminService {


    @Autowired
    private RoleMenuAdminDao roleMenuAdminDao;
    @Autowired
    private MenuAdminServiceImpl menuAdminServiceImpl;
    @Override
    public List<RoleMenuAdmin> findRoleId(Integer roleId) {
        return roleMenuAdminDao.findRoleId(roleId);
    }

    @Override
    public void roleMenuAuth(Integer roleId, Integer[] menuIds, Integer pid) {
        //计算添加（遍历前台数据，查看后台是否存在角色权限，不存在添加）
        Map<Integer, RoleMenuAdmin> roleMenuMap = new HashMap<>(8); //后台角色菜单数据，判断添加
        Map<Integer, Integer> roleMenuIdMap = new HashMap<>(8);     //前台传入台角色菜单数据，判断删除
        List<RoleMenuAdmin> rms = roleMenuAdminDao.findRoleId(roleId);
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
            menus = menuAdminServiceImpl.getIdNodeMenu(pid,roleId,1);
        }else{
            //获取指定系统级的菜单
            menus = menuAdminServiceImpl.getIdNodeMenu(pid,roleId,2);
        }
        List<RoleMenuAdmin> deleteRoleMenu = new ArrayList<>();
        for (MenuAdmin menu:  menus){
            if(!roleMenuIdMap.containsKey(menu.getId())){
                deleteRoleMenu.add(roleMenuMap.get(menu.getId()));
            }
        }
        //添加菜单权限
        if(addRoleMenu.size() > 0){
            roleMenuAdminDao.saveAll( addRoleMenu);
        }
        //删除菜单权限
        if(deleteRoleMenu.size() > 0){
            roleMenuAdminDao.deleteInBatch(deleteRoleMenu);
        }
    }
}
