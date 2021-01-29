package com.ws.ldy.modules.yw.pets.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.modules.sys.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 支付订单/缴费管理表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:54:07
 */
@Data
@ToString(callSuper = true)
@TableName("t_pets_order")
@ApiModel(value = "PetsOrder 对象", description = "支付订单/缴费管理表")
public class PetsOrder extends BaseEntity {

    private static final long serialVersionUID = -503731032386834441L;

    /**
     * 用户id 
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 宠物id 
     */
    @TableField(value = "pet_id")
    private String petId;
    /**
     * 月费表id/产品id
     */
    @TableField(value = "month_fee_id")
    private String monthFeeId;
    /**
     * 支付金额 
     */
    @TableField(value = "money")
    private BigDecimal money;

    /**
     * 当前支付基数 (基数根据申报次数变动) 
     */
    @TableField(value = "payment_base")
    private Double paymentBase;

    /**
     * 支付渠道( 字典code) 
     */
    @TableField(value = "pay_channel")
    private Integer payChannel;

    /**
     * 订单状态( 字典code 0-待支付*默认 1-支付失败 2-支付成功/待绑定宠物  3-已绑定宠物 4-订单失效) 
     */
    @TableField(value = "order_state")
    private Integer orderState;

    /**
     * 交易订单号 
     */
    @TableField(value = "order_no")
    private String orderNo;

    /**
     * 业务类型( 字典code -根据系统业务定制 如  1-用户支付  2-平台打款 等) 
     */
    @TableField(value = "business_type")
    private Integer businessType;

    /**
     * 业务描叙 
     */
    @TableField(value = "business_desc")
    private String businessDesc;
    /**
     * 是否自动续费
     */
    @TableField(value = "is_auto_renew")
    private Boolean isAutoRenew;

    /**
     * 是否首次支付
     */
    @TableField(value = "is_first")
    private Boolean isFirst;

    /**
     * 支付时间
     */
    @TableField(value = "pay_time")
    private LocalDateTime payTime;

}

