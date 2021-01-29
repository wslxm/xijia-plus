package com.ws.ldy.client.yw.pets.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.config.auth.util.JwtUtil;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import com.ws.ldy.modules.yw.pets.model.dto.PetsBankCardDTO;
import com.ws.ldy.modules.yw.pets.model.entity.PetsBankCard;
import com.ws.ldy.modules.yw.pets.model.vo.PetsBankCardVO;
import com.ws.ldy.modules.yw.pets.service.PetsBankCardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 银行卡管理
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 18:49:29
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiClient + "/pets/petsBankCard")
@Api(value = "UPetsBankCardController", tags = "yh--银行卡管理")
public class UPetsBankCardController extends BaseController<PetsBankCardService> {


    @RequestMapping(value = "/findListByUser", method = RequestMethod.GET)
    @ApiOperation(value = "我的银行卡列表", notes = "")
    public R<IPage<PetsBankCardVO>> findListByUser() {
        Page<PetsBankCard> page = baseService.page(this.getPage(), new LambdaQueryWrapper<PetsBankCard>()
                .orderByDesc(PetsBankCard::getCreateTime)
                .eq(PetsBankCard::getUserId, JwtUtil.getJwtUser(request).getUserId())

        );
        return R.successFind(BeanDtoVoUtil.pageVo(page, PetsBankCardVO.class));
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "银行卡添加", notes = "必须不传递ID")
    public R<Boolean> insert(@RequestBody @Validated PetsBankCardDTO dto) {
        if (StringUtils.isNotBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_FALSE);
        }
        return R.successInsert(baseService.insert(dto));
    }


    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    @ApiOperation(value = "银行卡编辑--ID编辑", notes = "必须传递ID")
    public R<Boolean> upd(@RequestBody @Validated PetsBankCardDTO dto) {
        if (StringUtils.isBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_TRUE);
        }
        return R.successUpdate(baseService.updateById(dto.convert(PetsBankCard.class)));
    }


    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    @ApiOperation(value = "银行卡删除--ID删除", notes = "")
    public R<Boolean> del(@RequestParam String id) {
        return R.successDelete(baseService.removeById(id));
    }
}
