package io.github.wslxm.springbootplus2.manage.admin.model.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.wslxm.springbootplus2.core.base.model.BaseEntity;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 *   用户
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/14 21:06
 */
@Data
@ToString(callSuper = true)
@TableName(value = "t_admin_user")
public class AdminUser extends BaseEntity {

    private static final long serialVersionUID = 4934650100711613453L;


    /**
     * 姓名/用户名
     */
    private String fullName;

    /**
     * 账号/用户名
     */
    private String username;
    /**
     * 手机号/第二账号
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String head;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 地址
     */
    private String address;

    /**
     * 注册时间
     */
    private LocalDateTime regTime;

    /**
     * 最后登录时间
     */
    private LocalDateTime endTime;
    /**
     * 禁用 0-否，1-是
     */
    @TableField(value = "`disable`")
    private Integer disable;
    /**
     * 公司/部门
     */
    private String organId;

    /**
     * 职位 职位(字典code)
     */
    private Integer position;
    /**
     * 微信openId
     */
    private String wxOpenId;
    /**
     * 终端 (字段code)
     */
    private Integer terminal;
    /**
     * 备注
     */
    private Integer remarks;
}
