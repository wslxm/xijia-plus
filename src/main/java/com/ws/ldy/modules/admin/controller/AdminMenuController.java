package com.ws.ldy.modules.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.enums.base.BaseConstant;
import com.ws.ldy.modules.admin.model.dto.AdminMenuDTO;
import com.ws.ldy.modules.admin.model.entity.AdminMenu;
import com.ws.ldy.modules.admin.model.vo.AdminMenuVO;
import com.ws.ldy.modules.admin.service.AdminMenuService;
import com.ws.ldy.others.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 *   菜单
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 13:38
 */
@RestController
@RequestMapping("/admin/adminMenu")
@Api(value = "AdminMenuController", tags = "菜单管理", description = BaseConstant.InterfaceType.PC_ADMIN)
public class AdminMenuController extends BaseController<AdminMenuService> {


    @RequestMapping(value = "/findTree", method = RequestMethod.GET)
    @ApiOperation(value = "左导航菜单", notes = "当前用户对应的角色菜单数据，树结构数据,无限级,不限制层次,根据sort字段正序排序,sort越小越靠前")
    public R<List<AdminMenuVO>> menuTree() {
        //获取菜单
        List<AdminMenuVO> menuTree = baseService.getMenuTree();
        return R.successFind(menuTree);
    }


    @RequestMapping(value = "/findList", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有", notes = "根据sort字段正序排序,sort越小越靠前")
    public R<List<AdminMenuVO>> findList() {
        List<AdminMenu> menus = baseService.list(new LambdaQueryWrapper<AdminMenu>()
                .orderByAsc(AdminMenu::getSort)
                .orderByAsc(AdminMenu::getId)
        );
        return R.successFind(BeanDtoVoUtil.listVo(menus, AdminMenuVO.class));
    }

    /**
     * @param id     父id
     * @param roleId 角色Id，判断当前是否有权限并选中 List
     */
    @RequestMapping(value = "/findByPidOrRoleId", method = RequestMethod.GET)
    @ApiOperation(value = "pid + roleId 查询菜单列表", notes = "1、未传递查询所有: isChecked=false || null \r\n 2、根据 pid + roleId 查询当前角色+指定父菜单下的所有菜单给予选中状态 isChecked=true，包括自身, 不在当前 pid 下和 roleId没有权限角色的: isChecked=false || null, 返回List 列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "父id", required = false, paramType = "query"),
            @ApiImplicitParam(name = "roleId", value = "角色Id", required = false, paramType = "query")
    })
    public R<List<AdminMenuVO>> findByPidOrRoleId(String id, String roleId) {
        List<AdminMenuVO> menus = baseService.findPIdOrRoleIdList(id, roleId);
        return R.successFind(BeanDtoVoUtil.listVo(menus, AdminMenuVO.class));
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "菜单添加", notes = "")
    public R<Void> insert(@RequestBody AdminMenuDTO adminMenuDto) {
        baseService.save(adminMenuDto.convert(AdminMenu.class));
        return R.successInsert();
    }


    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    @ApiOperation(value = "ID编辑", notes = "")
    public R<Void> upd(@RequestBody AdminMenuDTO adminMenuDto) {
        baseService.updateById( adminMenuDto.convert(AdminMenu.class));
        return R.successUpdate();
    }


    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    @ApiOperation(value = "ID删除", notes = "同时删除当前菜单和当前菜单下的所有子菜单")
    public R<List<String>> del(@RequestParam String id) {
        List<String> menuIds =  baseService.findPIdOrRoleIdList(id,null).stream().map(i-> i.getId()).collect(Collectors.toList());
        baseService.removeByIds(menuIds);
        return R.successDelete(menuIds);
    }
}
