package com.ws.ldy.modules.dev.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.others.base.model.dev.BaseDevEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * TODO  开发规范
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-06-27 12:22:53
 */
@Data
@TableName("t_dev_norm")
@ApiModel(value = "DevNorm 对象", description = "开发规范")
public class DevNorm extends BaseDevEntity {

    private static final long serialVersionUID = 0L;
    
    @ApiModelProperty(notes = "规范名称")
    @TableField(value = "`name`")
    private String name;

    @ApiModelProperty(notes = "规范内容(富文本)")
    @TableField(value = "content")
    private String content;

    @ApiModelProperty(notes = "排序")
    @TableField(value = "`sort`")
    private Integer sort;

}

