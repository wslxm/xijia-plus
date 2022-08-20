package io.github.wslxm.springbootplus2.manage.admin.model.dto;

import io.github.wslxm.springbootplus2.core.base.model.Convert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 *   菜单
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/14 20:49
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "AdminMenuDTO", description = "菜单")
public class AdminMenuDTO extends Convert {

    private static final long serialVersionUID = -33297418791559528L;
    @ApiModelProperty(value = "目录级别(1，系统, 2、菜单, 3、页面)")
    private Integer root;
    @ApiModelProperty(value = "指定父id")
    private String pid;
    @ApiModelProperty(value = "菜单名")
    private String name;
    @ApiModelProperty(value = "菜单url")
    private String url;
    @ApiModelProperty(value = "第二菜单url, 如url不满足前后端分离, 可使用第二路由")
    private String twoUrl;
    @ApiModelProperty(value = "图标")
    private String icon;
    @ApiModelProperty(value = "排序")
    private Integer sort;
    @ApiModelProperty(value = "禁用（0-否，1-是）")
    private Integer disable;
}
