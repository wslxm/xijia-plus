package com.ws.ldy.admin.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.base.model.entity.BaseAdminEntity;
import lombok.Data;

/**
 * TODO  代码生成器自动生成，请自定义描叙
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Sun Nov 24 11:23:12 CST 2019
 */
@Data
@TableName(value = "t_admin_dictionary")
public class DictionaryAdmin extends BaseAdminEntity {

    private static final long serialVersionUID = 0L;
    /**
     * 数据库自增id
     */
    @TableId(type = IdType.AUTO) //自增
    private int id;
    /**
     * 描叙
     */
    @TableField(value = "`desc`")
    private String desc;

    /**
     * 搜索值
     */
    @TableField(value = "`key`")
    private String key;

    /**
     * 字典类型
     */
    @TableField(value = "`type`")
    private String type;

    /**
     * 选择值
     */
    @TableField(value = "`value`")
    private String value;

}

