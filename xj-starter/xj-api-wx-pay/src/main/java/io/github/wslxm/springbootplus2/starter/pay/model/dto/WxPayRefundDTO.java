package io.github.wslxm.springbootplus2.starter.pay.model.dto;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;


/**
 * 退款参数
 * @author wangsong
 * @date 2021/1/22 0022 10:45
 * @return
 * @version 1.0.1
 */
@Data
@ToString(callSuper = true)
public class WxPayRefundDTO implements Serializable {

    private static final long serialVersionUID = 1335556773202152305L;
    /**
     * * 支付订单的商户订单号
     */
    private String outTradeNo;
    /**
     * * 商户退款单号
     */
    private String outRefundNo;
    /**
     *  * 支付订单订单金额(单位分)
     */
    private Integer totalFee;
    /**
     *  * 退款金额(单位分)
     */
    private Integer refundFee;
    /**
     * 退款原因
     */
    private String refundDesc;
}
