package com.ws.ldy.adminconsole.service.impl;

import com.ws.ldy.adminconsole.dao.MenuAdminDao;
import com.ws.ldy.adminconsole.dao.RoleMenuAdminDao;
import com.ws.ldy.adminconsole.entity.MenuAdmin;
import com.ws.ldy.adminconsole.entity.RoleMenuAdmin;
import com.ws.ldy.adminconsole.entity.UserAdmin;
import com.ws.ldy.adminconsole.service.MenuAdminService;
import com.ws.ldy.admincore.service.impl.BaseServiceApiImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@Service
public class MenuAdminServiceImpl extends BaseServiceApiImpl<MenuAdmin, Integer> implements MenuAdminService {

    @Autowired
    private RoleMenuAdminDao roleMenuDao;

    @Autowired
    private MenuAdminDao menuDao;
    /**
     * =========================================================================
     * ========================   导航树结构菜单查询处理  ======================
     * =========================================================================
     */
    //递归深度
    private final static int DEPTH = 4;

    @Override
    public List<MenuAdmin> getMenuTree(UserAdmin user) {
        //查询用户当前的角色下的单Id
        Map<Integer, Integer> roleMenuMap = new HashMap<>(8);
        List<RoleMenuAdmin> roleMenus = roleMenuDao.findUserIdRoleMenus(user.getId());
        roleMenus.forEach(item -> roleMenuMap.put(item.getMenuId(), 0));
        // 保存系统级-顶级菜单返回，root == 1 的
        List<MenuAdmin> menuList = new LinkedList<>();
        List<MenuAdmin> menus = menuDao.findAll();
        for (MenuAdmin menu : menus) {
            if (menu.getRoot() == 1 && roleMenuMap.containsKey(menu.getId())) {
                nextLowerNode(menus, menu, menu.getRoot(),roleMenuMap);
                menuList.add(menu);
            }
        }
        return menuList;
    }

    /***
     * TODO    菜单数遍历子节点(递归遍历)
     * @param menus 所有节点
     * @param menu  当前菜单节点
     * @param root  递归深度
     * @param roleMenuMap  当前用户存在的菜单权限
     * @date 2019/11/13 15:20
     * @return void
     */
    private void nextLowerNode(List<MenuAdmin> menus, MenuAdmin menu, Integer root, Map<Integer, Integer> roleMenuMap) {
        List<MenuAdmin> menuList = new LinkedList<>();
        for (MenuAdmin sonMenu : menus) {
            if (sonMenu.getPid() == menu.getId()  && roleMenuMap.containsKey(sonMenu.getId()) && root <= DEPTH) {
                //获取子节点
                menuList.add(sonMenu);
                //存在子节点递归遍历获得每个节点下的子节点
                nextLowerNode(menus, sonMenu, root + 1, roleMenuMap);
            }
        }
        //保存该节点下的子节点ss
        menu.setMenus(menuList);
    }

    /**
     * =========================================================================
     * ========================   角色分配数据处理    ==========================
     * =========================================================================
     */
    @Override
    public List<MenuAdmin> getIdNodeMenu(Integer id, Integer roleId, Integer type) {
        List<MenuAdmin> menuList = new LinkedList<>();
        List<MenuAdmin> menus = menuDao.findAll();
        //获得角色数据
        Map<Integer, Integer> mapMenu = new HashMap<>();
        List<RoleMenuAdmin> roleMenus = roleMenuDao.findRoleId(roleId);
        roleMenus.forEach(item -> mapMenu.put(item.getMenuId(), item.getMenuId()));
        if (type == 1) {
            for (MenuAdmin menu : menus) {
                //权限判断设置选中状态
                setChecked(menu, mapMenu);
                menuList.add(menu);
            }
        } else {
            for (MenuAdmin menu : menus) {
                if (menu.getId() == id) {
                    //权限判断设置选中状态
                    setChecked(menu, mapMenu);
                    menuList.add(menu);
                    nextLowerIdNode(menus, menuList, menu, mapMenu);
                }
            }
        }
        return menuList;
    }

    /**
     * TODO    获取指定父节点下的子节点(递归遍历)
     *
     * @param menus    所有节点
     * @param menu     当前节点
     * @param menuList 接收保存子节点数据
     * @return void
     * @date 2019/11/13 15:20
     */
    private void nextLowerIdNode(List<MenuAdmin> menus, List<MenuAdmin> menuList, MenuAdmin menu, Map<Integer, Integer> mapMenu) {
        for (MenuAdmin sonMenu : menus) {
            if (sonMenu.getPid() == menu.getId()) {
                //权限判断设置选中状态
                setChecked(sonMenu, mapMenu);
                //获取子节点
                menuList.add(sonMenu);
                //存在子节点递归遍历获得每个节点下的子节点
                nextLowerIdNode(menus, menuList, sonMenu, mapMenu);
            }
        }
    }

    /**
     * 判断并设置为选中状态
     */
    private void setChecked(MenuAdmin menu, Map<Integer, Integer> mapMenu) {
        //权限判断设置选中状态
        if (mapMenu.containsKey(menu.getId())) {
            menu.setLAY_CHECKED(true);
        }
    }
}