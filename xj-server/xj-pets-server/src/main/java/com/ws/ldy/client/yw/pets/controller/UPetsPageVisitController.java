package com.ws.ldy.client.yw.pets.controller;

import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.config.auth.util.JwtUtil;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.yw.pets.model.dto.PetsPageVisitDTO;
import com.ws.ldy.modules.yw.pets.model.entity.PetsPageVisit;
import com.ws.ldy.modules.yw.pets.service.PetsPageVisitService;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 页面访问记录表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:54:16
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiClient + "/pets/petsPageVisit")
@Api(value = "UPetsPageVisitController", tags = "yh--页面访问记录表")
public class UPetsPageVisitController extends BaseController<PetsPageVisitService> {


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "页面统计-数据添加", notes = "必须不传递ID")
    public R<Boolean> insert(@RequestBody @Validated PetsPageVisitDTO dto) {
        if (StringUtils.isNotBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_FALSE);
        }
        PetsPageVisit petsPageVisit = dto.convert(PetsPageVisit.class);
        petsPageVisit.setIp(getIpAddress(request));
        petsPageVisit.setUserId(JwtUtil.getJwtUser(request).getUserId());
        return R.successInsert(baseService.save(petsPageVisit));
    }


}
