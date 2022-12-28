package io.github.wslxm.springbootplus2.manage.test.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.github.wslxm.springbootplus2.core.base.model.BaseEntity;

/**
 * 基础表--菜单 Entity
 *
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>

 * @author ws
 * @email 1720696548@qq.com
 * @date 2022-12-28 17:38:58
 */
@Data
@ToString(callSuper = true)
@TableName("t_gc_menu")
@ApiModel(value = "GcMenu 对象", description = "基础表--菜单")
public class GcMenu extends BaseEntity {

    private static final long serialVersionUID = -768013672055443456L;
    
    @ApiModelProperty(notes = "指定父id" ,position = 0)
    @TableField(value = "pid")
    private String pid;

    @ApiModelProperty(notes = "菜单名" ,position = 1)
    @TableField(value = "`name`")
    private String name;

    @ApiModelProperty(notes = "第二路由 (前后端分离前端使用第二路由)" ,position = 2)
    @TableField(value = "two_url")
    private String twoUrl;

    @ApiModelProperty(notes = "菜单url" ,position = 3)
    @TableField(value = "url")
    private String url;

    @ApiModelProperty(notes = "图标" ,position = 4)
    @TableField(value = "`icon`")
    private String icon;

    @ApiModelProperty(notes = "排序" ,position = 5)
    @TableField(value = "`sort`")
    private Integer sort;

    @ApiModelProperty(notes = "目录级别(1-系统, 2-菜单 ，3-页面, 4-按钮)" ,position = 6)
    @TableField(value = "root")
    private Integer root;

    @ApiModelProperty(notes = "禁用(0-启用 1-禁用)" ,position = 7)
    @TableField(value = "`disable`")
    private Integer disable;

}

