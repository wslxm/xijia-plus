package com.ws.ldy.client.yw.pets.model.dto;

import com.ws.ldy.modules.sys.base.model.Convert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Data
@ToString(callSuper = true)
@ApiModel(value = "UpdHeadOrNameOrPhoneDTO", description = "修改用户头像/电话/手机号")
public class UpdHeadOrNameOrPhoneDTO extends Convert {


    private static final long serialVersionUID = -8706991164449560297L;
    @ApiModelProperty(notes = "修改类型-固定值 (1-姓名  2-电话  3-头像)", position = 0)
    @Range(min = 1, max = 3, message = "修改类型  必须>=1 和 <=3")
    private Integer type;

    @ApiModelProperty(notes = "类型1--姓名", position = 1)
    @Length(min = 1, max = 32, message = "用户头像  必须>=0 和 <=32位")
    private String wxName;

    @ApiModelProperty(notes = "类型2--电话", position = 2)
    @Length(min = 11, max = 11, message = "用户电话  必须为11位")
    private String phone;

    @ApiModelProperty(notes = "类型2--电话验证码", position = 2)
    @Length(min = 6, max = 6, message = "验证码  必须为6位")
    private String code;

    @ApiModelProperty(notes = "类型3--用户头像 (默认使用微信头像)", position = 3)
    @Length(min = 1, max = 512, message = "用户头像  必须>=0 和 <=512位")
    private String wxHeadPic;
}
