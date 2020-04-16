package com.ws.ldy.admin.model.dto;

import com.ws.ldy.base.model.dto.BaseAdminDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * TODO  角色-菜单关联表
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/14 20:49
 */
@Data
@ApiModel(value = "RoleMenuAdminDto", description = " 角色-菜单关联表")
public class RoleMenuAdminDto extends BaseAdminDto {

    private static final long serialVersionUID = 7936919715202241575L;


    @ApiModelProperty(value = "菜单id")
    private int menuId;

    @ApiModelProperty(value = "角色id")
    private int roleId;
}
