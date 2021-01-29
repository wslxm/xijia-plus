package com.ws.ldy.modules.yw.statistics.model.dto;

import com.ws.ldy.modules.sys.base.model.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

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
@ApiModel(value = "StatisticsIncreaseDeclareDTO 对象", description = "申报统计每小时增长表")
public class StatisticsIncreaseDeclareDTO extends BaseDto {

    private static final long serialVersionUID = -503729954400374785L;
    
    @ApiModelProperty(notes = "申报金额" ,position = 0)
    @DecimalMin(value = "0",message = "申报金额 必须>=0")
    @DecimalMax(value = "9999999999",message = "申报金额 必须<=9999999999")
    private BigDecimal declareMoney;

    @ApiModelProperty(notes = "申报数量" ,position = 1)
    @Range(min=0, max=7766279631452241920L,message = "申报数量 必须>=0 和 <=7766279631452241920")
    private Long declareNum;

    @ApiModelProperty(notes = "统计开始时间( 根据时间间隔 决定 数据条数)" ,position = 2)
    private LocalDateTime startTime;

    @ApiModelProperty(notes = "统计结束时间" ,position = 3)
    private LocalDateTime endTime;

}

