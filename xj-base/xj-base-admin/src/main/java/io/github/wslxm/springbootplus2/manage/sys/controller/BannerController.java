package io.github.wslxm.springbootplus2.manage.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.wslxm.springbootplus2.core.base.controller.BaseController;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.R;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.BannerDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.query.BannerQuery;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.BannerVO;
import io.github.wslxm.springbootplus2.manage.sys.service.BannerService;
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
@RequestMapping(BaseConstant.Uri.API_ADMIN+ "/sys/banner")
@Api(value = "BannerController", tags = "base--sys--banner")
public class BannerController extends BaseController<BannerService> {

    @GetMapping(value = "/findPage")
    @ApiOperation(value = "列表查询")
    public R<IPage<BannerVO>> findPage(@ModelAttribute @Validated BannerQuery query) {
        return R.success(baseService.findPage(query));
    }

    @PostMapping
    @ApiOperation(value = "添加")
    public R<String> insert(@RequestBody @Validated BannerDTO dto) {
        return R.successInsert(baseService.insert(dto));
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "ID编辑")
    public R<Boolean> upd(@PathVariable String id, @RequestBody @Validated BannerDTO dto) {
        return R.successUpdate(baseService.upd(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "ID删除")
    public R<Boolean> del(@PathVariable String id) {
        return R.successDelete(baseService.del(id));
    }

}
