package com.ws.ldy.modules.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.others.base.model.BaseEntity;
import lombok.Data;
import lombok.ToString;

/**
 *   权限列表
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Mon Nov 25 08:02:49 CST 2019
 */
@Data
@ToString(callSuper = true)
@TableName(value = "t_admin_authority")
public class AdminAuthority extends BaseEntity {

    private static final long serialVersionUID = 0L;
    /**
     * 权限类Id（方法与类/层级关系展示)
     */
    private String pid;
    /**
     * 权限描叙
     */
    @TableField(value = "`desc`")
    private String desc;

    /**
     * 权限url
     */
    private String url;
    /**
     * 请求方式（请求方式(GET/POST/PUT/DELETE）
     */
    private String method;

    /**
     * 禁用 0-否，1-是
     */
    private Integer disable;
}

