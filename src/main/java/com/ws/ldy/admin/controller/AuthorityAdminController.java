package com.ws.ldy.admin.controller;

import com.ws.ldy.admin.entity.AuthorityAdmin;
import com.ws.ldy.admin.service.impl.AuthorityAdminServiceImpl;
import com.ws.ldy.admin.service.impl.RoleAuthAdminServiceImpl;
import com.ws.ldy.base.controller.BaseController;
import com.ws.ldy.common.query.QueryCriteria;
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
 * TODO  代码生成器自动生成，请自定义描叙
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Mon Nov 25 08:02:49 CST 2019
 */
@RestController
@RequestMapping("/authorityAdmin")
@Api(value = "AuthorityAdminController", tags = "URL权限管理")
public class AuthorityAdminController extends BaseController {

    @Autowired
    private AuthorityAdminServiceImpl authorityAdminServiceImpl;
    @Autowired
    private RoleAuthAdminServiceImpl roleAuthAdminServiceImpl;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "id", value = "数据id", required = false, paramType = "query"),
    })
    public Result<List<AuthorityAdmin>> findAll(Integer id) {
        Page<AuthorityAdmin> authorityAdmins = authorityAdminServiceImpl.page(this.getPage(), new QueryCriteria()
                .equal(id != null, "id", id)
                .orderByAsc("id")
        );
        return success(authorityAdmins.getContent(), authorityAdmins.getTotalPages());
    }


    @ApiOperation("添加/修改== 1 添加，2修改")
    @RequestMapping(value = "/save/{type}", method = RequestMethod.POST)
    public Result save(@PathVariable Integer type, AuthorityAdmin authorityAdmin) {
        if (type == 1) {
            authorityAdminServiceImpl.save(authorityAdmin);
        } else {
            authorityAdminServiceImpl.save(authorityAdmin);
        }
        return success();
    }


    @ApiOperation("批量删除/单删除")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Result<Void> delete(@RequestBody Integer[] ids) {
        authorityAdminServiceImpl.deleteByIds(ids);
        return success();
    }


    @ApiOperation("扫描权限：权限列表数据刷新，根据权限注解动态生成权限列表，无权限注解默认然后用户有权限访问")
    @RequestMapping(value = "/putAuthority", method = RequestMethod.PUT)
    public Result<Void> putAuthority() {
        authorityAdminServiceImpl.putClass();
        return success();
    }


    @ApiOperation("查询所有,跟据角色赋予选中状态")
    @RequestMapping(value = "/findList", method = RequestMethod.GET)
    public Result findList(Integer roleId) {
        List<AuthorityAdmin> roleAuthorityChecked = roleAuthAdminServiceImpl.findRoleAuthorityChecked(roleId);
        return success(roleAuthorityChecked);
    }
}
