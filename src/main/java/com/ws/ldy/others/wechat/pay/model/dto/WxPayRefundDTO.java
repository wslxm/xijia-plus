package com.ws.ldy.others.wechat.pay.model.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@ApiModel(value = "WxPayRefundDTO 对象", description = "微信退款")
public class WxPayRefundDTO {


    @ApiModelProperty(value = " * 商户订单号")
    private String outTradeNo;

    @ApiModelProperty(value = " * 商户退款单号")
    private String outRefundNo;

    @ApiModelProperty(value = " * 订单金额(单位分)")
    private Integer totalFee;

    @ApiModelProperty(value = " * 退款金额(单位分)")
    private  Integer refundFee;

    @ApiModelProperty(value = "退款原因")
    private  String refundDesc;
}
