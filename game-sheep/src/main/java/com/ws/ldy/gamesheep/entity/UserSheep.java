package com.ws.ldy.gamesheep.entity;


import com.ws.ldy.admincore.entity.BaseEntity;
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
@Table(name = "t_sheep_user")
@DynamicUpdate(value = true)
public class UserSheep extends BaseEntity {


    private static final long serialVersionUID = -6177347264327900669L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    /** 头像 */
    private String head;

    /** 用户名  */
    private String name;

    /** 用户名  */
    private int lv;

    /** 用户名  */
    private int gold;

    /** 注册时间 */
    private Date time;
}
