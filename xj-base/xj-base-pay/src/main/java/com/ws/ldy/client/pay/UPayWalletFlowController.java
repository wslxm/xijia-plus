package com.ws.ldy.client.pay;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.config.auth.util.JwtUtil;
import com.ws.ldy.constant.BaseConstant;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import com.ws.ldy.modules.sys.pay.model.entity.PayWalletFlow;
import com.ws.ldy.modules.sys.pay.model.vo.PayWalletFlowVO;
import com.ws.ldy.modules.sys.pay.service.PayWalletFlowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@RequestMapping(BaseConstant.Uri.apiClient + "/pets/petsWalletFlow")
@Api(value = "UPayWalletFlowController", tags = "yh--账单/流水/支付流水表")
public class UPayWalletFlowController extends BaseController<PayWalletFlowService> {


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation(value = "我的缴费记录-分页查询", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页数", required = true, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "size", value = "记录数", required = true, paramType = "query", example = "20"),
    })
    public R<IPage<PayWalletFlowVO>> findPage() {
        Page<PayWalletFlow> page = baseService.page(this.getPage(), new LambdaQueryWrapper<PayWalletFlow>()
                .orderByDesc(PayWalletFlow::getCreateTime)
                .eq( PayWalletFlow::getUserId, JwtUtil.getJwtUser(request).getUserId())
        );
        return R.successFind(BeanDtoVoUtil.pageVo(page, PayWalletFlowVO.class));
    }
}
