package com.ws.ldy.modules.caipu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.ws.ldy.enums.BaseConstant;

import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import com.ws.ldy.config.error.ErrorException;
import org.springframework.format.annotation.DateTimeFormat;

import com.ws.ldy.modules.caipu.model.entity.CpInfo;
import com.ws.ldy.modules.caipu.model.vo.CpInfoVO;
import com.ws.ldy.modules.caipu.model.dto.CpInfoDTO;
import com.ws.ldy.modules.caipu.service.CpInfoService;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.others.base.controller.BaseController;
import java.util.Arrays;
import java.time.LocalDateTime;


/**
 * 菜谱表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-10-04 18:50:10
 */
@RestController
@RequestMapping("/caipu/cpInfo")
@Api(value ="CpInfo" ,tags = "菜谱表", consumes = BaseConstant.InterfaceType.PC_ADMIN)
public class CpInfoController extends BaseController<CpInfoService>  {


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询", notes= "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页数", required = true, paramType = "query",example = "1"),
            @ApiImplicitParam(name = "size", value = "记录数", required = true, paramType = "query",example = "20"),
 
    })
    public R<IPage<CpInfoVO>> findPage(
                                              ) {
        Page<CpInfo> page = baseService.page(this.getPage(), new LambdaQueryWrapper<CpInfo>()
                .orderByDesc(CpInfo::getCreateTime)
 
        );
        return R.successFind(BeanDtoVoUtil.pageVo(page, CpInfoVO.class));
    }

    @RequestMapping(value = "/findId", method = RequestMethod.GET)
    @ApiOperation(value = "ID查询", notes= "")
    public R<CpInfoVO> findId(@RequestParam String id) {
        return R.successFind(BeanDtoVoUtil.convert( baseService.getById(id),CpInfoVO.class));
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "添加", notes= "必须不传递ID")
    public R<Boolean> insert(@RequestBody @Validated CpInfoDTO dto) {
        if (StringUtils.isNotBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_FALSE);
        }
        CpInfo cpInfo = dto.convert(CpInfo.class);
        return R.successInsert(baseService.save(cpInfo));
    }


    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    @ApiOperation(value = "ID编辑", notes= "必须传递ID")
    public R<Boolean> upd(@RequestBody @Validated CpInfoDTO dto) {
        if (StringUtils.isBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_TRUE);
        }
        return R.successUpdate(baseService.updateById(dto.convert(CpInfo.class)));
    }


    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    @ApiOperation(value = "ID删除", notes= "")
    public R<Boolean> del(@RequestParam String id) {
        return R.successDelete(baseService.removeById(id));
    }


    @RequestMapping(value = "/delByIds", method = RequestMethod.DELETE)
    @ApiOperation(value = "批量ID删除", notes= "")
    public R<Boolean> delByIds(@RequestParam String[] ids) {
        return R.successDelete(baseService.removeByIds(Arrays.asList(ids)));
    }
}
