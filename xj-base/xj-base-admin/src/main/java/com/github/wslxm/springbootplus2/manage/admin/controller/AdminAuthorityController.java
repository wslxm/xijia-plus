package com.github.wslxm.springbootplus2.manage.admin.controller;

import com.github.wslxm.springbootplus2.core.base.controller.BaseController;
import com.github.wslxm.springbootplus2.core.constant.BaseConstant;
import com.github.wslxm.springbootplus2.core.result.R;
import com.github.wslxm.springbootplus2.manage.admin.model.dto.AdminAuthorityDTO;
import com.github.wslxm.springbootplus2.manage.admin.model.query.AdminAuthorityQuery;
import com.github.wslxm.springbootplus2.manage.admin.model.vo.AdminAuthorityVO;
import com.github.wslxm.springbootplus2.manage.admin.service.AdminAuthorityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 接口管理
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Mon Nov 25 08:02:49 CST 2019
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin + "/authority")
@Api(value = "AdminAuthorityController", tags = "base--URL权限管理")
public class AdminAuthorityController extends BaseController<AdminAuthorityService> {


    @GetMapping(value = "/list")
    @ApiOperation(value = "查询所有-接口管理", notes = "查询所有权限数据，根据不同的端的枚举code 拼接最顶级的目录, 顶级目录ID = -1")
    public R<List<AdminAuthorityVO>> list(@ModelAttribute AdminAuthorityQuery query) {
        return R.successFind(baseService.list(query));
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "ID编辑", notes = "必须传递ID")
    public R<Boolean> upd(@PathVariable String id, @RequestBody @Validated AdminAuthorityDTO dto) {
        return R.successUpdate(baseService.upd(id, dto));
    }


    @ApiOperation(value = "扫描权限", notes = "扫描权限列表数据, 1、存在变更接口描叙, 2、url变动会重新生成权限数据,角色原有的该接口权限会丢失,需重新分配 3、自动删除的多余接口")
    @PutMapping(value = "/refreshAuthority")
    @Deprecated
    public R<Boolean> refreshAuthority() {
        return R.success(baseService.refreshAuthDB());
    }
}
