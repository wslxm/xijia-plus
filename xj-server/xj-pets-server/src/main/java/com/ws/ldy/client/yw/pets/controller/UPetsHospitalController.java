package com.ws.ldy.client.yw.pets.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.enums.Enums;
import com.ws.ldy.modules.yw.pets.model.dto.PetsHospitalDTO;
import com.ws.ldy.modules.yw.pets.model.entity.PetsHospital;
import com.ws.ldy.modules.yw.pets.model.vo.PetsHospitalVO;
import com.ws.ldy.modules.yw.pets.service.PetsHospitalService;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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
@RequestMapping(BaseConstant.Uri.apiClient + "/pets/petsHospital")
@Api(value = "UPetsHospitalController", tags = "yh--医院表")
public class UPetsHospitalController extends BaseController<PetsHospitalService> {


    @RequestMapping(value = "/findList", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有医院列表", notes = "")
    public R<List<PetsHospitalVO>> findList() {
        List<PetsHospital> petsHospital = baseService.list(new LambdaQueryWrapper<PetsHospital>()
                .orderByDesc(PetsHospital::getCreateTime)
                .eq(PetsHospital::getDisable, Enums.Base.Disable.DISABLE_0.getValue())
        );
        List<PetsHospitalVO> petsHospitalVOS = BeanDtoVoUtil.listVo(petsHospital, PetsHospitalVO.class);
        return R.success(petsHospitalVOS);
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "添加-申报时添加医院", notes = "必须不传递ID")
    public R<Boolean> insert(@RequestBody @Validated PetsHospitalDTO dto) {
        if (StringUtils.isNotBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_FALSE);
        }
        return R.success(baseService.insert(dto));
    }
}
