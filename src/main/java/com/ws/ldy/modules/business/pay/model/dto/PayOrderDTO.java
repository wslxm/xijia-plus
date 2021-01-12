package com.ws.ldy.modules.business.pay.model.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString(callSuper = true)
@ApiModel(value = "PayOrderDTO 对象", description = "支付下单需要用到的参数")
public class PayOrderDTO {

    @ApiModelProperty(value = "必传--支付总金额(单位元)--包括平台手续费")
    private BigDecimal moneyTotal;

    @ApiModelProperty(value = "必传--用户openid ( 微信交易类型 JSAPI 时必传)")
    private String openid;

    @ApiModelProperty(value = "必传--商户订单号(如不传将自动生成并在响应信息中会返回)")
    private String orderNo;

    @ApiModelProperty(value = "非必传--平台手续费(单位元)--不传或0 默认代表没有平台手续费")
    private BigDecimal platformFee;

    @ApiModelProperty(value = "非必传--渠道手续费(单位元)--微信/支付宝每笔交易收取手续")
    private BigDecimal channelFee;

    @ApiModelProperty(value = "非必传：默认JSAPI -- 微信支付必传--交易类型( 默认= JSAPI=公众号/公众号h5/小程序支付 | NATIVE --Native支付 | APP --app支付 |  MWEB --H5支付")
    private String tradeType;

    @ApiModelProperty(value = "非必传： wxPay--商品描述(建议传递, 不传默认=暂无商品描叙 )")
    private String body;

    @ApiModelProperty(value = "非必传： wxPay--客户终端IP(未传递时默认使用 request.getRemoteHost() 获取客户ip )")
    private String spbillCreateIp;
}
