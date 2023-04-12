package io.github.wslxm.springbootplus2.manage.sys.model.dto.login;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 帐户密码登录 dto
 *
 * @author wangsong
 * @date 2023/04/07
 */
@Data
public class AccountPasswordLoginDTO {

    @ApiModelProperty(value = "账号or手机号")
    private String username;

    @ApiModelProperty(value = "密码,加密传输")
    private String password;
}
