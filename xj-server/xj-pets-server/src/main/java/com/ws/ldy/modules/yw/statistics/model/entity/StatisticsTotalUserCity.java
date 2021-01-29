package com.ws.ldy.modules.yw.statistics.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.modules.sys.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 用户城市总量表(每小时一次)
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 10:50:33
 */
@Data
@ToString(callSuper = true)
@TableName("t_statistics_total_user_city")
@ApiModel(value = "StatisticsTotalUserCity 对象", description = "用户城市总量表(每小时一次)")
public class StatisticsTotalUserCity extends BaseEntity {

    private static final long serialVersionUID = -503730135489449990L;


    // 重写deleted, 关闭该表的逻辑删除
    private Integer deleted;
    /** 
     * 城市名 
     */
    @TableField(value = "city")
    private String city;

    /** 
     * 当前城市人总数 
     */
    @TableField(value = "city_user_total")
    private Long cityUserTotal;

    /** 
     * 男性城市人总数( 预留字段) 
     */
    @TableField(value = "city_boy_user_total")
    private Long cityBoyUserTotal;

    /** 
     * 女性城市人总数( 预留字段) 
     */
    @TableField(value = "city_girl_user_total")
    private Long cityGirlUserTotal;

    /** 
     * 最后统计时间( 城市数 决定 数据条数) 
     */
    @TableField(value = "`time`")
    private LocalDateTime time;

}

