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
 * 页面统计每小时增长表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 10:50:09
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "StatisticsIncreasePageDTO 对象", description = "页面统计每小时增长表")
public class StatisticsIncreasePageDTO extends BaseDto {

    private static final long serialVersionUID = -503730036432572436L;
    
    @ApiModelProperty(notes = "统计开始时间( 根据时间间隔 * 页面名称 决定 数据条数)" ,position = 0)
    private LocalDateTime startTime;

    @ApiModelProperty(notes = "统计结束时间" ,position = 1)
    private LocalDateTime endTime;

    @ApiModelProperty(notes = "页面访问数" ,position = 2)
    @Range(min=0, max=7766279631452241920L,message = "页面访问数 必须>=0 和 <=7766279631452241920")
    private Long pageTotal;

    @ApiModelProperty(notes = "页面名称" ,position = 3)
    @Length(min=1, max=64,message = "页面名称 必须>=0 和 <=64位")
    private String pageName;

    @ApiModelProperty(notes = "页面url" ,position = 4)
    @Length(min=1, max=512,message = "页面url 必须>=0 和 <=512位")
    private String pageUrl;

}

