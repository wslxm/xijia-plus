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
 * 审报城市统计表(每小时一次)
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 10:50:19
 */
@Data
@ToString(callSuper = true)
@TableName("t_statistics_total_declare_city")
@ApiModel(value = "StatisticsTotalDeclareCity 对象", description = "审报城市统计表(每小时一次)")
public class StatisticsTotalDeclareCity extends BaseEntity {

    private static final long serialVersionUID = -503730078279143441L;

    // 重写deleted, 关闭该表的逻辑删除
    private Integer deleted;

    /** 
     * 城市名 
     */
    @TableField(value = "city")
    private String city;

    /** 
     * 当前城市申报总金额 
     */
    @TableField(value = "city_declare_money")
    private BigDecimal cityDeclareMoney;

    /** 
     * 当前城市申报总数 
     */
    @TableField(value = "city_declare_num")
    private Long cityDeclareNum;

    /** 
     * 最后统计时间 (根据城市 决定 数据条数) 
     */
    @TableField(value = "`time`")
    private LocalDateTime time;

}

