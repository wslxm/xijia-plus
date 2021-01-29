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
 * 缴费增长每小时统计表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 10:50:13
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "StatisticsIncreasePaymentDTO 对象", description = "缴费增长每小时统计表")
public class StatisticsIncreasePaymentDTO extends BaseDto {

    private static final long serialVersionUID = -503730050848395283L;
    
    @ApiModelProperty(notes = "统计开始时间( 根据时间间隔 决定 数据条数)" ,position = 0)
    private LocalDateTime startTime;

    @ApiModelProperty(notes = "统计结束时间" ,position = 1)
    private LocalDateTime endTime;

    @ApiModelProperty(notes = "缴费次数" ,position = 2)
    @Range(min=0, max=1215752191L,message = "缴费次数 必须>=0 和 <=1215752191")
    private Integer paymentNum;

    @ApiModelProperty(notes = "缴费总金额( 开始时间-结束时间的金额)" ,position = 3)
    @DecimalMin(value = "0",message = "缴费总金额 必须>=0")
    @DecimalMax(value = "9999999999",message = "缴费总金额 必须<=9999999999")
    private BigDecimal paymentMoney;

    @ApiModelProperty(notes = "男性缴费金额数 (预留字段)" ,position = 4)
    private BigDecimal paymentBoyMoneyTotal;

    @ApiModelProperty(notes = "女性缴费金额数 (预留字段)" ,position = 5)
    private BigDecimal paymentGirlMoneyTotal;

}

