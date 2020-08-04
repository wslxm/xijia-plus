package com.ws.ldy.modules.admin.controller;

import com.ws.ldy.modules.admin.model.vo.AdminAuthorityVO;
import com.ws.ldy.modules.admin.service.AdminAuthorityService;
import com.ws.ldy.modules.admin.service.AdminRoleAuthService;
import com.ws.ldy.others.base.controller.BaseController;
import com.ws.ldy.enums.base.BaseConstant;
import com.ws.ldy.common.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *   接口管理
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Mon Nov 25 08:02:49 CST 2019
 */
@RestController
@RequestMapping("/admin/adminAuthority")
@Api(value = "AuthorityAdminController", tags = "URL权限管理", description = BaseConstant.InterfaceType.PC_ADMIN)
public class AdminAuthorityController extends BaseController<AdminAuthorityService> {

    @Autowired
    private AdminRoleAuthService adminRoleAuthService;


    @ApiOperation(value = "扫描权限", notes = "扫描权限列表数据, 1、存在变更接口描叙, 2、url变动会重新生成权限数据,角色原有的该接口权限会丢失,需重新分配 3、自动删除的多余接口")
    @RequestMapping(value = "/refreshAuthority", method = RequestMethod.PUT)
    public R<Void> refreshAuthority() {
        baseService.refreshAuthority();
        return R.success();
    }


    @ApiOperation(value = "查询所有 || 根据角色ID选中", notes = "1、没有传递角色Id,查询所有权限数据 isChecked=false || null 2、跟据角色ID查询,角色当前拥有权限：isChecked=true 角色没有权限：isChecked=false || null")
    @RequestMapping(value = "/findList", method = RequestMethod.GET)
    @ApiImplicitParam(name = "roleId", value = "角色Id", required = false, paramType = "query")
    public R<List<AdminAuthorityVO>> findList(String roleId) {
        List<AdminAuthorityVO> roleAuthorityChecked = adminRoleAuthService.findRoleAuthorityChecked(roleId);
        return R.success(roleAuthorityChecked);
    }
}
