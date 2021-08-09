package com.ws.ldy.manage.admin.model.dto.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 用户权限分配
 * @author wangsong
 * @date 2020/9/23 0023 11:47
 * @return
 * @version 1.0.0
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "RoleAuthDTO 对象", description = "用户权限分配 参数")
public class RoleAuthDTO {


    @ApiModelProperty(notes = "用户Id", position = 0)
    private String roleId;

    @ApiModelProperty(notes = "角色Ids", position = 1)
    private List<String> authIds;


}
