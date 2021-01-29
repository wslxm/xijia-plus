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
 * 用户增长每小时统计表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 10:50:16
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "StatisticsIncreaseUserDTO 对象", description = "用户增长每小时统计表")
public class StatisticsIncreaseUserDTO extends BaseDto {

    private static final long serialVersionUID = -503730065180332049L;
    
    @ApiModelProperty(notes = "统计开始时间( 根据时间间隔 决定 数据条数)" ,position = 0)
    private LocalDateTime startTime;

    @ApiModelProperty(notes = "统计结束时间" ,position = 1)
    private LocalDateTime endTime;

    @ApiModelProperty(notes = "平台总人数增长数" ,position = 2)
    @Range(min=0, max=7766279631452241920L,message = "平台总人数增长数 必须>=0 和 <=7766279631452241920")
    private Long userTotal;

    @ApiModelProperty(notes = "男性增长数" ,position = 3)
    @Length(min=1, max=20,message = "男性增长数 必须>=0 和 <=20位")
    private Long boyUserTotal;

    @ApiModelProperty(notes = "女性增长数" ,position = 4)
    @Length(min=1, max=20,message = "女性增长数 必须>=0 和 <=20位")
    private Long girlUserTotal;

}

