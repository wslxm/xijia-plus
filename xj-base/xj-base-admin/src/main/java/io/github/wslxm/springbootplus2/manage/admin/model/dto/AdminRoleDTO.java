package io.github.wslxm.springbootplus2.manage.admin.model.dto;

import io.github.wslxm.springbootplus2.core.base.model.Convert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 *   角色
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/14 20:48
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "AdminRoleDTO", description = "角色")
public class AdminRoleDTO extends Convert {

    private static final long serialVersionUID = 5684918982089765949L;
    @ApiModelProperty(value = "角色名")
    private String name;
    @ApiModelProperty(value = "描叙")
    private String desc;
    @ApiModelProperty(value = "查询code")
    private String code;
    @ApiModelProperty(value = "禁用（0-否，1-是）")
    private Integer disable;
    @ApiModelProperty(value = "角色的菜单列表ids, 存在数据才分配,不存在数据不做任何处理")
    private List<String> menuIds;
}

