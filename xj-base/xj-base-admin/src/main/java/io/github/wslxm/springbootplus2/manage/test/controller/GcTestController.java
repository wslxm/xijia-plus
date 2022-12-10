package io.github.wslxm.springbootplus2.manage.test.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.github.wslxm.springbootplus2.manage.test.model.vo.GcTestVO;
import io.github.wslxm.springbootplus2.manage.test.model.dto.GcTestDTO;
import io.github.wslxm.springbootplus2.manage.test.model.query.GcTestQuery;
import io.github.wslxm.springbootplus2.manage.test.service.GcTestService;
import io.github.wslxm.springbootplus2.core.base.controller.BaseController;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.Result;

/**
 * 代码生成测试表 前端控制器
 *
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 *
 * @author ws
 * @email 1720696548@qq.com
 * @date 2022-09-09 18:26:48
 */
@RestController
@RequestMapping(BaseConstant.Uri.API_ADMIN + "/test/gcTest")
public class GcTestController extends BaseController<GcTestService> {

    /**
     * 列表查询
     * @author wangsong
     * @param query
     */
    @GetMapping(value = "/findPage")
    public Result<IPage<GcTestVO>> findPage(@ModelAttribute @Validated GcTestQuery query) {
        return Result.success(baseService.findPage(query));
    }

    /**
     * ID查询
     * @author wangsong
     * @param query
     */
    @GetMapping(value = "/{id}")
    public Result<GcTestVO> findId(@PathVariable String id) {
        return Result.success(baseService.findId(id));
    }

    /**
     * 添加
     * @author wangsong
     * @param query
     */
    @PostMapping
    public Result<String> insert(@RequestBody @Validated GcTestDTO dto) {
        return Result.successInsert(baseService.insert(dto));
    }

    /**
     * ID编辑
     * @author wangsong
     * @param query
     */
    @PutMapping(value = "/{id}")
    public Result<Boolean> upd(@PathVariable String id, @RequestBody @Validated GcTestDTO dto) {
        return Result.successUpdate(baseService.upd(id, dto));
    }

    /**
     * ID删除
     * @author wangsong
     * @param query
     */
    @DeleteMapping(value = "/{id}")
    public Result<Boolean> del(@PathVariable String id) {
        return Result.successDelete(baseService.del(id));
    }

}
