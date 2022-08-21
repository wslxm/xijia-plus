package io.github.wslxm.springbootplus2.manage.sys.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.wslxm.springbootplus2.core.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 系统全局数据信息配置表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-08-31 18:31:44
 */
@Data
@ToString(callSuper = true)
@TableName("t_sys_config")
@ApiModel(value = "Config 对象", description = "系统全局数据信息配置表")
public class Config extends BaseEntity {

    private static final long serialVersionUID = 0L;
    
    @ApiModelProperty(notes = "配置code|搜索值(不能重复)" ,position = 0)
    @TableField(value = "code")
    private String code;

    @ApiModelProperty(notes = "配置名称" ,position = 1)
    @TableField(value = "`name`")
    private String name;

    @ApiModelProperty(notes = "类型(0-文本 1-图片)" ,position = 2)
    @TableField(value = "type")
    private Integer type;

    @ApiModelProperty(notes = "配置内容" ,position = 2)
    @TableField(value = "content")
    private String content;

    @ApiModelProperty(notes = "排序" ,position = 3)
    @TableField(value = "`sort`")
    private Integer sort;

    @ApiModelProperty(notes = "描述" ,position = 3)
    @TableField(value = "`desc`")
    private String desc;
    /**
     * 扩展字段 1
     */
    private String ext1;
    /**
     * 扩展字段 2
     */
    private String ext2;
    /**
     * 扩展字段 3
     */
    private String ext3;
}

