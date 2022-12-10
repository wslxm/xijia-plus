package io.github.wslxm.springbootplus2.manage.sys.controller;

import io.github.wslxm.springbootplus2.core.result.Result;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.DepDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.DepVO;
import io.github.wslxm.springbootplus2.core.base.controller.BaseController;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.manage.sys.model.query.DepQuery;
import io.github.wslxm.springbootplus2.manage.sys.service.DepService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * base--sys--组织机构
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 *
 * @author ws
 * @email 1720696548@qq.com
 * @date 2021-09-30 16:10:57
 */
@RestController
@RequestMapping(BaseConstant.Uri.API_ADMIN + "/sys/dep")
public class DepController extends BaseController<DepService> {

    /**
     * 列表查询
     *
     * @param query
     * @return io.github.wslxm.springbootplus2.core.result.Result<java.util.List < io.github.wslxm.springbootplus2.manage.sys.model.vo.DepVO>>
     * @author wangsong
     */
    @GetMapping(value = "/list")
    public Result<List<DepVO>> list(@ModelAttribute @Validated DepQuery query) {
        return Result.success(baseService.list(query));
    }

    /**
     * 添加
     *
     * @param dto
     * @return io.github.wslxm.springbootplus2.core.result.Result<java.lang.String>
     * @author wangsong
     */
    @PostMapping
    public Result<String> insert(@RequestBody @Validated DepDTO dto) {
        return Result.successInsert(baseService.insert(dto));
    }

    /**
     * ID编辑
     *
     * @param id
     * @param dto
     * @return io.github.wslxm.springbootplus2.core.result.Result<java.lang.Boolean>
     * @author wangsong
     */
    @PutMapping(value = "/{id}")
    public Result<Boolean> upd(@PathVariable String id, @RequestBody @Validated DepDTO dto) {
        return Result.successUpdate(baseService.upd(id, dto));
    }

    /**
     * ID删除(并删除子数据)
     *
     * @param id
     * @return io.github.wslxm.springbootplus2.core.result.Result<java.lang.Boolean>
     * @author wangsong
     */
    @DeleteMapping(value = "/{id}")
    public Result<Boolean> del(@PathVariable String id) {
        return Result.successDelete(baseService.del(id));
    }
}
