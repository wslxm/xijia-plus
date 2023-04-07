package io.github.wslxm.springbootplus2.manage.sys.model.dto.login;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 登录
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2022/8/21 0021 18:08
 * @version 1.0.0
 */
@Data
public class LoginDTO {

    @ApiModelProperty(value = "登录渠道 \n" +
            "登录方式: 登录渠道key -> 登录参数 data 参数对应的 dto \n" +
            "账号+密码: ACCOUNT_PASSWORD  ->  AccountPasswordLoginDTO\n" +
            "电话+密码: PHONE_PASSWORD  -> PhonePasswordLoginDTO \n" +
            "(账号or电话)+密码: ACCOUNT_OR_PHONE_PASSWORD -> AccountOrPhonePasswordLoginDTO \n" +
            "电话+手机验证码: PHONE_CODE : -> PhoneCodeLoginDTO (需自行实现登录逻辑)\n")
    private String channel;

    @ApiModelProperty(value = "登录参数")
    private Object data;
}
