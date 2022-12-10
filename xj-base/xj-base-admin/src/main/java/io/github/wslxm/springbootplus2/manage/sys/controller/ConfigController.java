package io.github.wslxm.springbootplus2.manage.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.wslxm.springbootplus2.core.base.controller.BaseController;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.Result;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.ConfigDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.query.ConfigQuery;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.ConfigVO;
import io.github.wslxm.springbootplus2.manage.sys.service.ConfigService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * base--sys--全局配置
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-08-31 18:31:44
 */
@RestController
@RequestMapping(BaseConstant.Uri.API_ADMIN + "/sys/config")
public class ConfigController extends BaseController<ConfigService> {


    /**
     * 分页查询
     * @author wangsong
     * @param query
     * @return io.github.wslxm.springbootplus2.core.result.Result<com.baomidou.mybatisplus.core.metadata.IPage<io.github.wslxm.springbootplus2.manage.sys.model.vo.ConfigVO>>
     */
    @GetMapping(value = "/findPage")
    public Result<IPage<ConfigVO>> findPage(@ModelAttribute @Validated ConfigQuery query) {
        return Result.success(baseService.findPage(query));
    }


    /**
     * ID查询
     * @author wangsong
     * @param id
     * @return io.github.wslxm.springbootplus2.core.result.Result<io.github.wslxm.springbootplus2.manage.sys.model.vo.ConfigVO>
     */
    @GetMapping(value = "/{id}")
    public Result<ConfigVO> findId(@PathVariable String id) {
        return Result.successUpdate(baseService.findId(id));
    }


    /**
     * 添加
     * @author wangsong
     * @param dto
     * @return io.github.wslxm.springbootplus2.core.result.Result<java.lang.String>
     */
    @PostMapping
    public Result<String> insert(@RequestBody @Validated ConfigDTO dto) {
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
    public Result<Boolean> upd(@PathVariable String id, @RequestBody @Validated ConfigDTO dto) {
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


    /**
     * CODE查询
     * @author wangsong
     * @param code
     * @return io.github.wslxm.springbootplus2.core.result.Result<io.github.wslxm.springbootplus2.manage.sys.model.vo.ConfigVO>
     */
    @RequestMapping(value = "/findByCode", method = RequestMethod.GET)
    public Result<ConfigVO> findByCode(@RequestParam String code) {
        return Result.successFind(baseService.findByCode(code));
    }

}
