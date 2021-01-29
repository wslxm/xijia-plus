package com.ws.ldy.client.yw.pets.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.common.result.R;
import com.ws.ldy.config.auth.util.JwtUtil;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import com.ws.ldy.modules.sys.pay.model.vo.PayOrderResultVO;
import com.ws.ldy.modules.yw.pets.model.dto.PetsOrderDTO;
import com.ws.ldy.modules.yw.pets.model.vo.PetsOrderClientPageVO;
import com.ws.ldy.modules.yw.pets.service.PetsOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 支付订单/缴费管理表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:54:07
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiClient + "/pets/petsOrder")
@Api(value = "UPetsOrderController", tags = "yh--支付订单/缴费管理表")
public class UPetsOrderController extends BaseController<PetsOrderService> {


    @RequestMapping(value = "/createOrder", method = RequestMethod.POST)
    @ApiOperation(value = "创建支付订单-返回支付相关参数", notes = "必须不传递ID")
    public R<PayOrderResultVO> createOrder(@RequestBody @Validated PetsOrderDTO dto) {
        return R.success(baseService.createOrder(dto));
    }


    @RequestMapping(value = "/wx/order/notify", method = RequestMethod.POST)
    @ApiOperation(value = "微信支付回调", notes = "")
    public String orderCallback(@RequestBody String xmlData) {
        return baseService.orderCallback(xmlData);
    }


    @RequestMapping(value = "/findClientPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询充值记录", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页数", required = true, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "size", value = "记录数", required = true, paramType = "query", example = "20"),
    })
    public R<Page<PetsOrderClientPageVO>> findClientPage() {
        String userId = JwtUtil.getJwtUser(request).getUserId();
        Page<PetsOrderClientPageVO> clientPageVO = baseService.findClientPage(this.getPage(), userId);
        return R.success(clientPageVO);
    }
}
