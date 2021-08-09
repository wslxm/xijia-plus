package com.ws.ldy.starter.pay.controller;

import com.github.binarywang.wxpay.exception.WxPayException;
import com.ws.ldy.core.result.R;
import com.ws.ldy.starter.pay.model.dto.WxPayOrderDTO;
import com.ws.ldy.starter.pay.model.dto.WxPayRefundDTO;
import com.ws.ldy.starter.pay.model.vo.WxPayOrderResultVO;
import com.ws.ldy.starter.pay.model.vo.WxPayRefundResultVO;
import com.ws.ldy.starter.pay.service.XjWxPayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Binary Wang
 */

@RestController
@AllArgsConstructor
@Api(value = "WebsocketController", tags = "WX  -->  微信支付")
@RequestMapping("/api/open/pay")
public class WxPayController {


    // 需要处理业务的中转方法
    @Autowired
    private XjWxPayService xjWxPayService;

    /**
     * 测试参数
     * {
     *   "openid": "omEyb0g3vKGXLIpG9FEcVuRxNnIM",
     *   "outTradeNo": "XJ-321565156156",
     *   "totalFee": 100
     * }
     */
    @ApiOperation(value = "统一下单，并组装所需支付参数", notes = "详见https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1")
    @PostMapping("/createOrder")
    public R<WxPayOrderResultVO> createOrder(@RequestBody WxPayOrderDTO dto) throws WxPayException {
        return this.xjWxPayService.createOrder(dto);
    }


    @ApiOperation(value = "退款", notes = "详见: https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4")
    @PostMapping("/refund")
    public R<WxPayRefundResultVO> refund(@RequestBody WxPayRefundDTO dto) throws WxPayException {
        return this.xjWxPayService.refund(dto);
    }

}

