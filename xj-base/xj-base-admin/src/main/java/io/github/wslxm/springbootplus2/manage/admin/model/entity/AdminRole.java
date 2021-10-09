package io.github.wslxm.springbootplus2.manage.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.wslxm.springbootplus2.core.base.model.BaseEntity;
import lombok.Data;
import lombok.ToString;

/**
 *   角色
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/14 20:48
 */
@Data
@ToString(callSuper = true)
@TableName(value = "t_admin_role")
public class AdminRole extends BaseEntity {

    private static final long serialVersionUID = 5684918982089765949L;

    /**
     * 角色名称
     */
    private String name;
    /**
     * 描叙
     */
    @TableField(value = "`desc`")
    private String desc;

    /**
     * 禁用 0-否，1-是
     */
    @TableField(value = "`disable`")
    private Integer disable;

    /**
     * 查询code
     */
    private String code;

    /**
     * 终端 (字段code)
     */
    private Integer terminal;
}

