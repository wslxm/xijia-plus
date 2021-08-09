package com.ws.ldy.basepay.manage.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.core.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;
import java.math.BigDecimal;

/**
 * 账单/流水/支付流水表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 10:55:32
 */
@Data
@ToString(callSuper = true)
@TableName("t_xj_pay_wallet_flow")
@ApiModel(value = "payWalletFlow 对象", description = "账单/流水/支付流水表")
public class PayWalletFlow extends BaseEntity {

    private static final long serialVersionUID = -503731388571324427L;
    
    /** 
     * 账号id=用户id/ 平台为0*默认 
     */
    @TableField(value = "user_id")
    private String userId;

    /** 
     * 订单号 
     */
    @TableField(value = "order_no")
    private String orderNo;

    /** 
     * 交易后总金额( 元) 
     */
    @TableField(value = "money_after")
    private BigDecimal moneyAfter;

    /** 
     * 金额( 不论支出收入都是正数) 
     */
    @TableField(value = "money")
    private BigDecimal money;

    /** 
     * 流水类型(1-收入 2-支出) 
     */
    @TableField(value = "wallet_type")
    private Integer walletType;

    /** 
     * 业务类型( 字段code) 
     */
    @TableField(value = "business_type")
    private Integer businessType;

    /** 
     * 业务描叙 
     */
    @TableField(value = "business_desc")
    private String businessDesc;

}

