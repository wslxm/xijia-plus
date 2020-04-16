package com.ws.ldy.admin.model.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.base.model.entity.BaseAdminEntity;
import lombok.Data;

import java.util.Date;

/**
 * TODO  用户
 * @author 王松
 * @WX-QQ 1720696548
 * @date  2019/11/14 21:06
 */
@Data
@TableName(value = "t_admin_user")
public class UserAdmin extends BaseAdminEntity {

    private static final long serialVersionUID = 4934650100711613453L;
    /**
     * 数据库自增id
     */
    @TableId(type = IdType.AUTO) //自增
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

//    /** 是否选中（角色是否有改用户，前台复选框默认选中需要值）  */
//    @JsonProperty  //防止大小写自动转换
//    @Transient
//    Boolean  LAY_CHECKED;
}
