package io.github.wslxm.springbootplus2.manage.test.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.github.wslxm.springbootplus2.core.base.model.BaseVo;
import io.github.wslxm.springbootplus2.core.utils.tree.BaseTree;

/**
 * 基础表--菜单 VO
 *
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>

 * @author ws
 * @email 1720696548@qq.com
 * @date 2022-12-28 20:24:04
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "GcMenuVO 对象", description = "基础表--菜单")
public class GcMenuVO extends BaseTree<GcMenuVO,String>  {

    private static final long serialVersionUID = -768055222454063104L;
    
    @ApiModelProperty(value = "指定父id" ,position = 0)
    private String pid;

    @ApiModelProperty(value = "菜单名" ,position = 1)
    private String name;

    @ApiModelProperty(value = "第二路由 (前后端分离前端使用第二路由)" ,position = 2)
    private String twoUrl;

    @ApiModelProperty(value = "菜单url" ,position = 3)
    private String url;

    @ApiModelProperty(value = "图标" ,position = 4)
    private String icon;

    @ApiModelProperty(value = "排序" ,position = 5)
    private Integer sort;

    @ApiModelProperty(value = "目录级别(1-系统, 2-菜单 ，3-页面, 4-按钮)" ,position = 6)
    private Integer root;

    @ApiModelProperty(value = "禁用(0-启用 1-禁用)" ,position = 7)
    private Integer disable;

}

