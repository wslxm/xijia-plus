package com.ws.ldy.modules.admin.model.dto;

import com.ws.ldy.enums.admin.MenuRootEnum;
import com.ws.ldy.others.base.model.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * TODO  菜单
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/14 20:49
 */
@Data
@ApiModel(value = "MenuAdminDto", description = "菜单")
public class MenuAdminDto extends BaseDto {

    private static final long serialVersionUID = -33297418791559528L;
    @ApiModelProperty(value = "目录级别(1，系统, 2、菜单, 3、页面)")
    private MenuRootEnum root;
    @ApiModelProperty(value = "指定父id")
    private String pid;
    @ApiModelProperty(value = "菜单名")
    private String name;
    @ApiModelProperty(value = "菜单url")
    private String url;
    @ApiModelProperty(value = "图标")
    private String icon;
    @ApiModelProperty(value = "排序")
    private Integer sort;
    @ApiModelProperty(value = "0-启用/1-禁用")
    private Integer state;
}
