package com.github.wslxm.springbootplus2.manage.admin.model.vo;

import com.github.wslxm.springbootplus2.core.base.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 *   代码生成器自动生成，请自定义描叙
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Mon Nov 25 08:02:49 CST 2019
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "AdminAuthorityVO", description = "接口权限 ")
public class AdminAuthorityVO extends BaseVo {

    private static final long serialVersionUID = 0L;

    @ApiModelProperty(value = "权限类")
    private String pid;
    @ApiModelProperty(value = "权限url")
    private String url;
    @ApiModelProperty(value = "权限描叙")
    private String desc;
    @ApiModelProperty(value = "请求方式")
    private String method;
    @ApiModelProperty(value = "禁用（0-否，1-是）")
    private Integer disable;
    @ApiModelProperty(value = "是否选中-是否选中（是否有权限，前台复选框默认选中需要值）")
    private Boolean isChecked = false;
    @ApiModelProperty(notes = "终端(字典code, 如 0-管理端 1-用户端)" ,position = 5)
    private Integer type;
    @ApiModelProperty(notes = "授权状态(字典code  0-无需登录 1-需登录 2-需登录+授权)" ,position = 6)
    private Integer state;
    @ApiModelProperty(notes = " 是否需要验签" ,position = 6)
    private  Boolean isSign;
    @ApiModelProperty(notes = "下级数据" ,position = 8)
    private List<AdminAuthorityVO> authoritys;
}

