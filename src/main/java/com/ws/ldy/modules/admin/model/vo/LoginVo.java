package com.ws.ldy.modules.admin.model.vo;

import com.ws.ldy.others.base.model.Convert;
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
    @ApiModelProperty(value = "用户姓名")
    private String username;
    @ApiModelProperty(value = "头像")
    private String head;
}
