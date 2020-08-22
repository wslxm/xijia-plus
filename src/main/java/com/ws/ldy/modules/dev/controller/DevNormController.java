package com.ws.ldy.modules.dev.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.dev.model.dto.DevNormDTO;
import com.ws.ldy.modules.dev.model.entity.DevNorm;
import com.ws.ldy.modules.dev.model.vo.DevNormVO;
import com.ws.ldy.modules.dev.service.DevNormService;
import com.ws.ldy.others.base.controller.BaseController;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


/**
 * 开发规范
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-07-31 15:14:20
 */
@RestController
@RequestMapping("/dev/devNorm")
@Api(value ="DevNorm" ,tags = "开发规范",consumes = BaseConstant.InterfaceType.PC_ADMIN)
public class DevNormController extends BaseController<DevNormService>  {


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query",example = "1"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query",example = "20")
    })
    public R<IPage<DevNormVO>> findPage(
            @ApiParam(value = "规范名称",required = false) @RequestParam(required = false) String name) {
        Page<DevNorm> page = baseService.page(this.getPage(), new LambdaQueryWrapper<DevNorm>()
                .orderByAsc(DevNorm::getId)
                .eq(StringUtils.isNotBlank(name),DevNorm::getName,name)

        );
        return R.successFind(BeanDtoVoUtil.pageVo(page, DevNormVO.class));
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "添加", notes = "")
    public R<Void> insert(@RequestBody @Validated DevNormDTO dto) {
        DevNorm devNorm = dto.convert(DevNorm.class);
        baseService.save(devNorm);
        return R.successInsert();
    }


    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    @ApiOperation(value = "ID编辑", notes = "")
    public R<Void> update(@RequestBody @Validated DevNormDTO dto) {
        baseService.updateById(dto.convert(DevNorm.class));
        return R.successUpdate();
    }


    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    @ApiOperation(value = "ID删除", notes = "")
    public R<Void> del(@RequestParam String id) {
        baseService.removeById(id);
        return R.successDelete();
    }


    @RequestMapping(value = "/delByIds", method = RequestMethod.DELETE)
    @ApiOperation(value = "批量ID删除", notes = "")
    public R<Void> delByIds(@RequestParam String[] ids) {
        baseService.removeByIds(Arrays.asList(ids));
        return R.successDelete();
    }
}
