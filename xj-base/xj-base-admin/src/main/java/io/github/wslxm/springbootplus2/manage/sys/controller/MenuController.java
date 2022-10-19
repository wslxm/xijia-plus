package io.github.wslxm.springbootplus2.manage.sys.controller;


import io.github.wslxm.springbootplus2.core.base.controller.BaseController;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.Result;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.MenuDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.query.MenuQuery;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.MenuVO;
import io.github.wslxm.springbootplus2.manage.sys.service.MenuService;
import io.swagger.annotations.Api;
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
@RequestMapping(BaseConstant.Uri.API_ADMIN+ "/sys/menu")
@Api(value = "MenuController", tags = "base--sys--菜单管理")
public class MenuController extends BaseController<MenuService> {


    @GetMapping(value = "/tree")
    @ApiOperation(value = "列表查询(不支持分页)", notes = "根据sort正序排序返回")
    public Result<List<MenuVO>> tree(@ModelAttribute MenuQuery query) {
        return Result.success(baseService.tree(query));
    }


    @PostMapping
    @ApiOperation(value = "菜单添加")
    public Result<String> insert(@RequestBody MenuDTO dto) {
        return Result.successInsert(baseService.insert(dto));
    }


    @PutMapping(value = "/{id}")
    @ApiOperation(value = "ID编辑", notes = "如果修改了终端,那么下级数据全部变更为父级选中的终端")
    public Result<Boolean> upd(@PathVariable String id, @RequestBody MenuDTO dto) {
        return Result.successUpdate(baseService.upd(id, dto));
    }


    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "ID删除", notes = "同时删除当前菜单和当前菜单下的所有子菜单")
    public Result<List<String>> del(@PathVariable String id) {
        return Result.successDelete(baseService.del(id));
    }


    @GetMapping(value = "/findTree")
    @ApiOperation(value = "左导航菜单", notes = "当前用户对应的角色菜单数据, 树结构数据,无限级,不限制层次,根据sort字段正序排序,sort越小越靠前")
    public Result<List<MenuVO>> findTree() {
        return Result.successFind(baseService.findTree());
    }
}
