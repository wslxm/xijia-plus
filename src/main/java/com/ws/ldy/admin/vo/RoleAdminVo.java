package com.ws.ldy.admin.vo;

import com.ws.ldy.base.entity.BaseAdminVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * TODO  角色  
 * @author 王松
 * @WX-QQ 1720696548
 * @date  2019/11/14 20:48 
 */
@Data
@ApiModel(value = "RoleAdminVo", description = "角色表")
public class RoleAdminVo extends BaseAdminVo {

    @ApiModelProperty(value = "角色名")
    private String name;

    @ApiModelProperty(value = "描叙")
    private String desc;

    @ApiModelProperty(value = "角色分配, 当前用户拥有角色为true")
    private Boolean checked;

}

