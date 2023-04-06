package io.github.wslxm.springbootplus2.manage.sys.model.dto.login;

import io.github.wslxm.springbootplus2.core.base.annotation.XjSecret;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 登录
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2022/8/21 0021 18:08
 * @version 1.0.0
 */
@Data
public class LoginDTO {

    @ApiModelProperty(value = "账号或手机号")
    @Length(min = 1,max = 20,message = "账号必须大于1且小于20位")
    private String username;

    @ApiModelProperty(value = "密码")
    @XjSecret
    // @Length(min = 1,max = 20,message = "密码必须大于1且小于20位")
    private String password;

}
