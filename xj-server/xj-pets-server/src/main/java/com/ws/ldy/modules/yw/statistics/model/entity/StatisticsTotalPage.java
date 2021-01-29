package com.ws.ldy.modules.yw.statistics.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.modules.sys.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 关键页访问统计表(每小时一次)
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 10:50:26
 */
@Data
@ToString(callSuper = true)
@TableName("t_statistics_total_page")
@ApiModel(value = "StatisticsTotalPage 对象", description = "关键页访问统计表(每小时一次)")
public class StatisticsTotalPage extends BaseEntity {

    private static final long serialVersionUID = -503730104770367502L;

    // 重写deleted, 关闭该表的逻辑删除
    private Integer deleted;
    /** 
     * 页面url 
     */
    @TableField(value = "page_url")
    private String pageUrl;

    /** 
     * 页面名称 
     */
    @TableField(value = "page_name")
    private String pageName;

    /** 
     * 今日访问量 
     */
    @TableField(value = "page_day_total")
    private Long pageDayTotal;

    /** 
     * 七日访问量 
     */
    @TableField(value = "page_day7_total")
    private Long pageDay7Total;

    /** 
     * 30天访问量( 保留字段) 
     */
    @TableField(value = "page_day30_total")
    private Long pageDay30Total;

    /** 
     * 总访问量( 保留字段) 
     */
    @TableField(value = "page_total")
    private Long pageTotal;

    /** 
     * 最后统计时间( 根据页面名称 决定 数据条数 ) 
     */
    @TableField(value = "`time`")
    private LocalDateTime time;

}

