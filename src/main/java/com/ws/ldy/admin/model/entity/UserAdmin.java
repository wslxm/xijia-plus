package com.ws.ldy.admin.model.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.admin.baseModel.BaseAdminEntity;
import com.ws.ldy.base.enums.GenderEnum;
import lombok.Data;

import java.util.Date;

/**
 * TODO  用户
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/14 21:06
 */
@Data
@TableName(value = "t_admin_user")
public class UserAdmin extends BaseAdminEntity {

    private static final long serialVersionUID = 4934650100711613453L;

    /**
     * 头像
     */
    private String head;

    /**
     * 用户名
     */
    private String username;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private GenderEnum gender;

    /**
     * 地址
     */
    private String address;

    /**
     * 部门
     */
    private Integer empId;

    /**
     * 注册时间
     */
    private Date time;
}
