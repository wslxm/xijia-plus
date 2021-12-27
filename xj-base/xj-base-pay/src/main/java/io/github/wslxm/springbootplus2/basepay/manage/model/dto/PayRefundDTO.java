package io.github.wslxm.springbootplus2.basepay.manage.model.dto;

import io.github.wslxm.springbootplus2.core.enums.Admin;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 账单/流水/支付流水表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:55:32
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "PayRefundDTO 对象", description = "账单/流水/支付流水表")
public class PayRefundDTO implements Serializable {

    private static final long serialVersionUID = -503731388638433298L;
    /**
     * * 支付订单商户订单号
     */
    private String orderNo;
    /**
     *  * 支付订单商户交易号, // transactionNo
     */
    private String outTradeNo;
    /**
     * * 商户退款单号
     */
    private String outRefundNo;
    /**
     *  * 支付订单交易金额(单位元)
     */
    private BigDecimal totalFee;
    /**
     * * 退款金额(单位元)
     */
    private BigDecimal refundFee;
    /**
     * * 业务类型(当前系统字典code)
     */
    private Admin.PayBusiness payBusiness;
    /**
     * 退款原因
     */
    private String refundDesc;
}

