package com.ws.ldy.modules.yw.statistics.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.modules.sys.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 缴费增长每小时统计表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 10:50:13
 */
@Data
@ToString(callSuper = true)
@TableName("t_statistics_increase_payment")
@ApiModel(value = "StatisticsIncreasePayment 对象", description = "缴费增长每小时统计表")
public class StatisticsIncreasePayment extends BaseEntity {

    private static final long serialVersionUID = -503730050781286418L;
    
    /** 
     * 统计开始时间( 根据时间间隔 决定 数据条数) 
     */
    @TableField(value = "start_time")
    private LocalDateTime startTime;

    /** 
     * 统计结束时间 
     */
    @TableField(value = "end_time")
    private LocalDateTime endTime;

    /** 
     * 缴费次数 
     */
    @TableField(value = "payment_num")
    private Integer paymentNum;

    /** 
     * 缴费总金额( 开始时间-结束时间的金额) 
     */
    @TableField(value = "payment_money")
    private BigDecimal paymentMoney;

    /** 
     * 男性缴费金额数 (预留字段) 
     */
    @TableField(value = "payment_boy_money_total")
    private BigDecimal paymentBoyMoneyTotal;

    /** 
     * 女性缴费金额数 (预留字段) 
     */
    @TableField(value = "payment_girl_money_total")
    private BigDecimal paymentGirlMoneyTotal;


    // 首次 和 续费
    @TableField(value = "first_num")
    private Integer firstNum;

    @TableField(value = "first_money")
    private BigDecimal firstMoney;

    @TableField(value = "renew_num")
    private Integer renewNum;

    @TableField(value = "renew_money")
    private BigDecimal renewMoney;
}

