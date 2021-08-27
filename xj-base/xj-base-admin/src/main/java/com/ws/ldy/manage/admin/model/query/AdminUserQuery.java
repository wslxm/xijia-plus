package com.ws.ldy.manage.admin.model.query;


import com.ws.ldy.core.base.model.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 *   用户
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/14 21:06
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "AdminUserQuery", description = "用户表")
public class AdminUserQuery extends BaseQuery {

    private static final long serialVersionUID = 4934650100711613453L;

    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "账号/用户名")
    private String username;
    @ApiModelProperty(value = "电话")
    private String phone;
    @ApiModelProperty(value = "昵称")
    private String fullName;
    @ApiModelProperty(value = "禁用（0-否，1-是）")
    private Integer disable;
    @ApiModelProperty(value = "职位（字典code）")
    private Integer position;
    @ApiModelProperty(value = "终端（字典code）")
    private Integer terminal;


}
