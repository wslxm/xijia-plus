package com.ws.ldy.admin.entity;

import com.ws.ldy.base.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
/**
 * TODO  代码生成器自动生成，请自定义描叙
 * @author  wangsong
 * @WX-QQ  1720696548
 * @date  Mon Nov 25 11:38:29 CST 2019
 */
@Data
@Entity
@Table(name = "t_admin_role_auth")
@DynamicUpdate(value = true)
public class RoleAuthAdmin extends BaseEntity {

    private static final long serialVersionUID = 0L;
    
    /**  */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    /** url权限id */
    private Integer authId;
    /** 角色id */
    private Integer roleId;

    /**
     * TODO
     * @param roleId 角色id
     * @param authId 权限id
     * @date  2019/11/25 0025 18:29
     * @return
     */
    public RoleAuthAdmin(Integer roleId,Integer authId ) {
        this.authId = authId;
        this.roleId = roleId;
    }
    public RoleAuthAdmin(){}
}

