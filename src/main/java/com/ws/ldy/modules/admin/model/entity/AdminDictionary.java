package com.ws.ldy.modules.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.others.base.model.BaseEntity;
import lombok.Data;
import lombok.ToString;

/**
 *   代码生成器自动生成，请自定义描叙
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Sun Nov 24 11:23:12 CST 2019
 */
@Data
@ToString(callSuper = true)
@TableName(value = "t_admin_dictionary")
public class AdminDictionary extends BaseEntity {

    private static final long serialVersionUID = 0L;
    /**
     * 字典类型
     */
    @TableField(value = "code")
    private String code;
    /**
     * 字典名称
     */
    @TableField(value = "name")
    private String name;
    /**
     * 父Id
     */
    @TableField(value = "pid")
    private String pid;
    /**
     * 描叙
     */
    @TableField(value = "`desc`")
    private String desc;
    /**
     * 排序
     */
    @TableField(value = "`sort`")
    private Integer sort;

    /**
     * 禁用 0-否，1-是
     */
    @TableField(value = "`disable`")
    private Integer disable;



}

