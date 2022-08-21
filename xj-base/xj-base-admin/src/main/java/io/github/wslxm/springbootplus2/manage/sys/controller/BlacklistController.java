package io.github.wslxm.springbootplus2.manage.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.wslxm.springbootplus2.core.base.controller.BaseController;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.R;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.BlacklistDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.query.BlacklistQuery;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.BlacklistVO;
import io.github.wslxm.springbootplus2.manage.sys.service.BlacklistService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 黑名单
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-11-27 22:44:49
 */
@RestController
@RequestMapping(BaseConstant.Uri.API_ADMIN+ "/sys/blacklist")
@Api(value = "BlacklistController", tags = "base--sys--黑名单")
public class BlacklistController extends BaseController<BlacklistService> {


    @GetMapping(value = "/findPage")
    @ApiOperation(value = "列表查询")
    public R<IPage<BlacklistVO>> findPage(@ModelAttribute @Validated BlacklistQuery query) {
        return R.success(baseService.findPage(query));
    }


    @PostMapping
    @ApiOperation(value = "添加")
    public R<String> insert(@RequestBody @Validated BlacklistDTO dto) {
        return R.successInsert(baseService.insert(dto));
    }


    @PutMapping(value = "/{id}")
    @ApiOperation(value = "ID编辑")
    public R<Boolean> upd(@PathVariable String id, @RequestBody @Validated BlacklistDTO dto) {
        return R.successUpdate(baseService.upd(id, dto));
    }


    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "ID删除")
    public R<Boolean> del(@PathVariable String id) {
        return R.successDelete(baseService.del(id));
    }
}
