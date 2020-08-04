package com.ws.ldy.modules.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.enums.base.BaseConstant;
import com.ws.ldy.modules.admin.model.dto.RoleAdminDto;
import com.ws.ldy.modules.admin.model.entity.RoleAdmin;
import com.ws.ldy.modules.admin.model.vo.RoleAdminVo;
import com.ws.ldy.modules.admin.service.RoleAdminService;
import com.ws.ldy.modules.admin.service.RoleAuthAdminService;
import com.ws.ldy.modules.admin.service.RoleMenuAdminService;
import com.ws.ldy.others.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 13:38
 */
@RestController
@RequestMapping("/roleAdmin")
@Api(value = "RoleAdminController", tags = "角色管理", description = BaseConstant.InterfaceType.PC_ADMIN)
public class RoleAdminController extends BaseController<RoleAdminService> {

    @Autowired
    private RoleMenuAdminService roleMenuAdminService;
    @Autowired
    private RoleAuthAdminService roleAuthAdminService;

    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "name", value = "角色名称", required = false, paramType = "query"),
    })
    public R<IPage<RoleAdminVo>> findPage(String name) {
        Page<RoleAdmin> page = baseService.page(this.getPage(), new LambdaQueryWrapper<RoleAdmin>()
                .orderByAsc(RoleAdmin::getId)
                .like(StringUtils.isNotBlank(name), RoleAdmin::getName, name)
        );

        return R.successFind(BeanDtoVoUtil.pageVo(page, RoleAdminVo.class));
    }


    @RequestMapping(value = "/findList", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有", notes = "")
    public R<List<RoleAdminVo>> findList() {
        List<RoleAdmin> roles = baseService.list();
        return R.successFind(BeanDtoVoUtil.listVo(roles, RoleAdminVo.class));
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "添加", notes = "")
    public R<Void> insert(@RequestBody RoleAdminDto roleAdminDto) {
        baseService.save(roleAdminDto.convert(RoleAdmin.class));
        return R.successInsert();
    }

    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    @ApiOperation(value = "ID编辑", notes = "")
    public R<Void> upd(@RequestBody RoleAdminDto roleAdminDto) {
        baseService.updateById(roleAdminDto.convert(RoleAdmin.class));
        return R.successInsert();
    }

    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    @ApiOperation(value = "ID删除", notes = "")
    public R<Void> del(@RequestParam String id) {
        baseService.removeById(id);
        return R.successDelete();
    }


    //=========================================================================
    //============================ 增删改查外 ===================================
    //=========================================================================
    //=========================================================================

    @RequestMapping(value = "/findUserRole", method = RequestMethod.GET)//Checked
    @ApiOperation(value = "获取用户的当前角色", notes = "用户角色分配查询到所有角色, 并使用户拥有的角色赋予 isChecked=true")
    public R<List<RoleAdminVo>> findRoleChecked(@RequestParam String userId) {
        List<RoleAdminVo> roles = baseService.findRoleChecked(userId);
        return R.successFind(roles);
    }


    @RequestMapping(value = "/updUserRole", method = RequestMethod.PUT)
    @ApiOperation(value = "用户的角色分配", notes = "")
    public R<Void> updUserRole(@RequestParam String userId, String[] roleIds) {
        boolean result = baseService.updUserRole(userId, roleIds);
        return R.successUpdate();
    }


    @RequestMapping(value = "/updRoleMenu", method = RequestMethod.PUT)
    @ApiOperation(value = "角色的菜单分配", notes = "")
    public R<Void> updRoleMenu(@RequestParam String roleId, String[] menuIds) {
        roleMenuAdminService.roleMenuAuth(roleId, menuIds);
        return R.successUpdate();
    }


    @RequestMapping(value = "/updRoleAuth", method = RequestMethod.PUT)
    @ApiOperation(value = "角色的URL权限分配", notes = "")
    public R<Void> updRoleAuth(@RequestParam String roleId, String[] authIds) {
        roleAuthAdminService.roleUrlAuth(roleId, authIds);
        return R.successUpdate();
    }
}
