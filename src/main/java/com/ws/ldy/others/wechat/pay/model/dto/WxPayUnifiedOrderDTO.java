package com.ws.ldy.others.wechat.pay.model.dto;


import com.ws.ldy.others.base.model.Convert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@ApiModel(value = "WxPayUnifiedOrderDTO 对象", description = "微信下单需要用到的参数")
public class WxPayUnifiedOrderDTO extends Convert {


    @ApiModelProperty(value = "商户订单号")
    private String outTradeNo;

    @ApiModelProperty(value = "金额(单位分)")
    private Integer totalFee;

    @ApiModelProperty(value = "用户openid (交易类型 JSAPI 时必传)")
    private String openid;

    @ApiModelProperty(value = "交易类型( 默认= JSAPI=公众号/公众号h5/小程序支付 | NATIVE --Native支付 | APP --app支付 |  MWEB --H5支付")
    private String tradeType;

    @ApiModelProperty(value = "商品描述( 不传默认=暂无商品描叙 )")
    private String body;

    @ApiModelProperty(value = "客户终端IP( 未传递时默认使用 request.getRemoteHost()获取客户ip )")
    private String spbillCreateIp;
}
