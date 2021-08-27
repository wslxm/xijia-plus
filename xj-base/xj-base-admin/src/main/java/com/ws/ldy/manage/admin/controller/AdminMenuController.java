package com.ws.ldy.manage.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ws.ldy.core.base.controller.BaseController;
import com.ws.ldy.core.constant.BaseConstant;
import com.ws.ldy.core.result.R;
import com.ws.ldy.core.utils.BeanDtoVoUtil;
import com.ws.ldy.manage.admin.model.dto.AdminMenuDTO;
import com.ws.ldy.manage.admin.model.query.AdminMenuQuery;
import com.ws.ldy.manage.admin.model.vo.AdminMenuVO;
import com.ws.ldy.manage.admin.service.AdminMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *   菜单
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 13:38
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin + "/menu")
@Api(value = "AdminMenuController", tags = "base--菜单管理")
public class AdminMenuController extends BaseController<AdminMenuService> {


    @GetMapping(value = "/list")
    @ApiOperation(value = "列表查询", notes = "根据sort正序排序返回")
    public R<IPage<AdminMenuVO>> list(@ModelAttribute AdminMenuQuery query) {
        return R.success(baseService.list(query));
    }


    @PostMapping
    @ApiOperation(value = "菜单添加")
    public R<Boolean> insert(@RequestBody AdminMenuDTO dto) {
        return R.successInsert(baseService.insert(dto));
    }


    @PutMapping(value = "/{id}")
    @ApiOperation(value = "ID编辑", notes = "如果修改了终端,那么下级数据全部变更为父级选中的终端")
    public R<Boolean> upd(@PathVariable String id, @RequestBody AdminMenuDTO dto) {
        return R.successUpdate(baseService.upd(id, dto));
    }


    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "ID删除", notes = "同时删除当前菜单和当前菜单下的所有子菜单")
    public R<List<String>> del(@PathVariable String id) {
        return R.successDelete(baseService.del(id));
    }


    @GetMapping(value = "/findTree")
    @ApiOperation(value = "左导航菜单", notes = "当前用户对应的角色菜单数据, 树结构数据,无限级,不限制层次,根据sort字段正序排序,sort越小越靠前")
    public R<List<AdminMenuVO>> menuTree() {
        //获取菜单
        List<AdminMenuVO> menuTree = baseService.getMenuTree();
        return R.successFind(menuTree);
    }


    @GetMapping(value = "/menuList")
    @ApiOperation(value = "pid + roleId 查询菜单列表", notes = "" +
            "1、未传递查询所有: isChecked=false || null \r\n " +
            "2、根据 pid + roleId 查询当前角色+指定父菜单下的所有菜单给予选中状态 isChecked=true，包括自身, 不在当前 pid 下和 roleId没有权限角色的: isChecked=false || null, 返回List 列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "父id", required = false, paramType = "query"),
            @ApiImplicitParam(name = "roleId", value = "角色Id", required = false, paramType = "query"),
            @ApiImplicitParam(name = "terminal", value = "终端(不传查所有)", required = false, paramType = "query", example = "")
    })
    public R<List<AdminMenuVO>> menuList(@RequestParam(required = false) String id,
                                         @RequestParam(required = false) String roleId,
                                         @RequestParam(required = false) Integer terminal) {
        List<AdminMenuVO> menus = baseService.menuList(id, roleId, terminal);
        return R.successFind(BeanDtoVoUtil.listVo(menus, AdminMenuVO.class));
    }

    /**
     * @param id     父id
     * @param roleId 角色Id，判断当前是否有权限并选中 Tree
     */
    @GetMapping(value = "/menuTree")
    @ApiOperation(value = "pid + roleId 查询菜单列表", notes = "" +
            "1、未传递查询所有: isChecked=false || null \r\n " +
            "2、根据 pid + roleId 查询当前角色+指定父菜单下的所有菜单给予选中状态 isChecked=true，包括自身, 不在当前 pid 下和 roleId没有权限角色的: isChecked=false || null, 返回List->Tree ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "父id", required = false, paramType = "query"),
            @ApiImplicitParam(name = "roleId", value = "角色Id", required = false, paramType = "query"),
            @ApiImplicitParam(name = "terminal", value = "终端(不传查所有)", required = false, paramType = "query", example = "")
    })
    public R<List<AdminMenuVO>> menuTree(@RequestParam(required = false) String id,
                                         @RequestParam(required = false) String roleId,
                                         @RequestParam(required = false) Integer terminal
    ) {
        List<AdminMenuVO> menus = baseService.menuTree(id, roleId, terminal);
        return R.successFind(BeanDtoVoUtil.listVo(menus, AdminMenuVO.class));
    }

}
