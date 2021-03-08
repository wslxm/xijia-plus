package com.ws.ldy.modules.sys.admin.model.dto;

import com.ws.ldy.modules.sys.base.model.BaseDto;
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
public class AdminRoleDTO extends BaseDto {


    private static final long serialVersionUID = 5684918982089765949L;

    @ApiModelProperty(value = "角色名")
    private String name;

    @ApiModelProperty(value = "描叙")
    private String desc;

    @ApiModelProperty(value = "查询code")
    private String code;

    @ApiModelProperty(value = "禁用（0-否，1-是）")
    private Integer disable;

    @ApiModelProperty(value = "角色的菜单列表ids")
    private  List<String> menuIds;

}

