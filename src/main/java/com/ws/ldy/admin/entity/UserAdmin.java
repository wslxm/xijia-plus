package com.ws.ldy.admin.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.ws.ldy.base.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * TODO  用户
 * @author 王松
 * @WX-QQ 1720696548
 * @date  2019/11/14 21:06
 */
@Data
@Entity
@Table(name = "t_admin_user")
@DynamicUpdate(value = true)
public class UserAdmin extends BaseEntity {

    private static final long serialVersionUID = 4934650100711613453L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    /** 头像 */
    private String head;

    /** 用户名  */
    private String username;

    /** 账号  */
    private String account;

    /** 密码  */
    private String password;

    /** 年龄  */
    private int age;

    /** 性别  */
    private String gender;

    /** 地址 */
    private String address;

    /** 部门 */
    private int empId;

    /** 注册时间 */
    private Date time;

    /** 是否选中（角色是否有改用户，前台复选框默认选中需要值）  */
    @JsonProperty  //防止大小写自动转换
    @Transient
    Boolean  LAY_CHECKED;
}
