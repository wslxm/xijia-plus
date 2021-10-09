package io.github.wslxm.springbootplus2.manage.admin.model.query;

import io.github.wslxm.springbootplus2.core.base.model.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 角色
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/14 20:48
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "AdminRoleQuery", description = "角色")
public class AdminRoleQuery extends BaseQuery {

    private static final long serialVersionUID = 5684918982089765949L;

    @ApiModelProperty(value = "角色名-模糊查询", position = 1)
    private String name;

    @ApiModelProperty(value = "终端 (多平台,每个平台对应一个终端)", position = 2)
    private Integer terminal;

    @ApiModelProperty(value = "用户id,只查询指定用户存在的角色", position = 3)
    private String userId;

    @ApiModelProperty(value = "是否踢除userId 查询参数,踢除后用户是否存在角色将改为 isChecked=true/false 来标记, true-踢除 false-不踢除(默认)  ", position = 4)
    private Boolean isUserIdChecked = false;

    @ApiModelProperty(value = "是否只查询当前登录人创建的角色, true-是 false-否(默认),需要多层级的结构权限,在角色列表查询，和用户分配时使用传递true ", position = 5)
    private Boolean isLoginUser = false;

}

