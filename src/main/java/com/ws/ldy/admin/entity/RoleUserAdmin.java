package com.ws.ldy.admin.entity;

import com.ws.ldy.base.entity.BaseAdminEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * TODO  角色-菜单关联表
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/14 20:49
 */
@Data
@Entity
@Table(name = "t_admin_role_user")
@DynamicUpdate(value = true)
public class RoleUserAdmin extends BaseAdminEntity {


    private static final long serialVersionUID = 5610627763663770761L;
    /**
     * 数据库自增id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * 菜单id
     */
    private int userId;

    /**
     * 角色id
     */
    private int roleId;

    public RoleUserAdmin() {}
    public RoleUserAdmin(int roleId, int userId ) {
        this.userId = userId;
        this.roleId = roleId;
    }
}
