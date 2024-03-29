package io.github.wslxm.springbootplus2.manage.admin.controller;

import io.github.wslxm.springbootplus2.manage.admin.model.dto.AdminOrganDTO;
import io.github.wslxm.springbootplus2.manage.admin.model.vo.AdminOrganVO;
import io.github.wslxm.springbootplus2.core.base.controller.BaseController;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.R;
import io.github.wslxm.springbootplus2.manage.admin.model.query.AdminOrganQuery;
import io.github.wslxm.springbootplus2.manage.admin.service.AdminOrganService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 基础表--组织机构
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 * @author ws
 * @email 1720696548@qq.com
 * @date 2021-09-30 16:10:57
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin + "/organ")
@Api(value = "AdminOrganController", tags = "base--组织机构")
public class AdminOrganController extends BaseController<AdminOrganService> {

    @GetMapping(value = "/list")
    @ApiOperation(value = "列表查询")
    public R<List<AdminOrganVO>> list(@ModelAttribute @Validated AdminOrganQuery query) {
        return R.success(baseService.list(query));
    }

    @PostMapping
    @ApiOperation(value = "添加")
    public R<String> insert(@RequestBody @Validated AdminOrganDTO dto) {
        return R.successInsert(baseService.insert(dto));
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "ID编辑")
    public R<Boolean> upd(@PathVariable String id, @RequestBody @Validated AdminOrganDTO dto) {
        return R.successUpdate(baseService.upd(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "ID删除(并删除子数据)")
    public R<Boolean> del(@PathVariable String id) {
        return R.successDelete(baseService.del(id));
    }
}
