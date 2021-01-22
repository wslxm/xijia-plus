package com.ws.ldy.modules.third.pay.controller;

import com.github.binarywang.wxpay.exception.WxPayException;
import com.ws.ldy.common.result.R;
import com.ws.ldy.modules.third.pay.model.dto.WxPayOrderDTO;
import com.ws.ldy.modules.third.pay.model.dto.WxPayRefundDTO;
import com.ws.ldy.modules.third.pay.model.vo.WxPayOrderResultVO;
import com.ws.ldy.modules.third.pay.model.vo.WxPayRefundResultVO;
import com.ws.ldy.modules.third.pay.service.XjWxPayService;
import com.ws.ldy.modules.third.pay.wxApi.WxPayApi;
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
@RequestMapping("/api/pay")
public class WxPayController {

    // 直用方法
    @Autowired
    private WxPayApi wxPayApi;

    // 需要处理业务的中转方法
    @Autowired
    private XjWxPayService xjWxPayService;


//    @ApiOperation(value = "查询订单", notes = "" +
//            "查询订单(详见https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_2) \r\n" +
//            "transactionId=微信订单号 \r\n " +
//            "outTradeNo=商户系统内部的订单号，当没提供transactionId时需要传这个")
//    @GetMapping("/queryOrder")
//    public WxPayOrderQueryResult queryOrder(@RequestParam(required = false) String transactionId,
//                                            @RequestParam(required = false) String outTradeNo)
//            throws WxPayException {
//        return this.wxPayApi.queryOrder(transactionId, outTradeNo);
//    }
//
//
//    @ApiOperation(value = "关闭订单", notes = "outTradeNo=商户系统内部的订单号 \r\n 注意：订单生成后不能马上调用关单接口，最短调用时间间隔为5分钟。")
//    @GetMapping("/closeOrder/{outTradeNo}")
//    public WxPayOrderCloseResult closeOrder(@PathVariable String outTradeNo) throws WxPayException {
//        return this.wxPayApi.closeOrder(outTradeNo);
//    }


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
        return  this.xjWxPayService.createOrder(dto);
    }


    @ApiOperation(value = "退款", notes = "详见: https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4")
    @PostMapping("/refund")
    public R<WxPayRefundResultVO> refund(@RequestBody WxPayRefundDTO dto) throws WxPayException {
        return this.xjWxPayService.refund(dto);
    }


    //    @ApiOperation(value = "退款查询", notes = "详见 https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_5")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "transactionId", value = "微信订单号（以下四个参数四选一）", required = false, example = ""),
//            @ApiImplicitParam(name = "outTradeNo", value = "商户订单号", required = false, example = ""),
//            @ApiImplicitParam(name = "outRefundNo", value = "商户退款单号", required = false, example = ""),
//            @ApiImplicitParam(name = "refundId", value = "微信退款单号", required = false, example = "")
//    })
//    @GetMapping("/refundQuery")
//    public WxPayRefundQueryResult refundQuery(@RequestParam(required = false) String transactionId,
//                                              @RequestParam(required = false) String outTradeNo,
//                                              @RequestParam(required = false) String outRefundNo,
//                                              @RequestParam(required = false) String refundId)
//            throws WxPayException {
//        return this.wxPayApi.refundQuery(transactionId, outTradeNo, outRefundNo, refundId);
//    }
//
//    @ApiOperation(value = "退款查询")
//    @PostMapping("/refundQuery")
//    public WxPayRefundQueryResult refundQuery(@RequestBody WxPayRefundQueryRequest wxPayRefundQueryRequest) throws WxPayException {
//        return this.wxPayApi.refundQuery(wxPayRefundQueryRequest);
//    }
//
//
//    @ApiOperation(value = "下载对账单", notes = "详见：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_6")
//    @PostMapping("/downloadBill")
//    public WxPayBillResult downloadBill(WxPayDownloadBillRequest wxPayDownloadBillRequest) throws WxPayException {
//        return this.wxPayApi.downloadBill(wxPayDownloadBillRequest);
//    }
//
//
//    @ApiOperation(value = "撤销订单", notes = " \r\n 调用支付接口后请勿立即调用撤销订单API，建议支付后至少15s后再调用撤销订单接口" +
//            "文档地址：https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_11&index=3")
//    @PostMapping("/reverseOrder")
//    public WxPayOrderReverseResult reverseOrder(@RequestBody WxPayOrderReverseRequest request) throws WxPayException {
//        return this.wxPayApi.reverseOrder(request);
//    }
//
//
//    @ApiOperation(value = "支付回调通知处理")
//    @PostMapping("/notify/order")
//    public String parseOrderNotifyResult(@RequestBody String xmlData) throws WxPayException {
//        String body = this.wxPayApi.parseOrderNotifyResult(xmlData);
//        // TODO 根据自己业务场景需要构造返回对象
//        return WxPayNotifyResponse.success("成功");
//    }

//    @ApiOperation(value = "退款回调通知处理")
//    @PostMapping("/notify/refund")
//    public String parseRefundNotifyResult(@RequestBody String xmlData) throws WxPayException {
//        String body = this.wxPayApi.parseRefundNotifyResult(xmlData);
//        // TODO 根据自己业务场景需要构造返回对象
//        return WxPayNotifyResponse.success("成功");
//    }
//
//    @ApiOperation(value = "扫码支付回调通知处理")
//    @PostMapping("/notify/scanpay")
//    public String parseScanPayNotifyResult(String xmlData) throws WxPayException {
//        String body = this.wxPayApi.parseScanPayNotifyResult(xmlData);
//        // TODO 根据自己业务场景需要构造返回对象
//        return WxPayNotifyResponse.success("成功");
//    }
}

