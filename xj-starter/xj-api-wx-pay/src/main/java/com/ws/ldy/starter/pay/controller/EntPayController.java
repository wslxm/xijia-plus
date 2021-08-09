package com.ws.ldy.starter.pay.controller;

import com.ws.ldy.starter.pay.service.XjEntPayService;
import com.ws.ldy.starter.pay.wxApi.EntPayApi;
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


}
