package com.ws.ldy.modules.yw.pets.model.dto;

import com.ws.ldy.modules.sys.base.model.Convert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 用户表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 11:03:46
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "PetsUserDTO 对象", description = "用户表")
public class PetsUserDTO extends Convert {

    private static final long serialVersionUID = -503733462503657497L;
    
    @ApiModelProperty(notes = "姓名" ,position = 0)
    private String fullName;

    @ApiModelProperty(notes = "电话" ,position = 1)
    private String phone;

    @ApiModelProperty(notes = "验证码" ,position = 1)
    private String code;

    @ApiModelProperty(notes = "身份证号" ,position = 2)
    private String idCard;

    @ApiModelProperty(notes = "身份证前" ,position = 3)
    private String idCardFrontPic;

    @ApiModelProperty(notes = "身份证后" ,position = 4)
    private String idCardAfterPic;

    @ApiModelProperty(notes = "省" ,position = 5)
    private String province;

    @ApiModelProperty(notes = "市" ,position = 6)
    private String city;

    @ApiModelProperty(notes = "区" ,position = 7)
    private String area;

//    @ApiModelProperty(notes = "用户微信openId" ,position = 8)
//    @Length(min=1, max=32,message = "用户微信openId 必须>=0 和 <=32位")
//    private String wxOpenId;

//    @ApiModelProperty(notes = "用户头像 (默认使用微信头像)" ,position = 9)
//    @Length(min=1, max=512,message = "用户头像  必须>=0 和 <=512位")
//    private String wxHeadPic;
//
//    @ApiModelProperty(notes = "用户昵称( 默认微信昵称)" ,position = 10)
//    @Length(min=1, max=255,message = "用户昵称 必须>=0 和 <=255位")
//    private String wxName;

//    @ApiModelProperty(notes = "1-禁用-黑名单 / 0-启用" ,position = 11)
//    @Range(min=0, max=9L,message = "1-禁用-黑名单 / 0-启用 必须>=0 和 <=9")
//    private Integer disable;

}

