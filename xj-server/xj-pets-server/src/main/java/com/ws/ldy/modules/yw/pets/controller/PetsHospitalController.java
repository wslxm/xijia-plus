package com.ws.ldy.modules.yw.pets.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.yw.pets.model.dto.PetsHospitalDTO;
import com.ws.ldy.modules.yw.pets.model.entity.PetsHospital;
import com.ws.ldy.modules.yw.pets.model.vo.PetsHospitalVO;
import com.ws.ldy.modules.yw.pets.service.PetsHospitalService;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 医院表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 11:26:04
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin + "/pets/petsHospital")
@Api(value = "PetsHospitalController", tags = "医院表")
public class PetsHospitalController extends BaseController<PetsHospitalService> {


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页数", required = true, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "size", value = "记录数", required = true, paramType = "query", example = "20"),
            @ApiImplicitParam(name = "name", value = "医院名称", required = false, paramType = "query", example = ""),
            @ApiImplicitParam(name = "disable", value = "1-禁用(黑名单) 0-启用", required = false, paramType = "query", example = ""),

    })
    public R<IPage<PetsHospitalVO>> findPage(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer disable
    ) {
        Page<PetsHospital> page = baseService.page(this.getPage(), new LambdaQueryWrapper<PetsHospital>()
                .orderByDesc(PetsHospital::getCreateTime)
                .eq(StringUtils.isNotBlank(name), PetsHospital::getName, name)
                .eq(disable != null, PetsHospital::getDisable, disable)
        );
        return R.successFind(BeanDtoVoUtil.pageVo(page, PetsHospitalVO.class));
    }

    @RequestMapping(value = "/findId", method = RequestMethod.GET)
    @ApiOperation(value = "ID查询", notes = "")
    public R<PetsHospitalVO> findId(@RequestParam String id) {
        return R.successFind(BeanDtoVoUtil.convert(baseService.getById(id), PetsHospitalVO.class));
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "添加", notes = "必须不传递ID")
    public R<Boolean> insert(@RequestBody @Validated PetsHospitalDTO dto) {
        if (StringUtils.isNotBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_FALSE);
        }
        PetsHospital petsHospital = dto.convert(PetsHospital.class);
        return R.successInsert(baseService.save(petsHospital));
    }


    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    @ApiOperation(value = "ID编辑", notes = "必须传递ID")
    public R<Boolean> upd(@RequestBody @Validated PetsHospitalDTO dto) {
        if (StringUtils.isBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_TRUE);
        }
        return R.successUpdate(baseService.updateById(dto.convert(PetsHospital.class)));
    }


    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    @ApiOperation(value = "ID删除", notes = "")
    public R<Boolean> del(@RequestParam String id) {
        return R.successDelete(baseService.removeById(id));
    }
}
