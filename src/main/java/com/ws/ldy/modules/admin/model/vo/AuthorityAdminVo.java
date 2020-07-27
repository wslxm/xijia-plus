package com.ws.ldy.modules.admin.model.vo;

import com.ws.ldy.others.base.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * TODO  代码生成器自动生成，请自定义描叙
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Mon Nov 25 08:02:49 CST 2019
 */
@Data
@ApiModel(value = "AuthorityAdminVo", description = "接口权限 ")
public class AuthorityAdminVo extends BaseVo {

    private static final long serialVersionUID = 0L;

    @ApiModelProperty(value = "权限类")
    private Integer pid;
    @ApiModelProperty(value = "权限url")
    private String url;
    @ApiModelProperty(value = "权限描叙")
    private String desc;
    @ApiModelProperty(value = "请求方式")
    private String method;
    @ApiModelProperty(value = "是否选中-是否选中（是否有权限，前台复选框默认选中需要值）")
    private Boolean isChecked;

}

