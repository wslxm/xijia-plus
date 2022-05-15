package io.github.wslxm.springbootplus2.manage.xj.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.wslxm.springbootplus2.core.base.controller.BaseController;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.R;
import io.github.wslxm.springbootplus2.manage.xj.model.dto.XjAdminBannerDTO;
import io.github.wslxm.springbootplus2.manage.xj.model.dto.XjAdminConfigDTO;
import io.github.wslxm.springbootplus2.manage.xj.model.query.XjAdminConfigQuery;
import io.github.wslxm.springbootplus2.manage.xj.model.vo.XjAdminConfigVO;
import io.github.wslxm.springbootplus2.manage.xj.service.XjAdminConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 系统全局数据信息配置表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-08-31 18:31:44
 */
@RestController
@RequestMapping(BaseConstant.Uri.API_ADMIN+ "/xj/config")
@Api(value = "XjAdminConfigController", tags = "base-plus--全局配置")
public class XjAdminConfigController extends BaseController<XjAdminConfigService> {


    @GetMapping(value = "/list")
    @ApiOperation(value = "分页查询")
    public R<IPage<XjAdminConfigVO>> list(@ModelAttribute @Validated XjAdminConfigQuery query) {
        return R.success(baseService.list(query));
    }


    @GetMapping(value = "/{id}")
    @ApiOperation(value = "ID查询")
    public R<XjAdminConfigVO> findId(@PathVariable String id) {
        return R.successUpdate(baseService.findId(id));
    }


    @PostMapping
    @ApiOperation(value = "添加")
    public R<String> insert(@RequestBody @Validated XjAdminConfigDTO dto) {
        return R.successInsert(baseService.insert(dto));
    }


    @PutMapping(value = "/{id}")
    @ApiOperation(value = "ID编辑")
    public R<Boolean> upd(@PathVariable String id, @RequestBody @Validated XjAdminConfigDTO dto) {
        return R.successUpdate(baseService.upd(id, dto));
    }


    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "ID删除")
    public R<Boolean> del(@PathVariable String id) {
        return R.successDelete(baseService.removeById(id));
    }


    @RequestMapping(value = "/findByCode", method = RequestMethod.GET)
    @ApiOperation(value = "CODE查询")
    public R<XjAdminConfigVO> findByCode(@RequestParam String code) {
        return R.successFind(baseService.findByCode(code));
    }

}
