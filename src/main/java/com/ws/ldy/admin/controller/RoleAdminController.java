package com.ws.ldy.admin.controller;

import com.ws.ldy.admin.dto.RoleAdminDto;
import com.ws.ldy.admin.entity.RoleAdmin;
import com.ws.ldy.admin.service.impl.RoleAdminServiceImpl;
import com.ws.ldy.admin.service.impl.RoleAuthAdminServiceImpl;
import com.ws.ldy.admin.service.impl.RoleMenuAdminServiceImpl;
import com.ws.ldy.admin.service.impl.RoleUserAdminServiceImpl;
import com.ws.ldy.admin.vo.RoleAdminVo;
import com.ws.ldy.base.controller.BaseController;
import com.ws.ldy.base.query.QueryCriteria;
import com.ws.ldy.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query"),
    })
    public Result<Page<RoleAdminVo>> findPage(Integer id) {
        QueryCriteria queryCriteria = new QueryCriteria().eq(id != null, "id", id).orderByAsc("id");
        Page<RoleAdmin> rolePage = roleAdminServiceImpl.page(this.getPage(), queryCriteria);
        return success(this.pageVoStream(rolePage, RoleAdminVo.class));
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation("查询所有")
    public Result<List<RoleAdminVo>> list() {
        List<RoleAdmin> roles = roleAdminServiceImpl.findAll();
        return success(this.listVoStream(roles, RoleAdminVo.class));
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
        return success();
    }


    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ApiOperation("批量删除/单删除")
    public Result<Void> delete(Integer[] ids) {
        roleAdminServiceImpl.deleteByIds(ids);
        return success();
    }


    @RequestMapping(value = "/updRoleMenu", method = RequestMethod.PUT)
    @ApiOperation("角色菜单权限分配")
    public Result<Void> updRoleMenu(@RequestParam Integer roleId, Integer[] menuIds, @RequestParam Integer pid) {
        roleMenuAdminServiceImpl.roleMenuAuth(roleId, menuIds, pid);
        return success();
    }


    @RequestMapping(value = "/updRoleUrlAuth", method = RequestMethod.PUT)
    @ApiOperation("角色URL权限分配")
    public Result<Void> updRoleUrlAuth(@RequestParam Integer roleId, Integer[] authIds) {
        roleAuthAdminServiceImpl.roleUrlAuth(roleId, authIds);
        return success();
    }


    @RequestMapping(value = "/updRoleUser", method = RequestMethod.PUT)
    @ApiOperation("角色用户分配")
    public Result<Void> updRoleUser(@RequestParam Integer roleId, Integer[] userIds) {
        roleUserAdminServiceImpl.updRoleUser(roleId, userIds);
        return success();
    }
}
