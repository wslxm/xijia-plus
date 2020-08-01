package com.ws.ldy.modules.dev.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.common.result.Result;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.config.auth.util.JwtUtil;
import com.ws.ldy.enums.base.BaseConstant;
import com.ws.ldy.modules.dev.model.dto.DevBugDTO;
import com.ws.ldy.modules.dev.model.entity.DevBug;
import com.ws.ldy.modules.dev.model.vo.DevBugVO;
import com.ws.ldy.modules.dev.service.DevBugService;
import com.ws.ldy.others.base.controller.BaseController;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;


/**
 * TODO  Bug修复
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 *
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-06-27 11:14:57
 */
@RestController
@RequestMapping("/dev/devBug")
@Api(value = "DevBug", tags = "Bug修复", description = BaseConstant.InterfaceType.PC_ADMIN)
public class DevBugController extends BaseController<DevBugService> {


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query", example = "20")
    })
    public Result<IPage<DevBugVO>> findPage(
            @ApiParam(value = "任务名", required = false) @RequestParam(required = false) String name,
            @ApiParam(value = "任务类型(1-管理端 2-用户端 3-app端 4-全端)", required = false) @RequestParam(required = false) Integer type,
            @ApiParam(value = "任务状态(0-未开始 1-正在进行 2-已完成 3-已撤销)", required = false) @RequestParam(required = false) Integer state,
            @ApiParam(value = "项目字典code", required = false) @RequestParam(required = false) String item,
            @ApiParam(value = "指派人Id", required = false) @RequestParam(required = false) Integer taskUserId) {
        Page<DevBug> page = baseService.page(this.getPage(), new LambdaQueryWrapper<DevBug>()
                .orderByDesc(DevBug::getCreateTime)
                .like(StringUtils.isNotBlank(name), DevBug::getName, name)
                .eq(type != null, DevBug::getType, type)
                .eq(state != null, DevBug::getState, state)
                .eq(StringUtils.isNotBlank(item), DevBug::getItem, item)
                .eq(taskUserId != null, DevBug::getTaskUserId, taskUserId)

        );
        return Result.successFind(BeanDtoVoUtil.pageVo(page, DevBugVO.class));
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation("添加")
    public Result<Void> insert(@RequestBody @Validated DevBugDTO dto) {
        DevBug devBug = dto.convert(DevBug.class);
        devBug.setCreateUser(JwtUtil.getUserId(request.getHeader(BaseConstant.Sys.TOKEN)));
        devBug.setState(0);//默认未开始
        baseService.save(devBug);
        return Result.successInsert();
    }


    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    @ApiOperation("ID编辑")
    public Result<Void> update(@RequestBody @Validated DevBugDTO dto) {
        baseService.updateById(dto.convert(DevBug.class));
        return Result.successUpdate();
    }


    @RequestMapping(value = "/updState", method = RequestMethod.PUT)
    @ApiOperation("ID编辑状态--任务状态(0-未开始 1-正在进行 2-已完成 3-已撤销)")
    public Result<Void> update(@RequestParam String id, Integer state, Double takeUpTime) {
        DevBug devBug = new DevBug();
        devBug.setState(state);
        if (state == 2) {
            devBug.setEntTime(LocalDateTime.now());
            devBug.setTakeUpTime(takeUpTime);
        } else if (state == 3) {
            devBug.setEntTime(null);
            devBug.setTakeUpTime(null);
        }
        devBug.setId(id);
        baseService.updateById(devBug);
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
