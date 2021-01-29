package com.ws.ldy.modules.yw.statistics.model.vo;

import com.ws.ldy.modules.sys.base.model.Convert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "StatisticsTotalPageVO 对象", description = "关键页访问统计表(每小时一次)")
public class StatisticsTotalPageVO extends Convert {

    private static final long serialVersionUID = -503730104816504842L;
    
    @ApiModelProperty(notes = "页面url" ,position = 0)
    private String pageUrl;

    @ApiModelProperty(notes = "页面名称" ,position = 1)
    private String pageName;

    @ApiModelProperty(notes = "今日访问量" ,position = 2)
    private Long pageDayTotal;

    @ApiModelProperty(notes = "七日访问量" ,position = 3)
    private Long pageDay7Total;

    @ApiModelProperty(notes = "30天访问量( 保留字段)" ,position = 4)
    private Long pageDay30Total;

    @ApiModelProperty(notes = "总访问量( 保留字段)" ,position = 5)
    private Long pageTotal;

    @ApiModelProperty(notes = "最后统计时间( 根据页面名称 决定 数据条数 )" ,position = 6)
    private LocalDateTime time;

}

