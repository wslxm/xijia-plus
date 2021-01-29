package com.ws.ldy.modules.yw.statistics.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.modules.sys.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 用户增长每小时统计表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:50:16
 */
@Data
@ToString(callSuper = true)
@TableName("t_statistics_increase_user")
@ApiModel(value = "StatisticsIncreaseUser 对象", description = "用户增长每小时统计表")
public class StatisticsIncreaseUser extends BaseEntity {

    private static final long serialVersionUID = -503730065117417474L;

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
     * 平台总人数增长数 
     */
    @TableField(value = "user_total")
    private Long userTotal;

    /**
     * 男性增长数 
     */
    @TableField(value = "boy_user_total")
    private Long boyUserTotal;

    /**
     * 女性增长数 
     */
    @TableField(value = "girl_user_total")
    private Long girlUserTotal;

}

