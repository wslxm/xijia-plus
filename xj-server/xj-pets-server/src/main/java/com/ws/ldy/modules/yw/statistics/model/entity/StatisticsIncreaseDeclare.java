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
 * 申报统计每小时增长表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 10:49:50
 */
@Data
@ToString(callSuper = true)
@TableName("t_statistics_increase_declare")
@ApiModel(value = "StatisticsIncreaseDeclare 对象", description = "申报统计每小时增长表")
public class StatisticsIncreaseDeclare extends BaseEntity {

    private static final long serialVersionUID = -503729954316488706L;
    
    /** 
     * 申报金额 
     */
    @TableField(value = "declare_money")
    private BigDecimal declareMoney;

    /** 
     * 申报数量 
     */
    @TableField(value = "declare_num")
    private Long declareNum;

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

}

