package com.ws.lay.adminconsole.controller;


import com.ws.lay.adminconsole.controller.base.BaseContoller;
import com.ws.lay.adminconsole.entity.Menu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * TODO  菜单
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 13:38
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends BaseContoller {


    @RequestMapping("/menu")
    public String menu() {
        return "menu/menu";
    }

    /**
     * 获取菜单树
     * @return
     */
    @RequestMapping("/menuTree")
    @ResponseBody
    public List<Menu> menuTree() {
        List<Menu> menuTree = service.menuServiceImpl.getMenuTree(1);
        return menuTree;
    }


    /**
     * TODO 菜单查询
     *
     * @return
     */
    @RequestMapping("/findAll")
    @ResponseBody
    public List<Menu> findAll() {
        //查询所有
        List<Menu> all = service.menuServiceImpl.findAll(dao.menuDao);
        return all;
    }

    /**
     * TODO 菜单添加 type=1：添加系统 2：一级菜单 3：二级菜单 4：页面
     *
     * @return
     */
    @RequestMapping("/save/{type}")
    @ResponseBody
    public String save(String name, Integer pid, String url, String icon, @PathVariable Integer type) {
        Menu menu = new Menu();
        menu.setName(name);    //名称
        menu.setAuthority(0);  //菜单权限(菜单级别3设置权限Id-权限表对应 ）
        menu.setSort(0);       //排序
        menu.setRoot(type);    //菜单级别(1、根目录,2、子目录, 3、菜单 4、页面
        if (type == 1) {
            //添加系统
            menu.setPid(0);          //父id
            menu.setIcon("");        //图标
            menu.setUrl("");         //url
        } else if (type == 2) {
            //添加一级菜单
            menu.setPid(pid);        //父id
            menu.setIcon(icon);      //图标
            menu.setUrl("");         //url
        } else if (type == 3) {
            //添加二级菜单
            menu.setPid(pid);        //父id
            menu.setIcon(icon);      //图标
            menu.setUrl("");         //url
        } else if (type == 4) {
            //添加页面
            menu.setPid(pid);        //父id
            menu.setIcon("");        //图标
            menu.setUrl(url);         //url
        }
        service.menuServiceImpl.save(dao.menuDao, menu);
        return "success";
    }

    /**
     * TODO 删除
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public String delete(Integer id) {
        service.menuServiceImpl.delete(dao.menuDao, id);
        return "success";
    }


    /**
     * TODO 修改
     *
     * @param type = 1，修改排序  2，修改图标  3、修改菜单url， 4、修改权限id  5、修改菜单名
     * @return
     */
    @ResponseBody
    @RequestMapping("/update/{type}")
    public String update(@PathVariable Integer type, Integer id,String val) {
        Menu menu = service.menuServiceImpl.get(dao.menuDao, id);
        if (type == 1) {
            menu.setSort(Integer.parseInt(val));
        } else if (type == 2) {
            menu.setIcon(val);
        } else if (type == 3) {
            menu.setUrl(val);
        } else if (type == 4) {
            menu.setAuthority(Integer.parseInt(val));
        } else if (type == 5) {
            menu.setName(val);
        }
        service.menuServiceImpl.save(dao.menuDao,menu);
        return "success";
    }
}
