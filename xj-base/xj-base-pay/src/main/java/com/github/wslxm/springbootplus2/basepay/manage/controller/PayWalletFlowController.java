package com.github.wslxm.springbootplus2.basepay.manage.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.wslxm.springbootplus2.core.base.controller.BaseController;
import com.github.wslxm.springbootplus2.core.constant.BaseConstant;
import com.github.wslxm.springbootplus2.core.result.R;
import com.github.wslxm.springbootplus2.core.utils.BeanDtoVoUtil;
import com.github.wslxm.springbootplus2.basepay.manage.model.vo.PayWalletFlowVO;
import com.github.wslxm.springbootplus2.basepay.manage.model.entity.PayWalletFlow;
import com.github.wslxm.springbootplus2.basepay.manage.service.PayWalletFlowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 账单/流水/支付流水表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:55:32
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin + "/pets/petsWalletFlow")
@Api(value = "PayWalletFlowController", tags = "账单/流水/支付流水表")
public class PayWalletFlowController extends BaseController<PayWalletFlowService> {


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页数", required = true, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "size", value = "记录数", required = true, paramType = "query", example = "20"),
            @ApiImplicitParam(name = "orderNo", value = "订单号", required = false, paramType = "query", example = ""),
            @ApiImplicitParam(name = "userId", value = "账号(商家/用户--0为平台)", required = false, paramType = "query", example = ""),
    })
    public R<IPage<PayWalletFlowVO>> findPage(@RequestParam(required = false) String orderNo,
                                              @RequestParam(required = false) String userId
    ) {
        Page<PayWalletFlow> page = baseService.page(this.getPage(), new LambdaQueryWrapper<PayWalletFlow>()
                .orderByDesc(PayWalletFlow::getCreateTime)
                .eq(StringUtils.isNotBlank(orderNo), PayWalletFlow::getOrderNo, orderNo)
                .eq(StringUtils.isNotBlank(userId), PayWalletFlow::getUserId, userId)
        );
        return R.successFind(BeanDtoVoUtil.pageVo(page, PayWalletFlowVO.class));
    }
}
