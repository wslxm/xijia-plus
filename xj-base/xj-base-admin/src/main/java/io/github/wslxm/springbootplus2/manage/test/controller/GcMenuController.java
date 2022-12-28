package io.github.wslxm.springbootplus2.manage.test.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import io.github.wslxm.springbootplus2.manage.test.model.vo.GcMenuVO;
import io.github.wslxm.springbootplus2.manage.test.model.dto.GcMenuDTO;
import io.github.wslxm.springbootplus2.manage.test.model.query.GcMenuQuery;
import io.github.wslxm.springbootplus2.manage.test.service.GcMenuService;
import io.github.wslxm.springbootplus2.core.base.controller.BaseController;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.Result;
import java.util.List;

/**
 * 基础表--菜单 前端控制器
 *
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 *
 * @author ws
 * @email 1720696548@qq.com
 * @date 2022-12-28 17:38:58
 */
@RestController
@RequestMapping(BaseConstant.Uri.API_ADMIN + "/test/gcMenu")
@Api(value = "GcMenuController", tags = "基础表--菜单")
public class GcMenuController extends BaseController<GcMenuService> {

    @GetMapping(value = "/tree")
    @ApiOperation(value = "树结构数据")
    public Result<List<GcMenuVO>> tree(@ModelAttribute @Validated GcMenuQuery query) {
        return Result.success(baseService.tree(query));
    }

    @GetMapping(value = "/findPage")
    @ApiOperation(value = "列表查询")
    public Result<IPage<GcMenuVO>> findPage(@ModelAttribute @Validated GcMenuQuery query) {
        return Result.success(baseService.findPage(query));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "ID查询")
    public Result<GcMenuVO> findId(@PathVariable String id) {
        return Result.success(baseService.findId(id));
    }

    @PostMapping
    @ApiOperation(value = "添加")
    public Result<String> insert(@RequestBody @Validated GcMenuDTO dto) {
        return Result.successInsert(baseService.insert(dto));
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "ID编辑")
    public Result<Boolean> upd(@PathVariable String id, @RequestBody @Validated GcMenuDTO dto) {
        return Result.successUpdate(baseService.upd(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "ID删除")
    public Result<Boolean> del(@PathVariable String id) {
        return Result.successDelete(baseService.del(id));
    }

}
