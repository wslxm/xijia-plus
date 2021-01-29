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
 * 平台基础数据统计表(每小时一次)
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 10:50:22
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "StatisticsTotalInfoDTO 对象", description = "平台基础数据统计表(每小时一次)")
public class StatisticsTotalInfoDTO extends BaseDto {

    private static final long serialVersionUID = -503730091013050378L;
    
    @ApiModelProperty(notes = "平台资金累积总金额" ,position = 0)
    @DecimalMin(value = "0",message = "平台资金累积总金额 必须>=0")
    @DecimalMax(value = "9999999999",message = "平台资金累积总金额 必须<=9999999999")
    private BigDecimal moneyTotal;

    @ApiModelProperty(notes = "平台收益总额度" ,position = 1)
    @DecimalMin(value = "0",message = "平台收益总额度 必须>=0")
    @DecimalMax(value = "9999999999",message = "平台收益总额度 必须<=9999999999")
    private BigDecimal moneyProfit;

    @ApiModelProperty(notes = "资金池剩余额度" ,position = 2)
    @DecimalMin(value = "0",message = "资金池剩余额度 必须>=0")
    @DecimalMax(value = "9999999999",message = "资金池剩余额度 必须<=9999999999")
    private BigDecimal moneySurplus;

    @ApiModelProperty(notes = "平台互助金已发放总金额" ,position = 3)
    @DecimalMin(value = "0",message = "平台互助金已发放总金额 必须>=0")
    @DecimalMax(value = "9999999999",message = "平台互助金已发放总金额 必须<=9999999999")
    private BigDecimal moneyPayment;

    @ApiModelProperty(notes = "平台宠物总数" ,position = 4)
    @Range(min=0, max=7766279631452241920L,message = "平台宠物总数 必须>=0 和 <=7766279631452241920")
    private Long petsTotal;

    @ApiModelProperty(notes = "平台用户总数" ,position = 5)
    @Range(min=0, max=7766279631452241920L,message = "平台用户总数 必须>=0 和 <=7766279631452241920")
    private Long userTotal;

    @ApiModelProperty(notes = "平台女性总数" ,position = 6)
    @Range(min=0, max=7766279631452241920L,message = "平台女性总数 必须>=0 和 <=7766279631452241920")
    private Long girlUserTotal;

    @ApiModelProperty(notes = "平台男性总数" ,position = 7)
    @Range(min=0, max=7766279631452241920L,message = "平台男性总数 必须>=0 和 <=7766279631452241920")
    private Long boyUserTotal;

    @ApiModelProperty(notes = "申报用户总数" ,position = 8)
    @Range(min=0, max=7766279631452241920L,message = "申报用户总数 必须>=0 和 <=7766279631452241920")
    private Long declareUserTotal;

    @ApiModelProperty(notes = "平台续费用户总数" ,position = 9)
    @Range(min=0, max=7766279631452241920L,message = "平台续费用户总数 必须>=0 和 <=7766279631452241920")
    private Long renewalUserTotal;

    @ApiModelProperty(notes = "统计时间 ( 根据统计时间间隔 决定 数据条数)" ,position = 10)
    private LocalDateTime time;

}

