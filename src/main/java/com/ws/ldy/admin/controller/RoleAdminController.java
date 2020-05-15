package com.ws.ldy.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.admin.enums.Constant;
import com.ws.ldy.admin.model.dto.RoleAdminDto;
import com.ws.ldy.admin.model.entity.RoleAdmin;
import com.ws.ldy.admin.model.vo.RoleAdminVo;
import com.ws.ldy.admin.service.impl.RoleAdminServiceImpl;
import com.ws.ldy.admin.service.impl.RoleAuthAdminServiceImpl;
import com.ws.ldy.admin.service.impl.RoleMenuAdminServiceImpl;
import com.ws.ldy.admin.service.impl.RoleUserAdminServiceImpl;
import com.ws.ldy.base.controller.BaseController;
import com.ws.ldy.config.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
@Api(value = "RoleAdminController", tags = "角色管理", description = Constant.InterfaceType.PC_ADMIN)
public class RoleAdminController extends BaseController {

    @Resource
    private RoleAdminServiceImpl roleAdminServiceImpl;
    @Resource
    private RoleMenuAdminServiceImpl roleMenuAdminServiceImpl;
    @Resource
    private RoleAuthAdminServiceImpl roleAuthAdminServiceImpl;
    @Resource
    private RoleUserAdminServiceImpl roleUserAdminServiceImpl;


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "name", value = "角色名称", required = false, paramType = "query"),
    })
    public Result<IPage<RoleAdminVo>> findPage(String name) {
        Page<RoleAdmin> page = roleAdminServiceImpl.page(this.getPage(), new LambdaQueryWrapper<RoleAdmin>()
                .orderByAsc(RoleAdmin::getId)
                .like(StringUtils.isNotBlank(name), RoleAdmin::getName, name)
        );
        return successFind(page.convert(item -> item.convert(RoleAdminVo.class)));
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation("查询所有")
    public Result<List<RoleAdminVo>> list() {
        List<RoleAdmin> roles = roleAdminServiceImpl.list();
        return successFind(this.listVoStream(roles, RoleAdminVo.class));
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation("添加")
    public Result<Void> insert(@RequestBody RoleAdminDto roleAdminDto) {
        roleAdminServiceImpl.save(roleAdminDto.convert(RoleAdmin.class));
        return successInsert();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ApiOperation("编辑")
    public Result<Void> update(@RequestBody RoleAdminDto roleAdminDto) {
        roleAdminServiceImpl.updateById(roleAdminDto.convert(RoleAdmin.class));
        return successInsert();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ApiOperation("单删除")
    public Result<Void> delete(Integer id) {
        roleAdminServiceImpl.removeById(id);
        return successDelete();
    }


    //=========================================================================
    //============================ 增删改查外 ===================================
    //=========================================================================
    //=========================================================================


    @RequestMapping(value = "/findRoleChecked", method = RequestMethod.GET)
    @ApiOperation("用户角色分配==>查询所有角色,用户拥有角色赋予isChecked=true")
    public Result<List<RoleAdminVo>> findRoleChecked(@RequestParam String userId) {
        List<RoleAdminVo> roles = roleAdminServiceImpl.findRoleChecked(userId);
        return successFind(roles);
    }


    @RequestMapping(value = "/updUserRole", method = RequestMethod.PUT)
    @ApiOperation("用户角色分配")
    public Result<Void> updUserRole(@RequestParam Integer userId, Integer[] roleIds) {
        boolean result = roleAdminServiceImpl.updUserRole(userId, roleIds);
        return successUpdate();
    }


    @RequestMapping(value = "/updRoleMenu", method = RequestMethod.PUT)
    @ApiOperation("角色菜单分配")
    public Result<Void> updRoleMenu(@RequestParam Integer roleId, Integer[] menuIds) {
        roleMenuAdminServiceImpl.roleMenuAuth(roleId, menuIds);
        return successUpdate();
    }


    @RequestMapping(value = "/updRoleUrlAuth", method = RequestMethod.PUT)
    @ApiOperation("角色URL分配")
    public Result<Void> updRoleUrlAuth(@RequestParam Integer roleId, Integer[] authIds) {
        roleAuthAdminServiceImpl.roleUrlAuth(roleId, authIds);
        return successUpdate();
    }
}
