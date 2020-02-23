package com.ws.ldy.baseadmin.controller;


import com.ws.ldy.admincore.common.vo.ResponseData;
import com.ws.ldy.admincore.controller.BaseController;
import com.ws.ldy.baseadmin.entity.MenuAdmin;
import com.ws.ldy.baseadmin.entity.UserAdmin;
import com.ws.ldy.baseadmin.service.MenuAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TODO  菜单
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 13:38
 */
@RestController
@RequestMapping("/menuAdmin")
@Api(tags = {"Admin-Menu"}, description = "菜单管理")
public class MenuAdminController extends BaseController {
    @Autowired
    private MenuAdminService menuServiceImpl;


    @GetMapping("/menuTree")
    @ApiOperation("获取菜单树,左导航菜单")
    public ResponseData menuTree(Integer id) {
        UserAdmin user = (UserAdmin) session.getAttribute("user");
        List<MenuAdmin> menuTree = menuServiceImpl.getMenuTree(user);
        return ResponseData.success(menuTree);

    }


    /**
     * @param type   =1 获取所有  || =2 获取指定父id下的所有
     * @param id     父id
     * @param roleId 角色Id，判断当前是否有权限并选中
     */
    @GetMapping("/findAll/{type}")
    @ApiOperation("获取菜单列表查询")
    public ResponseData findAll(@PathVariable Integer type, Integer id, Integer roleId) {
        List<MenuAdmin> menus = menuServiceImpl.getIdNodeMenu(id, roleId, type);
        return ResponseData.success(menus);
    }

    /**
     * TODO 菜单添加 type=1：添加系统 2：一级菜单 3：二级菜单 4：页面
     */
    @PostMapping("/save/{type}")
    @ApiOperation("菜单添加")
    public ResponseData save(String name, Integer pid, String url, String icon, @PathVariable Integer type) {
        MenuAdmin menu = new MenuAdmin();
        menu.setName(name);    //名称
        //menu.setAuthority(0);  //菜单权限(菜单级别3设置权限Id-权限表对应 ）
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
        menuServiceImpl.save(menu);
        return ResponseData.success("success");
    }


    @PostMapping("/delete")
    @ApiOperation("Id删除")
    public ResponseData delete(Integer id) {
        menuServiceImpl.deleteById(id);
        return ResponseData.success("success");
    }


    /**
     * @param type = 1，修改排序  2，修改图标  3、修改菜单url， 4、修改权限id  5、修改菜单名
     */
    @PostMapping("/update/{type}")
    @ApiOperation("Id修改")
    public ResponseData update(@PathVariable Integer type, Integer id, String val) {
        MenuAdmin menu = menuServiceImpl.get(id);
        if (type == 1) {
            menu.setSort(Integer.parseInt(val));
        } else if (type == 2) {
            menu.setIcon(val);
        } else if (type == 3) {
            menu.setUrl(val);
        } else if (type == 4) {
            // menu.setAuthority(Integer.parseInt(val));
        } else if (type == 5) {
            menu.setName(val);
        }
        menuServiceImpl.save(menu);
        return ResponseData.success("success");
    }
}
