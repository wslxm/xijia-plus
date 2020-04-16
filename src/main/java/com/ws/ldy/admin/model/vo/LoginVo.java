package com.ws.ldy.admin.model.vo;

import com.ws.ldy.base.model.convert.Convert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel(value = "LoginVo", description = "登录token返回对象")
public class LoginVo extends Convert {

    @ApiModelProperty(value = "token")
    private String token;
}
