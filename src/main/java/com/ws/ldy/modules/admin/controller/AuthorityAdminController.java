package com.ws.ldy.modules.admin.controller;

import com.ws.ldy.modules.admin.model.vo.AuthorityAdminVo;
import com.ws.ldy.modules.admin.service.AuthorityAdminService;
import com.ws.ldy.modules.admin.service.RoleAuthAdminService;
import com.ws.ldy.others.base.controller.BaseController;
import com.ws.ldy.enums.base.BaseConstant;
import com.ws.ldy.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TODO  接口管理
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Mon Nov 25 08:02:49 CST 2019
 */
@RestController
@RequestMapping("/authorityAdmin")
@Api(value = "AuthorityAdminController", tags = "URL权限管理", description = BaseConstant.InterfaceType.PC_ADMIN)
public class AuthorityAdminController extends BaseController<AuthorityAdminService> {

    @Autowired
    private RoleAuthAdminService roleAuthAdminService;


    @ApiOperation("扫描权限：权限列表数据刷新")
    @RequestMapping(value = "/putAuthority", method = RequestMethod.PUT)
    public Result<Void> refreshAuthority() {
        baseService.refreshAuthority();
        return Result.success();
    }


    @ApiOperation("查询所有,跟据角色赋予选中状态")
    @RequestMapping(value = "/findList", method = RequestMethod.GET)
    @ApiImplicitParam(name = "roleId", value = "角色Id", required = false, paramType = "query")
    public Result<List<AuthorityAdminVo>> findList(Integer roleId) {
        List<AuthorityAdminVo> roleAuthorityChecked = roleAuthAdminService.findRoleAuthorityChecked(roleId);
        return Result.success(roleAuthorityChecked);
    }
}
