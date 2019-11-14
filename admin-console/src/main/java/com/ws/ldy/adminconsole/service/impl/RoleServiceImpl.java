package com.ws.ldy.adminconsole.service.impl;

import com.ws.ldy.adminconsole.entity.Menu;
import com.ws.ldy.adminconsole.service.MenuService;
import com.ws.ldy.adminconsole.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * TODO   菜单
 * @author 王松
 * @WX-QQ 1720696548
 * @date  2019/11/13 15:42 
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu,Integer> implements MenuService {

    //递归深度
    private final static int  DEPTH = 4;

    @Override
    public List<Menu> getMenuTree(Integer uid) {
        // 保存系统级-顶级菜单返回，root == 1 的
        List<Menu> menuList = new LinkedList<>();
        List<Menu> menus = dao.menuDao.findAll();
        for(Menu menu :  menus){
            if(menu.getRoot() == 1){
                nextLowerNode(menus,menu,menu.getRoot());
                menuList.add(menu);
            }
        }
        return menuList;
    }

    /***
     * TODO    遍历子节点s
     * @param menus 所有节点
     * @param menu  当前菜单节点
     * @param root  递归深度
     * @date  2019/11/13 15:20
     * @return void
     */
    private void nextLowerNode(List<Menu> menus,Menu menu,Integer root){
        List<Menu> menuList = new LinkedList<>();
        for (Menu sonMenu :  menus){
            if(sonMenu.getPid() == menu.getId() && root <= DEPTH){
                //获取子节点
                menuList.add(sonMenu);
                //存在子节点递归遍历获得每个节点下的子节点
                nextLowerNode(menus,sonMenu,root + 1);
            }
        }
         //保存该节点下的子节点ss
        menu.setMenus(menuList);
    }
}