package com.ws.ldy.modules.dev.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.others.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 学习计划
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-07-31 00:37:27
 */
@Data
@TableName("t_dev_study")
@ApiModel(value = "DevStudy 对象", description = "学习计划")
public class DevStudy extends BaseEntity {

    private static final long serialVersionUID = 0L;
    
    @ApiModelProperty(notes = "名称")
    @TableField(value = "`name`")
    private String name;

    @ApiModelProperty(notes = "学习内容(md-富文本)")
    @TableField(value = "content")
    private String content;

    @ApiModelProperty(notes = "排序")
    @TableField(value = "`sort`")
    private Integer sort;

}

