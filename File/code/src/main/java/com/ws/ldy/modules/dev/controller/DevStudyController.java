package com.ws.ldy.modules.dev.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.ws.ldy.modules.dev.model.entity.DevStudy;
import com.ws.ldy.modules.dev.model.vo.DevStudyVO;
import com.ws.ldy.modules.dev.model.dto.DevStudyDTO;
import com.ws.ldy.modules.dev.service.DevStudyService;
import com.ws.ldy.common.result.Result;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.others.base.controller.BaseController;
import java.util.Arrays;
import java.time.LocalDateTime;


/**
 * 学习计划
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-07-31 00:36:32
 */
@RestController
@RequestMapping("/dev/devStudy")
@Api(value ="DevStudy" ,tags = "学习计划",description = "学习计划")
public class DevStudyController extends BaseController<DevStudyService>  {


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query",example = "1"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query",example = "20")
    })
    public Result<IPage<DevStudyVO>> findPage() {
        Page<DevStudy> page = baseService.page(this.getPage(), new LambdaQueryWrapper<DevStudy>()
                .orderByAsc(DevStudy::getId)

        );
        return Result.successFind(BeanDtoVoUtil.pageVo(page, DevStudyVO.class));
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation("添加")
    public Result<Void> insert(@RequestBody @Validated DevStudyDTO dto) {
        DevStudy devStudy = dto.convert(DevStudy.class);
        baseService.save(devStudy);
        return Result.successInsert();
    }


    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    @ApiOperation("ID编辑")
    public Result<Void> update(@RequestBody @Validated DevStudyDTO dto) {
        baseService.updateById(dto.convert(DevStudy.class));
        return Result.successUpdate();
    }


    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    @ApiOperation("单删除")
    public Result<Void> delete(@RequestParam Integer id) {
        baseService.removeById(id);
        return Result.successDelete();
    }


    @RequestMapping(value = "/delByIds", method = RequestMethod.DELETE)
    @ApiOperation("批量删除")
    public Result<Void> deleteByIds(@RequestParam Integer[] ids) {
        baseService.removeByIds(Arrays.asList(ids));
        return Result.successDelete();
    }
}
