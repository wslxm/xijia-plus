package io.github.wslxm.springbootplus2.manage.admin.model.dto;


import io.github.wslxm.springbootplus2.core.base.model.Convert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 *   用户
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/14 21:06
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "AdminUserDTO", description = "用户表")
public class AdminUserDTO extends Convert {

    private static final long serialVersionUID = 4934650100711613453L;

    @ApiModelProperty(value = "头像")
    private String head;
    @ApiModelProperty(value = "账号/用户名")
    private String username;
    @ApiModelProperty(value = "手机号/第二账号")
    private String phone;
    @ApiModelProperty(value = "昵称")
    private String fullName;
    @ApiModelProperty(value = "密码(编辑无法进行修改)")
    private String password;
    @ApiModelProperty(value = "地址")
    private String address;
    @ApiModelProperty(value = "年龄")
    private Integer age;
    @ApiModelProperty(value = "性别（1-男，0-女）")
    private Integer gender;
    @ApiModelProperty(value = "禁用（0-否，1-是）")
    private Integer disable;
    @ApiModelProperty(value = "职位（字典code）")
    private Integer position;
    @ApiModelProperty(value = "终端 (字段code)")
    private Integer terminal;
    @ApiModelProperty(value = "备注")
    private String remarks;
    @ApiModelProperty(value = "角色Id")
    private List<String> roleIds;
    @ApiModelProperty(value = "公司/部门id(选择的最后一级)")
    private String organId;
}
