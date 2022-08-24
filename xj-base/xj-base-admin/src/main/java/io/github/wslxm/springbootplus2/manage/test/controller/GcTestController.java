package io.github.wslxm.springbootplus2.manage.test.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.wslxm.springbootplus2.core.result.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import io.github.wslxm.springbootplus2.manage.test.model.vo.GcTestVO;
import io.github.wslxm.springbootplus2.manage.test.model.dto.GcTestDTO;
import io.github.wslxm.springbootplus2.manage.test.model.query.GcTestQuery;
import io.github.wslxm.springbootplus2.manage.test.service.GcTestService;
import io.github.wslxm.springbootplus2.core.base.controller.BaseController;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;

/**
 * 代码生成测试表 前端控制器
 *
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 *
 * @author ws
 * @email 1720696548@qq.com
 * @date 2022-06-30 11:07:08
 */
@RestController
@RequestMapping(BaseConstant.Uri.API_ADMIN + "/test/gcTest")
@Api(value = "GcTestController", tags = "代码生成测试表")
public class GcTestController extends BaseController<GcTestService> {

    @GetMapping(value = "/list")
    @ApiOperation(value = "列表查询")
    public Result<IPage<GcTestVO>> list(@ModelAttribute @Validated GcTestQuery query) {
        return Result.success(baseService.list(query));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "ID查询")
    public Result<GcTestVO> findId(@PathVariable String id) {
        return Result.success(baseService.findId(id));
    }

    @PostMapping
    @ApiOperation(value = "添加")
    public Result<String> insert(@RequestBody @Validated GcTestDTO dto) {
        return Result.successInsert(baseService.insert(dto));
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "ID编辑")
    public Result<Boolean> upd(@PathVariable String id, @RequestBody @Validated GcTestDTO dto) {
        return Result.successUpdate(baseService.upd(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "ID删除")
    public Result<Boolean> del(@PathVariable String id) {
        return Result.successDelete(baseService.del(id));
    }
}
