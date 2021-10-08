package com.github.wslxm.springbootplus2.starter.qq.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 *
 * @author Administrator
 * @date 2018/10/30/030
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "QQLoginVO 对象", description = "qq登录")
public class QQLoginVO implements Serializable {

	@ApiModelProperty(notes = "openId", position = 0)
	private String qqOpenId;
    @ApiModelProperty(notes = "昵称", position = 0)
    private String nickname;
	@ApiModelProperty(notes = "性别(字典code，1男 2女 0未知)", position = 0)
    private Integer genderCode;
	@ApiModelProperty(notes = "openId", position = 0)
	private String headPic;
	@ApiModelProperty(notes = "生日(只能获取年限-默认 ?-01-01)" ,position = 8)
	private String birthday;
}