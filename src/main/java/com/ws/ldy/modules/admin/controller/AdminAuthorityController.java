package com.ws.ldy.modules.admin.controller;

import com.ws.ldy.common.result.R;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.enums.Enums;
import com.ws.ldy.modules.admin.model.dto.AdminAuthorityDTO;
import com.ws.ldy.modules.admin.model.entity.AdminAuthority;
import com.ws.ldy.modules.admin.model.vo.AdminAuthorityVO;
import com.ws.ldy.modules.admin.service.AdminAuthorityService;
import com.ws.ldy.others.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.EnumSet;
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
@Api(value = "AdminAuthorityController", tags = "URL权限管理", consumes = BaseConstant.InterfaceType.PC_ADMIN)
public class AdminAuthorityController extends BaseController<AdminAuthorityService> {


    @RequestMapping(value = "/findList", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有", notes = "查询所有权限数据，根据不同的端的枚举code 拼接最顶级的目录, 顶级目录ID = -1")
    public R<List<AdminAuthorityVO>> findList() {
        return R.successFind(baseService.findList());
    }


    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    @ApiOperation(value = "ID编辑", notes = "必须传递ID")
    public R<Void> upd(@RequestBody @Validated AdminAuthorityDTO dto) {
        baseService.updateById(dto.convert(AdminAuthority.class));
        // 刷新缓存
        baseService.refreshAuthCache();
        return R.successUpdate();
    }


    @ApiOperation(value = "查询所有 || 根据角色ID选中", notes = "" +
            "1、没有传递角色Id,查询所有权限数据 isChecked=false \r\n" +
            "2、跟据角色ID查询,角色当前拥有权限：isChecked=true 角色没有权限：isChecked=false \r\n" +
            "3、只查询管理端 \r\n" +
            "4、数据按请求方式排序 "
    )
    @RequestMapping(value = "/findByRoleIdList", method = RequestMethod.GET)
    @ApiImplicitParam(name = "roleId", value = "角色Id", required = false, paramType = "query")
    public R<List<AdminAuthorityVO>> findList(String roleId) {
        List<AdminAuthorityVO> roleAuthorityChecked = baseService.findByRoleIdAuthorityChecked(roleId);
        return R.success(roleAuthorityChecked);
    }


    @RequestMapping(value = "/findByRoleIdAuthorityTreeChecked", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有 || 根据角色ID选中 -> Tree ", notes = "" +
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
    public R<Void> refreshAuthority() {
        baseService.refreshAuthDB();
        return R.success();
    }

}
