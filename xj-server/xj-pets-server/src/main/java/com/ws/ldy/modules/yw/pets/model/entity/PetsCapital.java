package com.ws.ldy.modules.yw.pets.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.modules.sys.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;
import java.math.BigDecimal;

/**
 * 互助资金表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 10:52:40
 */
@Data
@ToString(callSuper = true)
@TableName("t_pets_capital")
@ApiModel(value = "PetsCapital 对象", description = "互助资金表")
public class PetsCapital extends BaseEntity {

    private static final long serialVersionUID = -503730669780865033L;
    
    /** 
     * 平台资金累积总金额 
     */
    @TableField(value = "money_total")
    private BigDecimal moneyTotal;

    /** 
     * 平台收益总额度 
     */
    @TableField(value = "money_profit")
    private BigDecimal moneyProfit;

    /** 
     * 资金池剩余额度 
     */
    @TableField(value = "money_surplus")
    private BigDecimal moneySurplus;

    /** 
     * 平台互助金已发放总金额 
     */
    @TableField(value = "money_payment")
    private BigDecimal moneyPayment;

}

