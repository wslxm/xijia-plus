package com.github.wslxm.springbootplus2.manage.admin.model.dto.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 用户角色分配
 * @author wangsong
 * @date 2020/9/23 0023 11:47
 * @return
 * @version 1.0.0
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "UserRoleDTO 对象", description = "用户角色分配 参数")
public class UserRoleDTO {

    @ApiModelProperty(notes = "角色Id", position = 0)
    private String userId;

    @ApiModelProperty(notes = "菜单Id", position = 1)
    private List<String> roleIds;
}
