package io.github.wslxm.springbootplus2.manage.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.wslxm.springbootplus2.core.base.controller.BaseController;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.Result;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.BannerDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.query.BannerQuery;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.BannerVO;
import io.github.wslxm.springbootplus2.manage.sys.service.BannerService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * base--sys--banner
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-08-23 23:14:01
 */
@RestController
@RequestMapping(BaseConstant.Uri.API_ADMIN+ "/sys/banner")
public class BannerController extends BaseController<BannerService> {

    /**
     * 列表查询
     * @param query
     * @return
     */
    @GetMapping(value = "/findPage")
    public Result<IPage<BannerVO>> findPage(@ModelAttribute @Validated BannerQuery query) {
        return Result.success(baseService.findPage(query));
    }

    /**
     * 添加
     * @author wangsong
     * @param dto
     * @return io.github.wslxm.springbootplus2.core.result.Result<java.lang.String>
     */
    @PostMapping
    public Result<String> insert(@RequestBody @Validated BannerDTO dto) {
        return Result.successInsert(baseService.insert(dto));
    }

    /**
     * ID编辑
     * @author wangsong
     * @param id
     * @param dto
     * @return io.github.wslxm.springbootplus2.core.result.Result<java.lang.Boolean>
     */
    @PutMapping(value = "/{id}")
    public Result<Boolean> upd(@PathVariable String id, @RequestBody @Validated BannerDTO dto) {
        return Result.successUpdate(baseService.upd(id, dto));
    }

    /**
     * ID删除
     * @author wangsong
     * @param id
     * @return io.github.wslxm.springbootplus2.core.result.Result<java.lang.Boolean>
     */
    @DeleteMapping(value = "/{id}")
    public Result<Boolean> del(@PathVariable String id) {
        return Result.successDelete(baseService.del(id));
    }

}
