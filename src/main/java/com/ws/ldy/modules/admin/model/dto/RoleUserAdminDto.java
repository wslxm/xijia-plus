package com.ws.ldy.modules.admin.model.dto;

import com.ws.ldy.others.base.model.admin.BaseAdminDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * TODO  角色-用户关联表
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/14 20:49
 */
@Data
@ApiModel(value = "RoleUserAdminDto", description = "角色-用户关联表")
public class RoleUserAdminDto extends BaseAdminDto {


    private static final long serialVersionUID = 5610627763663770761L;


    @ApiModelProperty(value = "用户id")
    private int userId;


    @ApiModelProperty(value = "角色id")
    private int roleId;
}
