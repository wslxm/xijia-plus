package com.ws.ldy.modules.yw.pets.model.vo;

import com.ws.ldy.modules.sys.base.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

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
@ApiModel(value = "PetsCapitalVO 对象", description = "互助资金表")
public class PetsCapitalVO extends BaseVo {

    private static final long serialVersionUID = -503730669806030854L;
    
    @ApiModelProperty(notes = "平台资金累积总金额" ,position = 0)
    private BigDecimal moneyTotal;

    @ApiModelProperty(notes = "平台收益总额度" ,position = 1)
    private BigDecimal moneyProfit;

    @ApiModelProperty(notes = "资金池剩余额度" ,position = 2)
    private BigDecimal moneySurplus;

    @ApiModelProperty(notes = "平台互助金已发放总金额" ,position = 3)
    private BigDecimal moneyPayment;

}

