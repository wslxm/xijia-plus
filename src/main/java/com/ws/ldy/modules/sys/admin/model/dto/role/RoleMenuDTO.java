package com.ws.ldy.modules.sys.admin.model.dto.role;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
  * 用户菜单分配
  * @author wangsong
  * @mail  1720696548@qq.com
  * @date  2020/9/23 0023 11:51
  * @version 1.0.0
  */
@Data
@ToString(callSuper = true)
@ApiModel(value = "RoleMenuDTO 对象", description = "用户菜单分配 参数")
public class RoleMenuDTO {

    @ApiModelProperty(notes = "角色Id", position = 0)
    private String roleId;

    @ApiModelProperty(notes = "菜单Id", position = 1)
    private List<String> menuIds;
}
