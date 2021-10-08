package com.github.wslxm.springbootplus2.starter.wechat.mp.entity;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class WxMpUserInfoVO implements Serializable {


    private static final long serialVersionUID = 1226534278274759013L;
    @ApiModelProperty(notes = "用户的唯一标识" ,position = 0)
    private String openid;
    @ApiModelProperty(notes = "用户昵称" ,position = 0)
    private String nickname;
    @ApiModelProperty(notes = "用户的性别，值为1时是男性，值为2时是女性，值为0时是未知" ,position = 0)
    private String sex;
    @ApiModelProperty(notes = "用户个人资料填写的省份" ,position = 0)
    private String province;
    @ApiModelProperty(notes = "普通用户个人资料填写的城市" ,position = 0)
    private String city;
    @ApiModelProperty(notes = "国家，如中国为CN" ,position = 0)
    private String country;
    @ApiModelProperty(notes = "用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。" ,position = 0)
    private String headimgurl;
    @ApiModelProperty(notes = "用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）" ,position = 0)
    private String privilege;
    @ApiModelProperty(notes = "只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。" ,position = 0)
    private String unionid;
}
