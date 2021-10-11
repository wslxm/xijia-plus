package io.github.wslxm.springbootplus2.manage.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.wslxm.springbootplus2.core.base.controller.BaseController;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.R;
import io.github.wslxm.springbootplus2.manage.admin.model.dto.AdminRoleDTO;
import io.github.wslxm.springbootplus2.manage.admin.model.dto.role.RoleAuthDTO;
import io.github.wslxm.springbootplus2.manage.admin.model.dto.role.RoleMenuDTO;
import io.github.wslxm.springbootplus2.manage.admin.model.dto.role.UserRoleDTO;
import io.github.wslxm.springbootplus2.manage.admin.model.query.AdminRoleQuery;
import io.github.wslxm.springbootplus2.manage.admin.model.vo.AdminRoleVO;
import io.github.wslxm.springbootplus2.manage.admin.service.AdminRoleService;
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
@RequestMapping(BaseConstant.Uri.apiAdmin + "/role")
@Api(value = "AdminRoleController", tags = "base--角色管理")
@CrossOrigin
public class AdminRoleController extends BaseController<AdminRoleService> {


    @GetMapping(value = "/list")
    @ApiOperation(value = "列表查询")
    public R<IPage<AdminRoleVO>> list(@ModelAttribute AdminRoleQuery query) {
        return R.success(baseService.list(query));
    }


    @PostMapping
    @ApiOperation(value = "添加")
    public R<String> insert(@RequestBody AdminRoleDTO dto) {
        return R.successInsert(baseService.insert(dto));
    }


    @PutMapping(value = "/{id}")
    @ApiOperation(value = "ID编辑")
    public R<Boolean> upd(@PathVariable String id, @RequestBody AdminRoleDTO dto) {
        return R.successUpdate(baseService.upd(id, dto));
    }


    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "ID删除")
    public R<Boolean> del(@PathVariable String id) {
        return R.successDelete(baseService.del(id));
    }

    //=========================================================================
    //============================ 增删改查外 ===================================
    //=========================================================================
    //=========================================================================

    // @GetMapping(value = "/findUserRole")
    // @ApiOperation(value = "获取指定用户的角色列表", notes = "用户角色分配查询到所有角色, 并使用户拥有的角色赋予 isChecked=true")
    // public R<List<AdminRoleVO>> findRoleChecked(@RequestParam String userId) {
    //     return R.successFind(baseService.findByUserIdRoleChecked(userId));
    // }

    @PutMapping(value = "/updUserRole")
    @ApiOperation(value = "用户的角色分配")
    @Deprecated
    public R<Boolean> updUserRole(@RequestBody UserRoleDTO dto) {
        return R.successUpdate(baseService.updUserRole(dto));
    }

    @PutMapping(value = "/updRoleMenu")
    @ApiOperation(value = "角色的菜单分配")
    public R<Boolean> updRoleMenu(@RequestBody RoleMenuDTO dto) {
        // 菜单每次都是重新请求接口获取的,不用做任何配置
        return R.successUpdate(baseService.roleMenuAuth(dto));
    }

    @PutMapping(value = "/updRoleAuth")
    @ApiOperation(value = "角色的URL权限分配")
    public R<Boolean> updRoleAuth(@RequestBody RoleAuthDTO dto) {
        return R.successUpdate(baseService.roleUrlAuth(dto));
    }

    @PutMapping(value = "/updRoleAuthAll")
    @ApiOperation(value = "所有角色拥有所有权限")
    public R<Boolean> updRoleAuthAll() {
        return R.successUpdate(baseService.roleAuthAll());
    }
}
