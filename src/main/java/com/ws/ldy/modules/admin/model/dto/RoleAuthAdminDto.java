package com.ws.ldy.modules.admin.model.dto;

import com.ws.ldy.others.base.model.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * TODO  角色接口权限
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Mon Nov 25 11:38:29 CST 2019
 */
@Data
@ApiModel(value = "RoleAuthAdminDto", description = " 角色-接口关联表")
public class RoleAuthAdminDto extends BaseDto {


    @ApiModelProperty(value = "接口id")
    private Integer authId;

    @ApiModelProperty(value = "角色id")
    private Integer roleId;


}

