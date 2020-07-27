package com.ws.ldy.modules.admin.model.dto;

import com.ws.ldy.others.base.model.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * TODO  角色
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/14 20:48
 */
@Data
@ApiModel(value = "RoleAdminDto", description = "角色")
public class RoleAdminDto extends BaseDto {


    private static final long serialVersionUID = 5684918982089765949L;

    @ApiModelProperty(value = "角色名")
    private String name;

    @ApiModelProperty(value = "描叙")
    private String desc;
}

