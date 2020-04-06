package com.ws.ldy.admin.vo;

import com.ws.ldy.base.entity.Convert;
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
