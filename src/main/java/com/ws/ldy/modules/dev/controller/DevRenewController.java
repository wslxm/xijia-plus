package com.ws.ldy.modules.dev.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.others.base.controller.BaseController;
import com.ws.ldy.common.result.Result;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.modules.dev.model.dto.DevRenewDTO;
import com.ws.ldy.modules.dev.model.entity.DevRenew;
import com.ws.ldy.modules.dev.model.vo.DevRenewVO;
import com.ws.ldy.modules.dev.service.DevRenewService;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


/**
 * TODO  更新内容
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-06-27 12:23:10
 */
@RestController
@RequestMapping("/dev/devRenew")
@Api(value ="DevRenew" ,tags = "更新内容",description = "更新内容")
public class DevRenewController extends BaseController<DevRenewService>  {


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query",example = "1"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query",example = "20")
    })
    public Result<IPage<DevRenewVO>> findPage( 
            @ApiParam(value = "更新名称",required = false) @RequestParam(required = false) String name,
            @ApiParam(value = "更新类型(1-管理端 2-用户端 3-app端)",required = false) @RequestParam(required = false) Integer type) {
        Page<DevRenew> page = baseService.page(this.getPage(), new LambdaQueryWrapper<DevRenew>()
                .orderByDesc(DevRenew::getCreateTime)
                .eq(StringUtils.isNotBlank(name),DevRenew::getName,name)
                .eq(type != null,DevRenew::getType,type)

        );
        return Result.successFind(BeanDtoVoUtil.pageVo(page, DevRenewVO.class));
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation("添加")
    public Result<Void> insert(@RequestBody @Validated DevRenewDTO dto) {
        DevRenew devRenew = dto.convert(DevRenew.class);
        baseService.save(devRenew);
        return Result.successInsert();
    }


    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    @ApiOperation("ID编辑")
    public Result<Void> update(@RequestBody @Validated DevRenewDTO dto) {
        baseService.updateById(dto.convert(DevRenew.class));
        return Result.successUpdate();
    }


    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    @ApiOperation("单删除")
    public Result<Void> delete(@RequestParam String id) {
        baseService.removeById(id);
        return Result.successDelete();
    }


    @RequestMapping(value = "/delByIds", method = RequestMethod.DELETE)
    @ApiOperation("批量删除")
    public Result<Void> deleteByIds(@RequestParam String[] ids) {
        baseService.removeByIds(Arrays.asList(ids));
        return Result.successDelete();
    }
}
