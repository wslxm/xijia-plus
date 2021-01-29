package com.ws.ldy.modules.yw.statistics.model.vo;

import com.ws.ldy.modules.sys.base.model.Convert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

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
@ApiModel(value = "StatisticsTotalInfoVO 对象", description = "平台基础数据统计表(每小时一次)")
public class StatisticsTotalInfoVO extends Convert {

    private static final long serialVersionUID = -503730090987884554L;
    
    @ApiModelProperty(notes = "平台资金累积总金额" ,position = 0)
    private BigDecimal moneyTotal;

    @ApiModelProperty(notes = "平台收益总额度" ,position = 1)
    private BigDecimal moneyProfit;

    @ApiModelProperty(notes = "资金池剩余额度" ,position = 2)
    private BigDecimal moneySurplus;

    @ApiModelProperty(notes = "平台互助金已发放总金额" ,position = 3)
    private BigDecimal moneyPayment;

    @ApiModelProperty(notes = "平台宠物总数" ,position = 4)
    private Long petsTotal;

    @ApiModelProperty(notes = "平台狗宠物总数" ,position = 4)
    private  Long petDogTotal;

    @ApiModelProperty(notes = "平台猫宠物总数" ,position = 4)
    private  Long petCatTotal;

    @ApiModelProperty(notes = "平台用户总数" ,position = 5)
    private Long userTotal;

    @ApiModelProperty(notes = "平台女性总数" ,position = 6)
    private Long girlUserTotal;

    @ApiModelProperty(notes = "平台男性总数" ,position = 7)
    private Long boyUserTotal;

    @ApiModelProperty(notes = "申报用户总数" ,position = 8)
    private Long declareUserTotal;

    @ApiModelProperty(notes = "平台续费用户总数" ,position = 9)
    private Long renewalUserTotal;

    @ApiModelProperty(notes = "统计时间 ( 根据统计时间间隔 决定 数据条数)" ,position = 10)
    private LocalDateTime time;

}

