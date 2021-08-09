package com.ws.ldy.starter.wechat.open.model.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString(callSuper = true)
@ApiModel(value = "WxUserInfoVO 对象", description = "wx登录")
public class WxUserInfoVO implements Serializable {

    private static final long serialVersionUID = 7702435078075689122L;
    @ApiModelProperty(notes = "用户昵称", position = 0)
    private String nickname;
    @ApiModelProperty(notes = "用户性别", position = 0)
    private String sex;
    @ApiModelProperty(notes = "openId", position = 0)
    private String openid;
    @ApiModelProperty(notes = "用户头像，最后一个数值代表正方形头像大小", position = 0)
    private List<String> headimgurl;
    @ApiModelProperty(notes = "普通用户个人资料填写的城市", position = 0)
    private String province;

}
