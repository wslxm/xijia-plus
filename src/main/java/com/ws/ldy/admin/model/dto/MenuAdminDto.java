package com.ws.ldy.admin.model.dto;

import com.ws.ldy.admin.enums.MenuRootEnum;
import com.ws.ldy.base.model.dto.BaseAdminDto;
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
public class MenuAdminDto extends BaseAdminDto {

    private static final long serialVersionUID = -33297418791559528L;
    @ApiModelProperty(value = "目录级别(1，系统, 2、菜单, 3、页面)")
    private MenuRootEnum root;
    @ApiModelProperty(value = "指定父id")
    private Integer pid;
    @ApiModelProperty(value = "菜单名")
    private String name;
    @ApiModelProperty(value = "菜单url")
    private String url;
    @ApiModelProperty(value = "图标")
    private String icon;
    @ApiModelProperty(value = "排序")
    private Integer sort;

}