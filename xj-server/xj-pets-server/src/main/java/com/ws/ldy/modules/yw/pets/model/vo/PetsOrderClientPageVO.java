package com.ws.ldy.modules.yw.pets.model.vo;


import com.ws.ldy.modules.sys.base.model.Convert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ToString(callSuper = true)
@ApiModel(value = "PetsOrderClientPageVO 对象", description = "支付订单/缴费成功")
public class PetsOrderClientPageVO extends Convert {

    private static final long serialVersionUID = 7016080519821509732L;
    @ApiModelProperty(notes = "订单id", position = 0)
    private String id;

    @ApiModelProperty(notes = "支付时间" ,position = 11)
    private LocalDateTime payTime;

    @ApiModelProperty(notes = "支付金额", position = 2)
    private BigDecimal money;

    @ApiModelProperty(notes = "宠物昵称", position = 3)
    private String petNickname;

    @ApiModelProperty(notes = "宠物id", position = 4)
    private String petId;

}
