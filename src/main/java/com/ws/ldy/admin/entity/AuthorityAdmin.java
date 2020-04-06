package com.ws.ldy.admin.entity;

import com.ws.ldy.base.entity.BaseAdminEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * TODO  权限列表
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Mon Nov 25 08:02:49 CST 2019
 */
@Data
@Entity
@Table(name = "t_admin_authority")
@DynamicUpdate(value = true)
public class AuthorityAdmin extends BaseAdminEntity {

    private static final long serialVersionUID = 0L;
    /**
     * 数据库自增id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * 权限描叙
     */
    @Column(name = "`desc`")
    private String desc;
    /**
     * 权限名
     */
    private String name;
    /**
     * 权限类
     */
    private Integer pid;
    /**
     * 权限url
     */
    private String url;
    /**
     * 请求方式
     */
    private String type;
}

