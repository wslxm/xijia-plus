package com.ws.ldy.modules.yw.pets.model.vo;


import com.ws.ldy.modules.sys.base.model.Convert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString(callSuper = true)
@ApiModel(value = "PetsDeclareFindDeclareSuccessCityVO 对象", description = "城市统计需要的数据查询")
public class PetsDeclareFindDeclareSuccessCityVO extends Convert {
    private static final long serialVersionUID = 1538518610702100520L;

    @ApiModelProperty(notes = "市", position = 1)
    private String city;

    @ApiModelProperty(notes = "申报金额/实发", position = 1)
    private BigDecimal paidInAmount;
}
