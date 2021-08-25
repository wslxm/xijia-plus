package com.ws.ldy.manage.admin.controller;

import com.ws.ldy.core.result.R;
import com.ws.ldy.core.constant.BaseConstant;
import com.ws.ldy.manage.admin.model.dto.AdminAuthorityDTO;
import com.ws.ldy.manage.admin.model.vo.AdminAuthorityVO;
import com.ws.ldy.manage.admin.service.AdminAuthorityService;
import com.ws.ldy.core.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 接口管理
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Mon Nov 25 08:02:49 CST 2019
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin + "/adminAuthority")
@Api(value = "AdminAuthorityController", tags = "base--URL权限管理")
public class AdminAuthorityController extends BaseController<AdminAuthorityService> {


    @ApiOperation(value = "查询所有", notes = "查询所有权限数据，根据不同的端的枚举code 拼接最顶级的目录, 顶级目录ID = -1")
    @RequestMapping(value = "/findList", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "指定端：字典code", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pid", value = "父级id(只查指定父级下的数据)", required = false, paramType = "query"),
    })
    public R<List<AdminAuthorityVO>> findList(Integer type, String pid) {
        return R.successFind(baseService.findList(type, pid));
    }


    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    @ApiOperation(value = "ID编辑", notes = "必须传递ID")
    public R<Boolean> upd(@RequestBody @Validated AdminAuthorityDTO dto) {
        return R.successUpdate(baseService.upd(dto));
    }


    @ApiOperation(value = "查询所有 || 根据角色ID选中" +
            "1、没有传递角色Id,查询所有权限数据 isChecked=false \r\n" +
            "2、跟据角色ID查询,角色当前拥有权限：isChecked=true 角色没有权限：isChecked=false \r\n" +
            "3、只查询管理端 \r\n" +
            "4、数据按请求方式排序 "
    )
    @RequestMapping(value = "/findByRoleIdList", method = RequestMethod.GET)
    @ApiImplicitParam(name = "roleId", value = "角色Id", required = false, paramType = "query")
    public R<List<AdminAuthorityVO>> findByRoleIdList(String roleId) {
        List<AdminAuthorityVO> roleAuthorityChecked = baseService.findByRoleIdAuthorityChecked(roleId);
        return R.success(roleAuthorityChecked);
    }


    @RequestMapping(value = "/findByRoleIdAuthorityTreeChecked", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有 || 根据角色ID选中 -> Tree " +
            "1、没有传递角色Id,查询所有权限数据 isChecked=false \r\n" +
            "2、跟据角色ID查询,角色当前拥有权限：isChecked=true 角色没有权限：isChecked=false \r\n" +
            "3、只查询管理端 \r\n" +
            "4、数据按请求方式排序 ")
    @ApiImplicitParam(name = "roleId", value = "角色Id", required = false, paramType = "query")
    public R<List<AdminAuthorityVO>> findByRoleIdAuthorityTreeChecked(String roleId) {
        return R.successFind(baseService.findByRoleIdAuthorityTreeChecked(roleId));
    }


    @ApiOperation(value = "扫描权限", notes = "扫描权限列表数据, 1、存在变更接口描叙, 2、url变动会重新生成权限数据,角色原有的该接口权限会丢失,需重新分配 3、自动删除的多余接口")
    @RequestMapping(value = "/refreshAuthority", method = RequestMethod.PUT)
    @Deprecated
    public R<Boolean> refreshAuthority() {
        return R.success(baseService.refreshAuthDB());
    }
}
