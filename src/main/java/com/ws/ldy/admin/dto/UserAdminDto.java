package com.ws.ldy.admin.dto;


import com.ws.ldy.base.entity.BaseAdminDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * TODO  用户
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/14 21:06
 */
@Data
@ApiModel(value = "RoleUserAdminDto", description = "用户表")
public class UserAdminDto extends BaseAdminDto {

    private static final long serialVersionUID = 4934650100711613453L;

    @ApiModelProperty(value = "头像")
    private String head;
    @ApiModelProperty(value = "昵称")
    private String username;
    @ApiModelProperty(value = "账号")
    private String account;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "地址")
    private String address;
    @ApiModelProperty(value = "年龄")
    private Integer age;
    @ApiModelProperty(value = "性别（1男，2女）")
    private Integer gender;
    @ApiModelProperty(value = "部门id")
    private Integer empId;
    @ApiModelProperty(value = "注册时间")
    private Date time;
}
