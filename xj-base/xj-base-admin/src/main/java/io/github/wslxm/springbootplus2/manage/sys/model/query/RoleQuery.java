package io.github.wslxm.springbootplus2.manage.sys.model.query;

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
@ApiModel(value = "RoleQuery", description = "角色")
public class RoleQuery extends BaseQuery {

    private static final long serialVersionUID = 5684918982089765949L;

    @ApiModelProperty(value = "角色名-模糊查询", position = 1)
    private String name;

    @ApiModelProperty(value = "角色code", position = 3)
    private String code;

    @ApiModelProperty(value = "启用/禁用", position = 3)
    private Integer disable;

    @ApiModelProperty(value = "用户id, 查询指定用户存在的角色", position = 3)
    private String userId;

    @ApiModelProperty(value = "用户id 查询模式, true-返回所有数据使用isChecked 标记用户是否存在角色  false-只查询出该用户存在的角色数据(默认)  ", position = 4)
    private Boolean isUserIdChecked;

    @ApiModelProperty(value = "是否只查询当前登录人创建的角色, true-是 false-否(默认)", position = 5)
    private Boolean isLoginUser;

}

