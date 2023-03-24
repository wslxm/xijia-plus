package io.github.wslxm.springbootplus2.manage.sys.controller;

import io.github.wslxm.springbootplus2.core.base.model.BasePage;
import io.github.wslxm.springbootplus2.core.base.controller.BaseController;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.Result;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.RoleDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.query.RoleQuery;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.RoleVO;
import io.github.wslxm.springbootplus2.manage.sys.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 13:38
 */
@RestController
@RequestMapping(BaseConstant.Uri.API_ADMIN+ "/sys/role")
@Api(value = "RoleController", tags = "base--sys--角色管理")
public class RoleController extends BaseController<RoleService> {


    @GetMapping(value = "/findPage")
    @ApiOperation(value = "列表查询")
    public Result<BasePage<RoleVO>> findPage(@ModelAttribute RoleQuery query) {
        return Result.success(baseService.findPage(query));
    }


    @PostMapping
    @ApiOperation(value = "添加")
    public Result<String> insert(@RequestBody RoleDTO dto) {
        return Result.successInsert(baseService.insert(dto));
    }


    @PutMapping(value = "/{id}")
    @ApiOperation(value = "ID编辑")
    public Result<Boolean> upd(@PathVariable String id, @RequestBody RoleDTO dto) {
        return Result.successUpdate(baseService.upd(id, dto));
    }


    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "ID删除")
    public Result<Boolean> del(@PathVariable String id) {
        return Result.successDelete(baseService.del(id));
    }

    //=========================================================================
    //============================ 增删改查外 ===================================
    //=========================================================================
    //=========================================================================

//    @PutMapping(value = "/updRoleAuth")
//    @ApiOperation(value = "角色的URL权限分配")
//    public Result<Boolean> updRoleAuth(@RequestBody RoleAuthDTO dto) {
//        return Result.successUpdate(baseService.roleUrlAuth(dto));
//    }

//    @PutMapping(value = "/updRoleAuthAll")
//    @ApiOperation(value = "所有角色拥有所有权限")
//    public Result<Boolean> updRoleAuthAll() {
//        return Result.successUpdate(baseService.roleAuthAll());
//    }
}
