package com.ws.ldy.adminconsole.controller;


import com.ws.ldy.adminconsole.controller.base.BaseAdminController;
import com.ws.ldy.adminconsole.entity.MenuAdmin;
import com.ws.ldy.adminconsole.entity.UserAdmin;
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
@RequestMapping("/menuAdmin")
public class MenuAdminController extends BaseAdminController {

    /**
     * TODO 获取菜单树,左导航菜单
     *
     * @param id
     * @return
     */
    @RequestMapping("/menuTree")
    @ResponseBody
    public List<MenuAdmin> menuTree(Integer id) {
        UserAdmin user = (UserAdmin)session.getAttribute("user");
        List<MenuAdmin> menuTree = service.menuServiceImpl.getMenuTree(user);
        return menuTree;

    }


    /**
     * TODO 菜单列表查询
     *
     * @param type =1 获取所有  || =2 获取指定父id下的所有
     * @param id     父id
     * @param roleId 角色Id，判断当前是否有权限并选中
     * @return
     */
    @RequestMapping("/findAll/{type}")
    @ResponseBody
    public List<MenuAdmin> findAll(@PathVariable Integer type,Integer id,Integer roleId) {
        List<MenuAdmin>  menus = service.menuServiceImpl.getIdNodeMenu(id,roleId,type);
        return menus;
    }

    /**
     * TODO 菜单添加 type=1：添加系统 2：一级菜单 3：二级菜单 4：页面
     *
     * @return
     */
    @RequestMapping("/save/{type}")
    @ResponseBody
    public String save(String name, Integer pid, String url, String icon, @PathVariable Integer type) {
        MenuAdmin menu = new MenuAdmin();
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
        service.menuServiceImpl.deleteById(dao.menuDao, id);
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
    public String update(@PathVariable Integer type, Integer id, String val) {
        MenuAdmin menu = service.menuServiceImpl.get(dao.menuDao, id);
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
        service.menuServiceImpl.save(dao.menuDao, menu);
        return "success";
    }
}
