package io.github.wslxm.springbootplus2.manage.sys.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.wslxm.springbootplus2.core.base.model.BaseEntity;
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
public class Config extends BaseEntity {

    private static final long serialVersionUID = 0L;

    /**
     * 配置code|搜索值(不能重复)
     */
    @TableField(value = "code")
    private String code;

    /**
     * 配置名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 配置内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 排序
     */
    @TableField(value = "`sort`")
    private Integer sort;

    /**
     * 类型(0-文本 1-图片 2-开关 3-富文本)
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 描述
     */
    @TableField(value = "`desc`")
    private String desc;

    /**
     * 扩展字段1
     */
    @TableField(value = "ext1")
    private String ext1;

    /**
     * 扩展字段2
     */
    @TableField(value = "ext2")
    private String ext2;

    /**
     * 扩展字段3
     */
    @TableField(value = "ext3")
    private String ext3;


}

