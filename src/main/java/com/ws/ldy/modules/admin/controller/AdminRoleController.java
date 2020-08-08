package com.ws.ldy.modules.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.enums.base.BaseConstant;
import com.ws.ldy.modules.admin.model.dto.AdminRoleDTO;
import com.ws.ldy.modules.admin.model.entity.AdminRole;
import com.ws.ldy.modules.admin.model.vo.AdminRoleVO;
import com.ws.ldy.modules.admin.service.AdminRoleService;
import com.ws.ldy.modules.admin.service.AdminRoleAuthService;
import com.ws.ldy.modules.admin.service.AdminRoleMenuService;
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
@RequestMapping("/admin/adminRole")
@Api(value = "AdminRoleController", tags = "角色管理", description = BaseConstant.InterfaceType.PC_ADMIN)
public class AdminRoleController extends BaseController<AdminRoleService> {

    @Autowired
    private AdminRoleMenuService adminRoleMenuService;
    @Autowired
    private AdminRoleAuthService adminRoleAuthService;

    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "name", value = "角色名称", required = false, paramType = "query"),
    })
    public R<IPage<AdminRoleVO>> findPage(String name) {
        Page<AdminRole> page = baseService.page(this.getPage(), new LambdaQueryWrapper<AdminRole>()
                .orderByAsc(AdminRole::getId)
                .like(StringUtils.isNotBlank(name), AdminRole::getName, name)
        );

        return R.successFind(BeanDtoVoUtil.pageVo(page, AdminRoleVO.class));
    }


    @RequestMapping(value = "/findList", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有", notes = "")
    public R<List<AdminRoleVO>> findList() {
        List<AdminRole> roles = baseService.list();
        return R.successFind(BeanDtoVoUtil.listVo(roles, AdminRoleVO.class));
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "添加", notes = "")
    public R<Void> insert(@RequestBody AdminRoleDTO adminRoleDto) {
        baseService.save(adminRoleDto.convert(AdminRole.class));
        return R.successInsert();
    }

    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    @ApiOperation(value = "ID编辑", notes = "")
    public R<Void> upd(@RequestBody AdminRoleDTO adminRoleDto) {
        baseService.updateById(adminRoleDto.convert(AdminRole.class));
        return R.successUpdate();
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
    public R<List<AdminRoleVO>> findRoleChecked(@RequestParam String userId) {
        List<AdminRoleVO> roles = baseService.findRoleChecked(userId);
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
        adminRoleMenuService.roleMenuAuth(roleId, menuIds);
        return R.successUpdate();
    }


    @RequestMapping(value = "/updRoleAuth", method = RequestMethod.PUT)
    @ApiOperation(value = "角色的URL权限分配", notes = "")
    public R<Void> updRoleAuth(@RequestParam String roleId, String[] authIds) {
        adminRoleAuthService.roleUrlAuth(roleId, authIds);
        return R.successUpdate();
    }
}
