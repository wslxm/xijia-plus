package com.ws.ldy.client.yw.pets.model.dto;

import com.ws.ldy.modules.sys.base.model.Convert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ToString(callSuper = true)
@ApiModel(value = "LoginDTO", description = "登录dto")
public class LoginDTO extends Convert {

    private static final long serialVersionUID = -4096357681009897004L;

    @ApiModelProperty(notes = "用户微信openId", position = 8)
    @Length(min = 1, max = 32, message = "用户微信openId 必须>=0 和 <=32位")
    @NotBlank
    private String wxOpenId;

    @ApiModelProperty(notes = "用户头像 (默认使用微信头像)", position = 9)
    @Length(min = 1, max = 512, message = "用户头像  必须>=0 和 <=512位")
    @NotBlank
    private String wxHeadPic;

    @ApiModelProperty(notes = "用户昵称( 默认微信昵称)", position = 10)
    @Length(min = 1, max = 255, message = "用户昵称 必须>=0 和 <=255位")
    @NotBlank
    private String wxName;

    @ApiModelProperty(notes = "用户姓名( 默认微信性别)", position = 10)
    @NotNull
    private Integer wxGender;


}
