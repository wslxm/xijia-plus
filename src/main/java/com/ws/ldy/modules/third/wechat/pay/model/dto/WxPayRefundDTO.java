package com.ws.ldy.modules.third.wechat.pay.model.dto;


import com.ws.ldy.modules.sys.base.model.Convert;
import lombok.Data;
import lombok.ToString;


@Data
@ToString(callSuper = true)
@SuppressWarnings("all")
public class WxPayRefundDTO  extends Convert {

    private static final long serialVersionUID = 1335556773202152305L;
    private String outTradeNo;  // * 商户订单号
    private String outRefundNo; // *  商户退款单号
    private Integer totalFee;   //* 订单金额(单位分)
    private  Integer refundFee; // * 退款金额(单位分)
    private  String refundDesc; // 退款原因
}
