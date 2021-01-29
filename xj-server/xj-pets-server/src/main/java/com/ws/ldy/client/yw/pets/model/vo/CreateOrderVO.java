package com.ws.ldy.client.yw.pets.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@ApiModel(value = "NewDeclareVO", description = "最新互助/申报成功")
public class CreateOrderVO {


    @ApiModelProperty(notes = "订单号", position = 1)
    private String orderNo;


}
