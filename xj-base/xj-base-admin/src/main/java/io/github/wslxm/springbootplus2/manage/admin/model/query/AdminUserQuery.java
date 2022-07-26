package io.github.wslxm.springbootplus2.manage.admin.model.query;


import io.github.wslxm.springbootplus2.core.base.model.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

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
    @ApiModelProperty(value = "是否只查询当前登录人创建的用户,默认false", notes = "需要多层级的结构权限,在角色列表查询，和用户分配时使用传递true",position = 5)
    private Boolean isLoginUser;
    @ApiModelProperty(value = "注册开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regTimeStart;
    @ApiModelProperty(value = "注册结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regTimeEnd;

}
