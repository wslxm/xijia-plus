package io.github.wslxm.springbootplus2.manage.sys.controller;

import io.github.wslxm.springbootplus2.core.base.controller.BaseController;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.R;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.AuthorityDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.query.AuthorityQuery;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.AuthorityVO;
import io.github.wslxm.springbootplus2.manage.sys.service.AuthorityService;
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
@RequestMapping(BaseConstant.Uri.API_ADMIN+ "/sys/authority")
@Api(value = "AuthorityController", tags = "base--sys--URL权限管理")
public class AuthorityController extends BaseController<AuthorityService> {


    @GetMapping(value = "/list")
    @ApiOperation(value = "查询所有-接口管理", notes = "查询所有权限数据，根据不同的端的枚举code 拼接最顶级的目录, 顶级目录ID = -1")
    public R<List<AuthorityVO>> list(@ModelAttribute AuthorityQuery query) {
        return R.successFind(baseService.list(query));
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "ID编辑", notes = "必须传递ID")
    public R<Boolean> upd(@PathVariable String id, @RequestBody @Validated AuthorityDTO dto) {
        return R.successUpdate(baseService.upd(id, dto));
    }


    @ApiOperation(value = "扫描权限", notes = "扫描权限列表数据, 1、存在变更接口描叙, 2、url变动会重新生成权限数据,角色原有的该接口权限会丢失,需重新分配 3、自动删除的多余接口")
    @PutMapping(value = "/refreshAuthority")
    public R<Boolean> refreshAuthority() {
        return R.success(baseService.refreshAuthDb());
    }
}
