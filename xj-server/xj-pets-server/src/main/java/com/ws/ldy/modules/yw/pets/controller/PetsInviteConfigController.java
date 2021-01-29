package com.ws.ldy.modules.yw.pets.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.yw.pets.model.dto.PetsInviteConfigDTO;
import com.ws.ldy.modules.yw.pets.model.entity.PetsInviteConfig;
import com.ws.ldy.modules.yw.pets.model.vo.PetsInviteConfigVO;
import com.ws.ldy.modules.yw.pets.service.PetsInviteConfigService;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 邀请增加时长配置表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 19:37:23
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin + "/pets/petsInviteConfig")
@Api(value = "PetsInviteConfigController", tags = "邀请增加时长配置表")
public class PetsInviteConfigController extends BaseController<PetsInviteConfigService> {


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页数", required = true, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "size", value = "记录数", required = true, paramType = "query", example = "20"),

    })
    public R<IPage<PetsInviteConfigVO>> findPage(
    ) {
        Page<PetsInviteConfig> page = baseService.page(this.getPage(), new LambdaQueryWrapper<PetsInviteConfig>()
                .orderByDesc(PetsInviteConfig::getCreateTime)

        );
        return R.successFind(BeanDtoVoUtil.pageVo(page, PetsInviteConfigVO.class));
    }



    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    @ApiOperation(value = "ID编辑", notes = "必须传递ID")
    public R<Boolean> upd(@RequestBody @Validated PetsInviteConfigDTO dto) {
        if (StringUtils.isBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_TRUE);
        }
        return R.successUpdate(baseService.updateById(dto.convert(PetsInviteConfig.class)));
    }


}
