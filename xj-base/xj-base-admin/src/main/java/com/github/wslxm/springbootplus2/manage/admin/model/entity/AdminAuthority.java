package com.github.wslxm.springbootplus2.manage.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.wslxm.springbootplus2.core.base.model.BaseEntity;
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
     * 不使用逻辑删除 --> 逻辑删除字段 int 默认值为0，(0、正常，1、删除) (mybatis-plus 策略, 添加了 @TableLogic 注解自动为逻辑删除)
     */
    private Integer deleted;
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
    @TableField(value = "`disable`")
    private Integer disable;

    /**
     * 终端(字典code, 如 0-管理端 1-用户端等)
     */
    private Integer type;

    /**
     * 授权状态(字典code  0-无需登录 1-需登录 2-需登录+授权)
     */
    private Integer state;

    /**
     * 是否需要验签
     */
    private  Boolean isSign;


}

