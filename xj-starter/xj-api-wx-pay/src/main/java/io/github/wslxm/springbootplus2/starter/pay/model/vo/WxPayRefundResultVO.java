package io.github.wslxm.springbootplus2.starter.pay.model.vo;

import lombok.Data;
import lombok.ToString;
import org.w3c.dom.Document;

/**
 * 微信退款
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/12/27 0027 20:15 
 * @version 1.0.0
 */
@Data
@ToString
public class WxPayRefundResultVO extends WxBaseResurtVO {

    private static final long serialVersionUID = -4690817449613955955L;
    /**
     * 微信订单号.
     */
    private String transactionId;

    /**
     * 商户订单号.
     */
    private String outTradeNo;

    /**
     * 商户退款单号.
     */
    private String outRefundNo;

    /**
     * 微信退款单号.
     */
    private String refundId;

    /**
     * 退款金额.
     */
    private Integer refundFee;

    /**
     * 应结退款金额.
     */
    private Integer settlementRefundFee;

    /**
     * 标价金额.
     */
    private Integer totalFee;

    /**
     * 应结订单金额.
     */
    private Integer settlementTotalFee;

    /**
     * 标价币种.
     */
    private String feeType;

    /**
     * 现金支付金额.
     */
    private Integer cashFee;

    /**
     * 现金支付币种.
     */
    private String cashFeeType;

    /**
     * 现金退款金额.
     */
    private String cashRefundFee;

    /**
     * 退款代金券使用数量.
     */
    private Integer couponRefundCount;


    //当return_code为SUCCESS的时候，还会包括以下字段：

    /**
     * 签名.
     */
    private String sign;

    //以下为辅助属性
    /**
     * xml字符串.
     */
    private String xmlString;

    /**
     * xml的Document对象，用于解析xml文本.
     */
    private Document xmlDoc;
}
