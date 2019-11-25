package com.ws.ldy.adminconsole.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ws.ldy.admincore.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
/**
 * TODO  代码生成器自动生成，请自定义描叙
 * @author  wangsong
 * @WX-QQ  1720696548
 * @date  Mon Nov 25 08:02:49 CST 2019
 */
@Data
@Entity
@Table(name = "t_admin_authority")
@DynamicUpdate(value = true)
public class AuthorityAdmin extends BaseEntity {

    private static final long serialVersionUID = 0L;
    
    /** 权限描叙 */
    @Column(name = "`desc`")
    private String desc;
    /** id */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    /** 权限名 */
    private String name;
    /** 权限类 */
    private Integer pid;
    /** 权限url */
    private String url;

    /** 是否选中（是否有权限，前台复选框默认选中需要值）  */
    @JsonProperty  //防止大小写自动转换
    @Transient
    Boolean  LAY_CHECKED;

}

