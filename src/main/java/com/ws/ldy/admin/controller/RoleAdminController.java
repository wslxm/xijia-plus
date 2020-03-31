package com.ws.ldy.admin.controller;

import com.ws.ldy.admin.entity.RoleAdmin;
import com.ws.ldy.admin.service.impl.RoleAdminServiceImpl;
import com.ws.ldy.admin.service.impl.RoleAuthAdminServiceImpl;
import com.ws.ldy.admin.service.impl.RoleMenuAdminServiceImpl;
import com.ws.ldy.admin.service.impl.RoleUserAdminServiceImpl;
import com.ws.ldy.base.controller.BaseController;
import com.ws.ldy.common.query.QueryCriteria;
import com.ws.ldy.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private RoleAdminServiceImpl roleAdminServiceImpl;
    @Autowired
    private RoleMenuAdminServiceImpl roleMenuAdminServiceImpl;
    @Autowired
    private RoleAuthAdminServiceImpl roleAuthAdminServiceImpl;
    @Autowired
    private RoleUserAdminServiceImpl roleUserAdminServiceImpl;


    @RequestMapping(value = "/findAll/{type}", method = RequestMethod.GET)
    @ApiOperation("分页查询/查询所有")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "type", value = "1 分页查询  =2 所有查询", required = true, paramType = "query"),
    })
    public Result findAll(@PathVariable Integer type) {
        if (type == 1) {
            QueryCriteria queryCriteria = new QueryCriteria().equal("id", getString("id", "")).orderByAsc("id");
            Page<RoleAdmin> roles = roleAdminServiceImpl.page(this.getPage(), queryCriteria.build(), queryCriteria.getSort());
            return success(roles.getContent(), roles.getTotalPages());
        } else {
            List<RoleAdmin> roles = roleAdminServiceImpl.findAll();
            return success(roles);
        }
    }


    /***
     * TODO  添加/修改
     * @param type t=1 添加，=2修改
     * @param role 对象数据
     */
    @RequestMapping(value = "/save/{type}", method = RequestMethod.POST)
    @ApiOperation("添加/修改")
    public Result save(@PathVariable Integer type, RoleAdmin role) {
        if (type == 1) {
            roleAdminServiceImpl.save(role);
        } else {
            roleAdminServiceImpl.save(role);
        }
        return success();
    }


    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ApiOperation("批量删除/单删除")
    public Result delete(Integer[] ids) {
        roleAdminServiceImpl.deleteByIds(ids);
        return success();
    }


    @RequestMapping(value = "/updRoleMenu", method = RequestMethod.PUT)
    @ApiOperation("角色菜单权限分配")
    public Result updRoleMenu(Integer roleId, Integer[] menuIds, Integer pid) {
        roleMenuAdminServiceImpl.roleMenuAuth(roleId, menuIds, pid);
        return success();
    }


    @RequestMapping(value = "/updRoleUrlAuth", method = RequestMethod.PUT)
    @ApiOperation("角色URL权限分配")
    public Result updRoleUrlAuth(Integer roleId, Integer[] authIds) {
        roleAuthAdminServiceImpl.roleUrlAuth(roleId, authIds);
        return success();
    }


    @RequestMapping(value = "/updRoleUser", method = RequestMethod.PUT)
    @ApiOperation("角色用户分配")
    public Result updRoleUser(Integer roleId, Integer[] userIds) {
        roleUserAdminServiceImpl.updRoleUser(roleId, userIds);
        return success();
    }
}
