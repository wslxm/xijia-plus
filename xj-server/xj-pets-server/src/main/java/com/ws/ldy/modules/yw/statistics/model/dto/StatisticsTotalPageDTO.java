package com.ws.ldy.modules.yw.statistics.model.dto;

import com.ws.ldy.modules.sys.base.model.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

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
@ApiModel(value = "StatisticsTotalPageDTO 对象", description = "关键页访问统计表(每小时一次)")
public class StatisticsTotalPageDTO extends BaseDto {

    private static final long serialVersionUID = -503730104845864962L;
    
    @ApiModelProperty(notes = "页面url" ,position = 0)
    @Length(min=1, max=512,message = "页面url 必须>=0 和 <=512位")
    private String pageUrl;

    @ApiModelProperty(notes = "页面名称" ,position = 1)
    @Length(min=1, max=128,message = "页面名称 必须>=0 和 <=128位")
    private String pageName;

    @ApiModelProperty(notes = "今日访问量" ,position = 2)
    @Range(min=0, max=7766279631452241920L,message = "今日访问量 必须>=0 和 <=7766279631452241920")
    private Long pageDayTotal;

    @ApiModelProperty(notes = "七日访问量" ,position = 3)
    @Range(min=0, max=99999999999L,message = "七日访问量 必须>=0 和 <=99999999999")
    private Long pageDay7Total;

    @ApiModelProperty(notes = "30天访问量( 保留字段)" ,position = 4)
    private Long pageDay30Total;

    @ApiModelProperty(notes = "总访问量( 保留字段)" ,position = 5)
    private Long pageTotal;

    @ApiModelProperty(notes = "最后统计时间( 根据页面名称 决定 数据条数 )" ,position = 6)
    private LocalDateTime time;

}

