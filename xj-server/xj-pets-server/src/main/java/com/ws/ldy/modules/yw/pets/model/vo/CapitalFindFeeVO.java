package com.ws.ldy.modules.yw.pets.model.vo;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString(callSuper = true)
@ApiModel(value = "CapitalFindFeeVO 对象", description = "抽成/手续费计算")
public class CapitalFindFeeVO {

    /**
     * 平台抽成（元）
     */
    private BigDecimal platformFee;
    /**
     * 微信支付手续费（元）
     */
    private BigDecimal wxChannelFee;


}
