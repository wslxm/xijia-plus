package com.ws.lay.adminconsole.entity;


import com.ws.ldy.admincore.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Data
@Entity
@Table(name = "t_admin_user")
public class User extends BaseEntity {


    private static final long serialVersionUID = 4934650100711613453L;
    @Id
    private int id;
    /** 用户名  */
    private String username;

    /** 密码  */
    private String password;

    /** 年龄  */
    private int age;

    /** 性别  */
    private String gender;

    /** 地址 */
    private String address;

    /** 头像 */
    private String head;

    /** 注册时间 */
    private Date time;

    /** 最后登录时间 */
    private Date lastTime;
}
