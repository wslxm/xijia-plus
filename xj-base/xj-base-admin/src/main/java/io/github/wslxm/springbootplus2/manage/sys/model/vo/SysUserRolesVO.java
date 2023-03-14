package io.github.wslxm.springbootplus2.manage.sys.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@ApiModel(value = "SysUserRolesVO 对象", description = "用户角色")
public class SysUserRolesVO {
    @ApiModelProperty(notes = "id", position = 1)
    private String id;
    @ApiModelProperty(notes = "用户Id", position = 2)
    private String userId;
    @ApiModelProperty(notes = "角色Id", position = 3)
    private String roleId;
    @ApiModelProperty(notes = "角色code", position = 4)
    private String code;
    @ApiModelProperty(notes = "角色名称", position = 5)
    private String name;

}
