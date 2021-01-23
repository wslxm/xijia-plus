package com.ws.ldy.modules.third.pay.controller;

import com.ws.ldy.modules.third.pay.service.XjEntPayService;
import com.ws.ldy.modules.third.pay.wxApi.EntPayApi;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 * 企业付款相关接口
 * Created by Binary Wang on 2018/9/27.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */

@RestController
@AllArgsConstructor
@Api(value = "WebsocketController", tags = "WX  -->  微信企业付款")
@RequestMapping( "/api/open/entPay")
public class EntPayController {

  // 直用方法
  @Autowired
  private EntPayApi entPayApi;
  // 需要处理业务的中转方法
  @Autowired
  private XjEntPayService xjEntPayService;

//
//  @ApiOperation(value = "企业付款到零钱")
//  @PostMapping("/entPay")
//  public EntPayResult entPay(@RequestBody EntPayRequest request) throws WxPayException {
//    return this.entPayApi.entPay(request);
//  }
//
//
//  @ApiOperation(value = "查询企业付款到零钱的结果",notes = "文档地址：https://pay.weixin.qq.com/wiki/doc/api/tools/mch_pay.php?chapter=14_3")
//  @ApiImplicitParam(name = "partnerTradeNo", value = "商户订单号", required = true, example = "")
//  @GetMapping("/queryEntPay")
//  public EntPayQueryResult queryEntPay(@RequestParam String partnerTradeNo) throws WxPayException {
//    return this.entPayApi.queryEntPay(partnerTradeNo);
//  }
//
//
//
//  @ApiOperation(value = "企业付款到银行卡",notes = "文档详见：https://pay.weixin.qq.com/wiki/doc/api/tools/mch_pay.php?chapter=24_2")
//  @PostMapping("/payBank")
//  public EntPayBankResult payBank(EntPayBankRequest request) throws WxPayException {
//    return this.entPayApi.payBank(request);
//  }
//
//
//  @ApiOperation(value = "查询企业付款到银行卡的结果",notes = "文档详见：https://pay.weixin.qq.com/wiki/doc/api/tools/mch_pay.php?chapter=24_3")
//  @GetMapping("/queryPayBank")
//  public EntPayBankQueryResult queryPayBank(@RequestParam String partnerTradeNo) throws WxPayException {
//    return this.entPayApi.queryPayBank(partnerTradeNo);
//  }

}
