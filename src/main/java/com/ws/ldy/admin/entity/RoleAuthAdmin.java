package com.ws.ldy.admin.entity;

import com.ws.ldy.base.entity.BaseAdminEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * TODO  角色菜单权限
 * @author  wangsong
 * @WX-QQ  1720696548
 * @date  Mon Nov 25 11:38:29 CST 2019
 */
@Data
@Entity
@Table(name = "t_admin_role_auth")
@DynamicUpdate(value = true)
public class RoleAuthAdmin extends BaseAdminEntity {

    private static final long serialVersionUID = 0L;
    /**
     * 数据库自增id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
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

