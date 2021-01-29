package com.ws.ldy.modules.yw.pets.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.yw.pets.model.entity.PetsInvite;
import com.ws.ldy.modules.yw.pets.model.vo.PetsInviteVO;
import com.ws.ldy.modules.yw.pets.service.PetsInviteService;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping(BaseConstant.Uri.apiAdmin + "/pets/petsInvite")
@Api(value = "PetsInviteController", tags = "邀请表")
public class PetsInviteController extends BaseController<PetsInviteService> {


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页数", required = true, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "size", value = "记录数", required = true, paramType = "query", example = "20"),

    })
    public R<IPage<PetsInviteVO>> findPage() {
        Page<PetsInvite> page = baseService.page(this.getPage(), new LambdaQueryWrapper<PetsInvite>()
                .orderByDesc(PetsInvite::getCreateTime)
        );
        return R.successFind(BeanDtoVoUtil.pageVo(page, PetsInviteVO.class));
    }
}


