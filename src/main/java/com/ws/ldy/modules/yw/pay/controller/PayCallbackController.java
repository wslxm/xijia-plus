package com.ws.ldy.modules.yw.pay.controller;

import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.yw.pay.service.PayService;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 第三方支付记录表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2021-01-05 10:14:05
 */
@RestController
@RequestMapping(BaseConstant.Sys.API + "/pay/notify")
@Api(value = "WxPayController", tags = "pay  -->  统一支付", consumes = BaseConstant.InterfaceType.PC_BASE)
public class PayCallbackController extends BaseController {

    @Autowired
    private PayService payService;

    @RequestMapping(value = "/wx/order", method = RequestMethod.POST)
    @ApiOperation(value = "微信支付回调", notes = "")
    public String orderCallback(@RequestBody String xmlData) {
        return payService.orderCallback(xmlData);
    }


    @RequestMapping(value = "/wx/refund", method = RequestMethod.POST)
    @ApiOperation(value = "微信退款回调", notes = "")
    public String refundCallback() {
        return null;
    }

}
