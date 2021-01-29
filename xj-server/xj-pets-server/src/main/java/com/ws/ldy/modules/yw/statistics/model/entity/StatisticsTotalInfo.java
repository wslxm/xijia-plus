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
 * 平台基础数据统计表(每小时一次)
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 10:50:22
 */
@Data
@ToString(callSuper = true)
@TableName("t_statistics_total_info")
@ApiModel(value = "StatisticsTotalInfo 对象", description = "平台基础数据统计表(每小时一次)")
public class StatisticsTotalInfo extends BaseEntity {

    private static final long serialVersionUID = -503730090962718726L;

    // 重写deleted, 关闭该表的逻辑删除
    private Integer deleted;

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

    /** 
     * 平台宠物总数 
     */
    @TableField(value = "pets_total")
    private Long petsTotal;

    /**
     * 平台狗宠物总数
     */
    @TableField(value = "pet_dog_total")
    private  Long petDogTotal;

    /**
     * 平台猫宠物总数
     */
    @TableField(value = "pet_cat_total")
    private  Long petCatTotal;

    /** 
     * 平台用户总数 
     */
    @TableField(value = "user_total")
    private Long userTotal;

    /** 
     * 平台女性总数 
     */
    @TableField(value = "girl_user_total")
    private Long girlUserTotal;

    /** 
     * 平台男性总数 
     */
    @TableField(value = "boy_user_total")
    private Long boyUserTotal;

    /** 
     * 申报用户总数 
     */
    @TableField(value = "declare_user_total")
    private Long declareUserTotal;

    /** 
     * 平台续费用户总数 
     */
    @TableField(value = "renewal_user_total")
    private Long renewalUserTotal;

    /** 
     * 统计时间 ( 根据统计时间间隔 决定 数据条数) 
     */
    @TableField(value = "`time`")
    private LocalDateTime time;

}

