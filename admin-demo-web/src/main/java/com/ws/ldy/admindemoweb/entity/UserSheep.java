package com.ws.ldy.admindemoweb.entity;

import com.ws.ldy.admincore.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
/**
 * TODO  代码生成器自动生成，请自定义描叙
 * @author  wangsong
 * @WX-QQ  1720696548
 * @date  Fri Nov 22 21:18:10 CST 2019
 */
@Data
@Entity
@Table(name = "t_sheep_user")
@DynamicUpdate(value = true)
public class UserSheep extends BaseEntity {

    private static final long serialVersionUID = 0L;
    
    /** 金币 */
    private Long gold;
    /** 头像 */
    private String head;
    /** id */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    /** 等级 */
    private Integer lv;
    /** 名称 */
    private String name;
    /** 时间 */
    private Date time;

}

