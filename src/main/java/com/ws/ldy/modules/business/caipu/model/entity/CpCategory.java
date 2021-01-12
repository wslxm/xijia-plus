package com.ws.ldy.modules.business.caipu.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.modules.sys.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 字典表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-10-04 21:44:55
 */
@Data
@ToString(callSuper = true)
@TableName("t_xj_cp_category")
@ApiModel(value = "CpCategory 对象", description = "菜谱类别字典表")
public class CpCategory extends BaseEntity {

    private static final long serialVersionUID = 0L;
    
    @ApiModelProperty(notes = "字典类型" ,position = 0)
    @TableField(value = "code")
    private String code;

    @ApiModelProperty(notes = "字典名称" ,position = 1)
    @TableField(value = "`name`")
    private String name;

    @ApiModelProperty(notes = "父Id" ,position = 2)
    @TableField(value = "pid")
    private String pid;

    @ApiModelProperty(notes = "描叙" ,position = 3)
    @TableField(value = "`desc`")
    private String desc;

    @ApiModelProperty(notes = "排序" ,position = 4)
    @TableField(value = "`sort`")
    private Integer sort;

    @ApiModelProperty(notes = "禁用(0-否 1-是)" ,position = 5)
    @TableField(value = "`disable`")
    private Integer disable;

}

