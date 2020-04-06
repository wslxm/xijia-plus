package com.ws.ldy.admin.controller;


import com.ws.ldy.admin.entity.MenuAdmin;
import com.ws.ldy.admin.service.impl.MenuAdminServiceImpl;
import com.ws.ldy.admin.vo.MenuAdminVo;
import com.ws.ldy.base.controller.BaseController;
import com.ws.ldy.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
@Api(value = "MenuAdminController", tags = "菜单管理")
public class MenuAdminController extends BaseController {

    @Autowired
    private MenuAdminServiceImpl menuService;

    @RequestMapping(value = "/menuTree", method = RequestMethod.GET)
    @ApiOperation("左导航菜单 ===>>> 树结构数据 ===>>> 需先登录才能获取")
    public Result<List<MenuAdminVo>> menuTree() {
        List<MenuAdminVo> menuTree = menuService.getMenuTree(this.getUserAdmin());
        return success(menuTree);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation("菜单列表 ==>>>  列表数据  ==>>>  所有")
    public Result<List<MenuAdminVo>> list() {
        List<MenuAdmin> menus = menuService.list();
        return success(listVo(menus, MenuAdminVo.class));
    }

    /**
     * @param id     父id
     * @param roleId 角色Id，判断当前是否有权限并选中
     */
    @RequestMapping(value = "/findPidOrRoleIdList", method = RequestMethod.GET)
    @ApiOperation("获取菜单列表查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "父id", required = false, paramType = "query"),
            @ApiImplicitParam(name = "roleId", value = "角色Id，判断当前是否有权限并选中", required = false, paramType = "query")
    })
    public Result<List<MenuAdminVo>> findPidOrRoleIdList(Integer id, Integer roleId) {
        List<MenuAdminVo> menus = menuService.findIdOrRoleIdList(id, roleId);
        return success(listVo(menus, MenuAdminVo.class));
    }


    @RequestMapping(value = "/save/{type}", method = RequestMethod.POST)
    @ApiOperation("菜单添加 type=1：添加系统 2：一级菜单 3：二级菜单 4：页面")
    public Result<Void> save(String name, Integer pid, String url, String icon, @PathVariable Integer type) {
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
        menuService.save(menu);
        return success();
    }


    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ApiOperation("Id删除")
    public Result<Void> delete(Integer id) {
        menuService.deleteById(id);
        return success();
    }


    @RequestMapping(value = "/update/{type}", method = RequestMethod.PUT)
    @ApiOperation("Id修改---type = 1，修改排序  2，修改图标  3、修改菜单url， 4、修改权限id  5、修改菜单名")
    public Result<Void> update(@PathVariable Integer type, Integer id, String val) {
        MenuAdmin menu = menuService.get(id);
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
        menuService.save(menu);
        return success();
    }
}
