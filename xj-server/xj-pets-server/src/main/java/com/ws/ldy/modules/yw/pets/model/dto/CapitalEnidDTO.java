package com.ws.ldy.modules.yw.pets.model.dto;

import com.ws.ldy.modules.sys.base.model.Convert;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 资金编辑dto
 */

@Data
@ToString
@SuppressWarnings("all")
public class CapitalEnidDTO extends Convert {
    private static final long serialVersionUID = 9215675349244771249L;

    private BigDecimal addMoneyTotal;   // 增加-平台资金累积总金额
    private BigDecimal addMoneyProfit;  // 增加-平台收益总额度
    private BigDecimal addMoneySurplus; // 增加-资金池剩余额度
    private BigDecimal addMoneyPayment; // 增加-平台互助金已发放总金额
    //
    private BigDecimal subtractMoneyTotal;   // 减少-平台资金累积总金额
    private BigDecimal subtractMoneyProfit;  // 减少-平台收益总额度
    private BigDecimal subtractMoneySurplus; // 减少-资金池剩余额度
    private BigDecimal subtractMoneyPayment; // 减少-平台互助金已发放总金额
}
