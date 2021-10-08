package com.ws.ldy.manage.admin.controller;

import com.ws.ldy.core.base.controller.BaseController;
import com.ws.ldy.core.constant.BaseConstant;
import com.ws.ldy.core.result.R;
import com.ws.ldy.manage.admin.model.dto.AdminOgranDTO;
import com.ws.ldy.manage.admin.model.query.AdminOgranQuery;
import com.ws.ldy.manage.admin.model.vo.AdminOgranVO;
import com.ws.ldy.manage.admin.service.AdminOgranService;
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
@RequestMapping(BaseConstant.Uri.apiAdmin + "/ogran")
@Api(value = "AdminOgranController", tags = "基础表--组织机构")
public class AdminOgranController extends BaseController<AdminOgranService> {

    @GetMapping(value = "/list")
    @ApiOperation(value = "列表查询")
    public R<List<AdminOgranVO>> list(@ModelAttribute @Validated AdminOgranQuery query) {
        return R.success(baseService.list(query));
    }

    @PostMapping
    @ApiOperation(value = "添加")
    public R<String> insert(@RequestBody @Validated AdminOgranDTO dto) {
        return R.successInsert(baseService.insert(dto));
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "ID编辑")
    public R<Boolean> upd(@PathVariable String id, @RequestBody @Validated AdminOgranDTO dto) {
        return R.successUpdate(baseService.upd(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "ID删除(并删除子数据)")
    public R<Boolean> del(@PathVariable String id) {
        return R.successDelete(baseService.del(id));
    }
}
