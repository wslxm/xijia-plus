package io.github.wslxm.springbootplus2.manage.sys.model.vo;


import io.github.wslxm.springbootplus2.core.base.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/14 21:06
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "SysUserVO", description = "用户表")
public class SysUserVO extends BaseVo {

    private static final long serialVersionUID = 4934650100711613453L;
    @ApiModelProperty(value = "头像")
    private String headPic;
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
    private String depIds;
    @ApiModelProperty(value = "职位（字典code）")
    private Integer position;
    @ApiModelProperty(value = "微信OpenId")
    private Integer wxOpenId;
    @ApiModelProperty(value = "备注")
    private String remarks;
    @ApiModelProperty(value = "用户角色ids,id 查询/列表查询 都返回")
    private List<String> roleIds;
    @ApiModelProperty(value = "用户角色信息,id 查询/列表查询 都返回")
    private List<SysUserRolesVO> roles;
    @ApiModelProperty(value = "公司/部门信息,存在下级关联数据")
    private DepVO dep;
    @ApiModelProperty(value = "公司/部门信息 / 公司/部门信息")
    private String depNames;
    @ApiModelProperty(value = "扩展字段 1")
    private String ext1;
    @ApiModelProperty(value = "扩展字段 2")
    private String ext2;
    @ApiModelProperty(value = "扩展字段 3")
    private String ext3;
}
