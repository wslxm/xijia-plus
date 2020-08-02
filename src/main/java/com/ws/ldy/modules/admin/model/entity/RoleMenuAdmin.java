package com.ws.ldy.modules.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.others.base.model.BaseEntity;
import lombok.Data;

/**
 *   角色-菜单关联表
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/14 20:49
 */
@Data
@TableName(value = "t_admin_role_menu")
public class RoleMenuAdmin extends BaseEntity {

    private static final long serialVersionUID = 7936919715202241575L;

    /**
     * 菜单id
     */
    private String menuId;

    /**
     * 角色id
     */
    private String roleId;

    public RoleMenuAdmin() {
    }

    public RoleMenuAdmin(String roleId, String menuId) {
        this.menuId = menuId;
        this.roleId = roleId;
    }
}
