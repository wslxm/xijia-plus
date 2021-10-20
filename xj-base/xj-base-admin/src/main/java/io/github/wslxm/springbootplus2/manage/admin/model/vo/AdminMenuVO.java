package io.github.wslxm.springbootplus2.manage.admin.model.vo;

import io.github.wslxm.springbootplus2.core.base.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 *   菜单
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/14 20:49
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "AdminMenuVO", description = "菜单表")
public class AdminMenuVO extends BaseVo {

    private static final long serialVersionUID = -33297418791559528L;


    @ApiModelProperty(value = "指定父id")
    private String pid;
    @ApiModelProperty(value = "菜单名")
    private String name;
    @ApiModelProperty(value = "菜单url")
    private String url;
    @ApiModelProperty(value = "路径-vue路由字段")
    private String pathx;
    @ApiModelProperty(value = "第二菜单url, 如url不满足前后端分离, 可使用第二路由")
    private String twoUrl;
    @ApiModelProperty(value = "图标")
    private String icon;
    @ApiModelProperty(value = "排序")
    private Integer sort;
    @ApiModelProperty(value = "目录级别(1，系统, 2、一级菜单 ，3，二级菜单, 4、页面)")
    private Integer root;
    @ApiModelProperty(value = "禁用（0-否，1-是）")
    private Integer disable;
    @ApiModelProperty(value = "当前页面权限id")
    private Integer authority;
    @ApiModelProperty(value = "终端 (字段code)")
    private Integer terminal;
    @ApiModelProperty(value = "当前节点的子节点，获取菜单树数据使用")
    private List<AdminMenuVO> menus;
    @ApiModelProperty(value = "是否选中（是否有权限，前台复选框默认选中需要值）")
    private Boolean isChecked;
}
