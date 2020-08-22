package com.ws.ldy.modules.admin.model.vo;


import com.ws.ldy.others.base.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
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
@ApiModel(value = "AdminUserVO", description = "用户表")
public class AdminUserVO extends BaseVo {

    private static final long serialVersionUID = 4934650100711613453L;

    @ApiModelProperty(value = "头像")
    private String head;
    @ApiModelProperty(value = "账号")
    private String username;
    @ApiModelProperty(value = "昵称")
    private String fullName;
    //    @ApiModelProperty(value = "密码")
//    private String password;
    @ApiModelProperty(value = "地址")
    private String address;
    @ApiModelProperty(value = "年龄")
    private Integer age;
    @ApiModelProperty(value = "性别（1男，0女）")
    private Integer gender;
    @ApiModelProperty(value = "禁用（0-否，1-是）")
    private Integer disable;
    @ApiModelProperty(value = "注册时间")
    private LocalDateTime regTime;
    @ApiModelProperty(value = "最后登录时间")
    private LocalDateTime entTime;
    @ApiModelProperty(value = "是否选中（角色是否有改用户，前台复选框默认选中需要值）")
    private Boolean isChecked;
    @ApiModelProperty(value = "用户权限")
    List<String> authList;


}
