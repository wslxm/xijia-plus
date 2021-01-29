package com.ws.ldy.modules.yw.pets.model.dto;

import com.ws.ldy.modules.sys.base.model.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

/**
 * 互助资金表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 10:52:40
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "PetsCapitalDTO 对象", description = "互助资金表")
public class PetsCapitalDTO extends BaseDto {

    private static final long serialVersionUID = -503730669827002394L;
    
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

    @ApiModelProperty(notes = "版本号" ,position = 3)
    private Integer version;

}

