package io.github.wslxm.springbootplus2.manage.sys.model.dto.login;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 手机号+密码登录 dto
 *
 * @author wangsong
 * @date 2023/04/07
 */
@Data
public class PhonePasswordLoginDTO {

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "密码,加密传输")
    private String password;
}
