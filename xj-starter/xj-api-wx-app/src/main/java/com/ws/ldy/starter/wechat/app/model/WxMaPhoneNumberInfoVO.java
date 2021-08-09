package com.ws.ldy.starter.wechat.app.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString(callSuper = true)
@ApiModel(value = "WxMaPhoneNumberInfoVO 对象", description = "获取手机号")
public class WxMaPhoneNumberInfoVO implements Serializable {

    private static final long serialVersionUID = 6046304959556643824L;
    @ApiModelProperty(value = "用户绑定的手机号（国外手机号会有区号）")
    private String phoneNumber;
    @ApiModelProperty(value = "没有区号的手机号")
    private String purePhoneNumber;
    @ApiModelProperty(value = "区号")
    private String countryCode;
}
