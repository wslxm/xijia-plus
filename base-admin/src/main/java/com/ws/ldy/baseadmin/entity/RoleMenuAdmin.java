package com.ws.ldy.baseadmin.entity;

import com.ws.ldy.admincore.entity.BaseEntity;
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
@Table(name = "t_admin_role_menu")
@DynamicUpdate(value = true)
public class RoleMenuAdmin extends BaseEntity {

    private static final long serialVersionUID = 7936919715202241575L;
    /**
     * 菜单id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * 菜单id
     */
    private int menuId;

    /**
     * 角色id
     */
    private int roleId;

    public RoleMenuAdmin() {}
    public RoleMenuAdmin(int roleId, int menuId ) {
        this.menuId = menuId;
        this.roleId = roleId;
    }
}
