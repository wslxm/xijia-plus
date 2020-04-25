package com.ws.ldy.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
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
@Api(value = "RoleAdminController", tags = "角色管理")
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
    })
    public Result<IPage<RoleAdminVo>> findPage(Integer id) {
        Page<RoleAdmin> page = roleAdminServiceImpl.page(this.getPage(), new LambdaQueryWrapper<RoleAdmin>()
                .orderByAsc(RoleAdmin::getId)
                .eq(id != null, RoleAdmin::getId, id)
        );
        return successFind(page.convert(item -> item.convert(RoleAdminVo.class)));
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation("查询所有")
    public Result<List<RoleAdminVo>> list() {
        List<RoleAdmin> roles = roleAdminServiceImpl.list();
        return successFind(this.listVoStream(roles, RoleAdminVo.class));
    }


    @RequestMapping(value = "/findRoleChecked", method = RequestMethod.GET)
    @ApiOperation("角色分配查询所有--用户当前角色赋予Checked=true")
    public Result<List<RoleAdminVo>> findRoleChecked(@RequestParam String userId) {
        List<RoleAdminVo> roles = roleAdminServiceImpl.findRoleChecked(userId);
        return successUpdate(roles);
    }


    @RequestMapping(value = "/updUserRole", method = RequestMethod.PUT)
    @ApiOperation("用户分配角色")
    public Result<Void> updUserRole(@RequestParam Integer userId, Integer[] roleIds) {
        boolean result = roleAdminServiceImpl.updUserRole(userId, roleIds);
        return successUpdate();
    }


    /***
     * TODO  添加/修改
     * @param type
     * @param roleAdminDto 对象数据
     */
    @RequestMapping(value = "/save/{type}", method = RequestMethod.POST)
    @ApiOperation("添加/修改 ==>>>  t=1 添加，=2修改")
    public Result<Void> save(@PathVariable Integer type, @RequestBody RoleAdminDto roleAdminDto) {
        if (type == 1) {
            roleAdminServiceImpl.save(roleAdminDto.convert(RoleAdmin.class));
        } else {
            roleAdminServiceImpl.save(roleAdminDto.convert(RoleAdmin.class));
        }
        return successInsert();
    }


    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ApiOperation("批量删除/单删除")
    public Result<Void> delete(Integer[] ids) {
        roleAdminServiceImpl.removeByIds(Arrays.asList(ids));
        return successDelete();
    }


    @RequestMapping(value = "/updRoleMenu", method = RequestMethod.PUT)
    @ApiOperation("角色菜单权限分配")
    public Result<Void> updRoleMenu(@RequestParam Integer roleId, Integer[] menuIds, @RequestParam Integer pid) {
        roleMenuAdminServiceImpl.roleMenuAuth(roleId, menuIds, pid);
        return successUpdate();
    }


    @RequestMapping(value = "/updRoleUrlAuth", method = RequestMethod.PUT)
    @ApiOperation("角色URL权限分配")
    public Result<Void> updRoleUrlAuth(@RequestParam Integer roleId, Integer[] authIds) {
        roleAuthAdminServiceImpl.roleUrlAuth(roleId, authIds);
        return successUpdate();
    }


    @RequestMapping(value = "/updRoleUser", method = RequestMethod.PUT)
    @ApiOperation("角色用户分配")
    public Result<Void> updRoleUser(@RequestParam Integer roleId, Integer[] userIds) {
        roleUserAdminServiceImpl.updRoleUser(roleId, userIds);
        return successUpdate();
    }
}
