package com.github.wslxm.springbootplus2.manage.admin.controller;


import com.github.wslxm.springbootplus2.core.base.controller.BaseController;
import com.github.wslxm.springbootplus2.core.constant.BaseConstant;
import com.github.wslxm.springbootplus2.core.result.R;
import com.github.wslxm.springbootplus2.manage.admin.model.dto.AdminMenuDTO;
import com.github.wslxm.springbootplus2.manage.admin.model.query.AdminMenuQuery;
import com.github.wslxm.springbootplus2.manage.admin.model.vo.AdminMenuVO;
import com.github.wslxm.springbootplus2.manage.admin.service.AdminMenuService;
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
@RequestMapping(BaseConstant.Uri.apiAdmin + "/menu")
@Api(value = "AdminMenuController", tags = "base--菜单管理")
public class AdminMenuController extends BaseController<AdminMenuService> {


    @GetMapping(value = "/list")
    @ApiOperation(value = "列表查询(不支持分页)", notes = "根据sort正序排序返回")
    public R<List<AdminMenuVO>> list(@ModelAttribute AdminMenuQuery query) {
        return R.success(baseService.list(query));
    }


    @PostMapping
    @ApiOperation(value = "菜单添加")
    public R<String> insert(@RequestBody AdminMenuDTO dto) {
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
    public R<List<AdminMenuVO>> findTree() {
        return R.successFind(baseService.findTree());
    }
}
