package com.ws.ldy.modules.yw.pay.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.modules.sys.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 第三方支付记录表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2021-01-05 10:14:05
 */
@Data
@ToString(callSuper = true)
@TableName("t_xj_pay_record")
@ApiModel(value = "PayRecord 对象", description = "第三方支付记录表")
public class PayRecord extends BaseEntity {

    private static final long serialVersionUID = -506257672295813126L;

    /**
     * 订单号（对应订单表）
     */
    @TableField(value = "order_no")
    private String orderNo;

    /**
     * 交易号（第三方支付时生成）( 支付/退款/放款等)
     */
    @TableField(value = "trade_no")
    private String tradeNo;

    /**
     * 平台手续费(元) 
     */
    @TableField(value = "platform_fee")
    private BigDecimal platformFee;

    /**
     * 第三方手续费(元), 为0时,手续费累加在 platform_fee 
     */
    @TableField(value = "channel_fee")
    private BigDecimal channelFee;

    /**
     * 交易总金额( 元) 
     */
    @TableField(value = "money_total")
    private BigDecimal moneyTotal;


    /**
     * 剩余金额( 如存在子商户, 则为子商户实际收入)
     */
    @TableField(value = "money_surplus")
    private BigDecimal moneySurplus;

    /**
     * 交易状态( 0-已发起 1-回调成功(临时状态) 2-交易失败 3-交易成功 ) 
     */
    @TableField(value = "pay_state")
    private Integer payState;

    /**
     * 支付渠道( 字典code 如1-支付宝 2-微信 3-银行卡 等) 
     */
    @TableField(value = "pay_channel")
    private Integer payChannel;

    /**
     * 支付类型( 字典code 如 1-支付 2-充值 3-退款 等) 
     */
    @TableField(value = "pay_type")
    private Integer payType;

    /**
     * 业务类型( 字典code -根据系统业务定制 如  1-用户支付  2-平台打款 等) 
     */
    @TableField(value = "business_type")
    private Integer businessType;

    /**
     * 业务描叙( 追踪具体业务) 
     */
    @TableField(value = "business_desc")
    private String businessDesc;

    /**
     * 请求数据 
     */
    @TableField(value = "request_data")
    private String requestData;

    /**
     * 响应数据 
     */
    @TableField(value = "response_data")
    private String responseData;

    /**
     * 回调数据 
     */
    @TableField(value = "callback_data")
    private String callbackData;

}

