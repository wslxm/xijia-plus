//package com.ws.ldy.modules.sys.pay.model.vo;
//
//
//import io.swagger.annotations.ApiModel;
//import lombok.Data;
//import lombok.ToString;
//
//import java.io.Serializable;
//
//@SuppressWarnings("all")
//@Data
//@ApiModel(value = "PayRefundVO 对象", description = "订单退款成功返回数据")
//@ToString
//public class PayRefundVO implements Serializable {
//
//    private static final long serialVersionUID = 167639328839129772L;
//    private String transactionId; // 微信订单号.
//    private String outTradeNo;    // 商户订单号.
//    private String outRefundNo;   // 商户退款单号.
//    private String refundId;      // 微信退款单号.
//    private Integer refundFee;    // 退款金额.
//    private Integer settlementRefundFee; // 应结退款金额.
//    private Integer totalFee;            // 标价金额.
//    private Integer settlementTotalFee;  // 应结订单金额.
//    private String feeType;              // 标价币种.
//    private Integer cashFee;             // 现金支付金额.
//    private String cashFeeType;          // 现金支付币种.
//    private String cashRefundFee;        // 现金退款金额.
//    private Integer couponRefundCount;   // 退款代金券使用数量.
//    //当return_code为SUCCESS的时候，还会包括以下字段：
//    private String sign;          // 签名.
//}
