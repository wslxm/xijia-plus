package com.ws.ldy.client.yw.pets.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.config.auth.util.JwtUtil;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.yw.pets.model.dto.PetsPetInfoDTO;
import com.ws.ldy.modules.yw.pets.model.entity.PetsPetInfo;
import com.ws.ldy.modules.yw.pets.model.vo.PetsPetInfoVO;
import com.ws.ldy.modules.yw.pets.service.PetsPetInfoService;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 宠物表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:54:33
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiClient + "/pets/petsPetInfo")
@Api(value = "UPetsPetInfoController", tags = "yh--宠物表")
public class UPetsPetInfoController extends BaseController<PetsPetInfoService> {


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation(value = "宠物列表(世界-所有)", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页数", required = true, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "size", value = "记录数", required = true, paramType = "query", example = "20"),
            @ApiImplicitParam(name = "type", value = "类别(字典code)", required = false, paramType = "query", example = ""),

    })
    public R<IPage<PetsPetInfoVO>> findPage(@RequestParam(required = false) String type) {
        Page<PetsPetInfo> page = baseService.page(this.getPage(), new LambdaQueryWrapper<PetsPetInfo>()
                .orderByDesc(PetsPetInfo::getCreateTime)
                .ne(PetsPetInfo::getNickname, "")
                .eq(StringUtils.isNotBlank(type), PetsPetInfo::getType, type)
        );
        return R.successFind(BeanDtoVoUtil.pageVo(page, PetsPetInfoVO.class));
    }


    @RequestMapping(value = "/findList", method = RequestMethod.GET)
    @ApiOperation(value = "我的宠物列表", notes = "")
    @ApiImplicitParam(name = "isTemporary", value = "" +
            "是否查询临时宠物( \r\n" +
            "1、支付后或个人中心页跳转绑定宠物传 false , \r\n" +
            "2、个人页列表传 true)", required = false, paramType = "query", example = "")
    public R<List<PetsPetInfoVO>> findList(@RequestParam(required = true) Boolean isTemporary) {
        List<PetsPetInfo> petsPetInfos = baseService.list(new LambdaUpdateWrapper<PetsPetInfo>()
                .orderByDesc(PetsPetInfo::getCreateTime)
                .eq(PetsPetInfo::getUserId, JwtUtil.getJwtUser(request).getUserId())
                .ne(!isTemporary, PetsPetInfo::getNickname, "")
        );
        return R.success(BeanDtoVoUtil.listVo(petsPetInfos, PetsPetInfoVO.class));
    }


    @RequestMapping(value = "/findId", method = RequestMethod.GET)
    @ApiOperation(value = "ID查询--宠物详情", notes = "")
    public R<PetsPetInfoVO> findId(@RequestParam String id) {
        return R.successFind(BeanDtoVoUtil.convert(baseService.getById(id), PetsPetInfoVO.class));
    }


    @RequestMapping(value = "/automaticRenewal", method = RequestMethod.PUT)
    @ApiOperation(value = "宠物互助自动续约开关", notes = "")
    public R<Boolean> automaticRenewal(@RequestParam String petId, @RequestParam Boolean isAutoRenew) {
        PetsPetInfo updPetInfo = new PetsPetInfo();
        updPetInfo.setId(petId);
        updPetInfo.setIsAutoRenew(isAutoRenew);
        return R.success(baseService.updateById(updPetInfo));
    }


    @RequestMapping(value = "/updIsDefault", method = RequestMethod.PUT)
    @ApiOperation(value = "设置用户的默认宠物", notes = "")
    public R<Boolean> updIsDefault(@RequestParam String petId, @RequestParam Boolean isDefault) {
        return R.success(baseService.updIsDefault(petId, isDefault));
    }


    @RequestMapping(value = "/insert", method = RequestMethod.PUT)
    @ApiOperation(value = "添加宠物", notes = "返回宠物id")
    public R<String> insert(@RequestBody @Validated PetsPetInfoDTO dto) {
        return R.successUpdate(baseService.insert(dto));
    }


//    @RequestMapping(value = "/updPetBind", method = RequestMethod.PUT)
//    @ApiOperation(value = "支付成功后立即绑定", notes = "返回宠物id")
//    @Deprecated
//    public R<String> updPetBind(@RequestBody @Validated UpdPetBindDTO dto) {
//        return R.successUpdate(baseService.updPetBind(dto));
//    }
//
//    @RequestMapping(value = "/updPetBindTwo", method = RequestMethod.PUT)
//    @ApiOperation(value = "支付成功后没有立即绑定, 个人页进行绑定", notes = "返回宠物id")
//    @Deprecated
//    public R<String> updPetBindTwo(@RequestBody @Validated UpdPetBindTwoDTO dto) {
//        return R.successUpdate(baseService.updPetBindTwo(dto));
//    }


    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    @ApiOperation(value = "编辑", notes = "")
    public R<Boolean> upd(@RequestBody @Validated PetsPetInfoDTO dto) {
        if (StringUtils.isBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_TRUE);
        }
        return R.successUpdate(baseService.upd(dto));
    }
}
