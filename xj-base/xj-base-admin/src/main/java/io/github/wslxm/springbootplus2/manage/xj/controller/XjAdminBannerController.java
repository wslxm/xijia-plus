package io.github.wslxm.springbootplus2.manage.xj.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.wslxm.springbootplus2.core.base.controller.BaseController;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.R;
import io.github.wslxm.springbootplus2.manage.xj.model.dto.XjAdminBannerDTO;
import io.github.wslxm.springbootplus2.manage.xj.model.query.XjAdminBannerQuery;
import io.github.wslxm.springbootplus2.manage.xj.model.vo.XjAdminBannerVO;
import io.github.wslxm.springbootplus2.manage.xj.service.XjAdminBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * banner表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-08-23 23:14:01
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin + "/xj/banner")
@Api(value = "XjAdminBannerController", tags = "base-plus--banner")
public class XjAdminBannerController extends BaseController<XjAdminBannerService> {

    @GetMapping(value = "/list")
    @ApiOperation(value = "列表查询")
    public R<IPage<XjAdminBannerVO>> list(@ModelAttribute @Validated XjAdminBannerQuery query) {
        return R.success(baseService.list(query));
    }

    @PostMapping
    @ApiOperation(value = "添加")
    public R<String> insert(@RequestBody @Validated XjAdminBannerDTO dto) {
        return R.successInsert(baseService.insert(dto));
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "ID编辑")
    public R<Boolean> upd(@PathVariable String id, @RequestBody @Validated XjAdminBannerDTO dto) {
        return R.successUpdate(baseService.upd(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "ID删除")
    public R<Boolean> del(@PathVariable String id) {
        return R.successDelete(baseService.del(id));
    }

}
