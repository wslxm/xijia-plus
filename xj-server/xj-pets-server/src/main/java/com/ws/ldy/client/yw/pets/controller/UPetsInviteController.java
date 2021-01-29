package com.ws.ldy.client.yw.pets.controller;

import com.ws.ldy.common.result.R;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.yw.pets.model.dto.PetsInviteDTO;
import com.ws.ldy.modules.yw.pets.service.PetsInviteService;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 邀请表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:53:38
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiClient + "/pets/petsInvite")
@Api(value = "UPetsInviteController", tags = "yh--邀请表")
public class UPetsInviteController extends BaseController<PetsInviteService> {


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "邀请--添加", notes = "必须不传递ID")
    public R<Boolean> insert(@RequestBody @Validated PetsInviteDTO dto) {
        return R.success( baseService.insert(dto));
    }


}
