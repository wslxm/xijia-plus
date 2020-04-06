package com.ws.ldy.admin.dto;

import com.ws.ldy.base.entity.BaseAdminDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * TODO  权限列表
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Mon Nov 25 08:02:49 CST 2019
 */
@Data
@ApiModel(value = "AuthorityAdminDto", description = "接口权限")
public class AuthorityAdminDto extends BaseAdminDto {

    private static final long serialVersionUID = 0L;

    @ApiModelProperty(value = "权限类")
    private Integer pid;
    @ApiModelProperty(value = "权限名")
    private String name;
    @ApiModelProperty(value = "权限url")
    private String url;
    @ApiModelProperty(value = "权限描叙")
    private String desc;
    @ApiModelProperty(value = "请求方式")
    private String type;

}

