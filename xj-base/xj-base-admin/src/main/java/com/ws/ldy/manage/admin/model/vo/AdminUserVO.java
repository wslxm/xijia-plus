package com.ws.ldy.manage.admin.model.vo;


import com.ws.ldy.core.base.model.BaseVo;
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
    @ApiModelProperty(value = "账号/用户名")
    private String username;
    @ApiModelProperty(value = "手机号/第二账号")
    private String phone;
    @ApiModelProperty(value = "昵称")
    private String fullName;
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
    private LocalDateTime endTime;
    @ApiModelProperty(value = "公司/部门Id")
    private String ogranId;
    @ApiModelProperty(value = "职位（字典code）")
    private Integer position;
    @ApiModelProperty(value = "终端 (字段code)")
    private Integer terminal;
    @ApiModelProperty(value = "终端 (字段code)")
    private Integer wxOpenId;
    @ApiModelProperty(value = "备注")
    private String remarks;
    /*  @ApiModelProperty(value = "是否选中(角色是否有改用户，前台复选框默认选中需要值)")
      private Boolean isChecked;*/
    @ApiModelProperty(value = "用户角色ids, id 查询存在")
    private List<String> roleIds;
    @ApiModelProperty(value = "用户角色信息,id 查询存在/列表查询都返回(只返回id/name)")
    private List<AdminRoleVO> roles;
    @ApiModelProperty(value = "公司/部门信息,存在下级关联数据")
    private AdminOgranVO ogran;
}
