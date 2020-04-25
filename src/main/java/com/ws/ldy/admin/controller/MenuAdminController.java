package com.ws.ldy.admin.controller;


import com.ws.ldy.admin.enums.MenuRootEnum;
import com.ws.ldy.admin.model.dto.MenuAdminDto;
import com.ws.ldy.admin.model.entity.MenuAdmin;
import com.ws.ldy.admin.model.entity.UserAdmin;
import com.ws.ldy.admin.model.vo.MenuAdminVo;
import com.ws.ldy.admin.service.impl.MenuAdminServiceImpl;
import com.ws.ldy.base.controller.BaseController;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.config.result.Result;
import com.ws.ldy.config.result.ResultEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Resource
    private MenuAdminServiceImpl menuService;

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ApiOperation("测试")
    public String test(@RequestParam MenuRootEnum rootEnum) {
        System.out.println(rootEnum);
        return "1";
    }
    @RequestMapping(value = "/test1", method = RequestMethod.POST)
    @ApiOperation("测试1")
    public String test1(@RequestBody MenuAdminDto menuAdminDto) {
        System.out.println(menuAdminDto.getRoot());
        return "1";
    }

    @RequestMapping(value = "/menuTree", method = RequestMethod.GET)
    @ApiOperation("左导航菜单 ===>>> 树结构数据 ===>>> 需先登录")
    public Result<List<MenuAdminVo>> menuTree() {
        //当前登录用户信息
        UserAdmin userAdmin = this.getUserAdmin();
        //获取菜单
        List<MenuAdminVo> menuTree = menuService.getMenuTree(userAdmin);
        return successFind(menuTree);
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation("菜单列表 ==>>>  列表数据  ==>>>  所有")
    public Result<List<MenuAdminVo>> list() {
        List<MenuAdmin> menus = menuService.list();
        return successFind(listVo(menus, MenuAdminVo.class));
    }

    /**
     * @param id     父id
     * @param roleId 角色Id，判断当前是否有权限并选中
     */
    @RequestMapping(value = "/findPidOrRoleIdList", method = RequestMethod.GET)
    @ApiOperation("根据pid +角色Id获取菜单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "父id", required = false, paramType = "query"),
            @ApiImplicitParam(name = "roleId", value = "角色Id，判断当前是否有权限并选中", required = false, paramType = "query")
    })
    public Result<List<MenuAdminVo>> findPidOrRoleIdList(Integer id, Integer roleId) {
        List<MenuAdminVo> menus = menuService.findIdOrRoleIdList(id, roleId);
        return successFind(listVo(menus, MenuAdminVo.class));
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation("菜单添加")
    public Result<Void> insert(@RequestBody MenuAdminDto menuAdminDto) {
        MenuAdmin menuAdmin = menuAdminDto.convert(MenuAdmin.class);
        menuService.save(menuAdmin);
        return successInsert();
    }


    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ApiOperation("编辑")
    public Result<Void> update(@RequestBody MenuAdminDto menuAdminDto) {
        if (menuAdminDto.getId() == null) {
            throw new ErrorException(ResultEnum.ADMIN_IS_NO_UPDATE_ID);
        }
        MenuAdmin menuAdmin = menuAdminDto.convert(MenuAdmin.class);
        menuService.updateById(menuAdmin);
        return successUpdate();
    }


    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ApiOperation("ID删除菜单+所有子菜单")
    public Result<List<Integer>> delete(@RequestParam Integer id) {
        List<MenuAdminVo> idOrRoleIdList = menuService.findIdOrRoleIdList(id);
        List<Integer> menuIds = new ArrayList<>();
        idOrRoleIdList.forEach(item -> menuIds.add(item.getId()));
        menuService.removeByIds(menuIds);
        return successDelete(menuIds);
    }


}
