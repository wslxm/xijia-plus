package com.ws.ldy.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.admin.model.dto.RoleAdminDto;
import com.ws.ldy.admin.model.entity.RoleAdmin;
import com.ws.ldy.admin.model.vo.RoleAdminVo;
import com.ws.ldy.admin.service.RoleAdminService;
import com.ws.ldy.admin.service.RoleAuthAdminService;
import com.ws.ldy.admin.service.RoleMenuAdminService;
import com.ws.ldy.admin.service.RoleUserAdminService;
import com.ws.ldy.base.controller.BaseController;
import com.ws.ldy.base.enums.BaseConstant;
import com.ws.ldy.common.result.Result;
import com.ws.ldy.common.utils.BeanDtoVoUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TODO
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 13:38
 */
@RestController
@RequestMapping("/roleAdmin")
@Api(value = "RoleAdminController", tags = "角色管理", description = BaseConstant.InterfaceType.PC_ADMIN)
public class RoleAdminController extends BaseController<RoleAdminService> {

//    @Autowired
//    private RoleAdminServiceImpl roleAdminServiceImpl;
    @Autowired
    private RoleMenuAdminService roleMenuAdminService;
    @Autowired
    private RoleAuthAdminService roleAuthAdminService;
    @Autowired
    private RoleUserAdminService roleUserAdminService;


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "name", value = "角色名称", required = false, paramType = "query"),
    })
    public Result<IPage<RoleAdminVo>> findPage(String name) {
        Page<RoleAdmin> page = baseService.page(this.getPage(), new LambdaQueryWrapper<RoleAdmin>()
                .orderByAsc(RoleAdmin::getId)
                .like(StringUtils.isNotBlank(name), RoleAdmin::getName, name)
        );
        return Result.successFind(page.convert(item -> item.convert(RoleAdminVo.class)));
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation("查询所有")
    public Result<List<RoleAdminVo>> list() {
        List<RoleAdmin> roles = baseService.list();
        return Result.successFind(BeanDtoVoUtils.listVo(roles, RoleAdminVo.class));
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation("添加")
    public Result<Void> insert(@RequestBody RoleAdminDto roleAdminDto) {
        baseService.save(roleAdminDto.convert(RoleAdmin.class));
        return Result.successInsert();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ApiOperation("编辑")
    public Result<Void> update(@RequestBody RoleAdminDto roleAdminDto) {
        baseService.updateById(roleAdminDto.convert(RoleAdmin.class));
        return Result.successInsert();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ApiOperation("删除")
    public Result<Void> delete(Integer id) {
        baseService.removeById(id);
        return Result.successDelete();
    }


    //=========================================================================
    //============================ 增删改查外 ===================================
    //=========================================================================
    //=========================================================================


    @RequestMapping(value = "/findRoleChecked", method = RequestMethod.GET)
    @ApiOperation("用户角色分配弹出层查询所有角色,用户拥有角色赋予isChecked=true")
    public Result<List<RoleAdminVo>> findRoleChecked(@RequestParam String userId) {
        List<RoleAdminVo> roles = baseService.findRoleChecked(userId);
        return Result.successFind(roles);
    }


    @RequestMapping(value = "/updUserRole", method = RequestMethod.PUT)
    @ApiOperation("用户角色分配")
    public Result<Void> updUserRole(@RequestParam Integer userId, Integer[] roleIds) {
        boolean result = baseService.updUserRole(userId, roleIds);
        return Result.successUpdate();
    }


    @RequestMapping(value = "/updRoleMenu", method = RequestMethod.PUT)
    @ApiOperation("角色菜单分配")
    public Result<Void> updRoleMenu(@RequestParam Integer roleId, Integer[] menuIds) {
        roleMenuAdminService.roleMenuAuth(roleId, menuIds);
        return Result.successUpdate();
    }


    @RequestMapping(value = "/updRoleUrlAuth", method = RequestMethod.PUT)
    @ApiOperation("角色URL分配")
    public Result<Void> updRoleUrlAuth(@RequestParam Integer roleId, Integer[] authIds) {
        roleAuthAdminService.roleUrlAuth(roleId, authIds);
        return Result.successUpdate();
    }
}
