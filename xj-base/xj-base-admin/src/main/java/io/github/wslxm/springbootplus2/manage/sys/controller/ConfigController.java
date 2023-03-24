package io.github.wslxm.springbootplus2.manage.sys.controller;

import io.github.wslxm.springbootplus2.core.base.model.BasePage;
import io.github.wslxm.springbootplus2.core.base.controller.BaseController;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.Result;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.ConfigDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.query.ConfigQuery;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.ConfigVO;
import io.github.wslxm.springbootplus2.manage.sys.service.ConfigService;
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
@RequestMapping(BaseConstant.Uri.API_ADMIN + "/sys/config")
@Api(value = "ConfigController", tags = "base--sys--全局配置")
public class ConfigController extends BaseController<ConfigService> {


    @GetMapping(value = "/findPage")
    @ApiOperation(value = "分页查询")
    public Result<BasePage<ConfigVO>> findPage(@ModelAttribute @Validated ConfigQuery query) {
        return Result.success(baseService.findPage(query));
    }


    @GetMapping(value = "/{id}")
    @ApiOperation(value = "ID查询")
    public Result<ConfigVO> findId(@PathVariable String id) {
        return Result.successUpdate(baseService.findId(id));
    }


    @PostMapping
    @ApiOperation(value = "添加")
    public Result<String> insert(@RequestBody @Validated ConfigDTO dto) {
        return Result.successInsert(baseService.insert(dto));
    }


    @PutMapping(value = "/{id}")
    @ApiOperation(value = "ID编辑")
    public Result<Boolean> upd(@PathVariable String id, @RequestBody @Validated ConfigDTO dto) {
        return Result.successUpdate(baseService.upd(id, dto));
    }


    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "ID删除")
    public Result<Boolean> del(@PathVariable String id) {
        return Result.successDelete(baseService.del(id));
    }


    @RequestMapping(value = "/findByCode", method = RequestMethod.GET)
    @ApiOperation(value = "CODE查询")
    public Result<ConfigVO> findByCode(@RequestParam String code) {
        return Result.successFind(baseService.findByCode(code));
    }

}
