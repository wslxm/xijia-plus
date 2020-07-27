package com.ws.ldy.modules.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.others.base.model.BaseEntity;
import lombok.Data;

/**
 * TODO  角色-菜单关联表
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
    private int menuId;

    /**
     * 角色id
     */
    private int roleId;

    public RoleMenuAdmin() {
    }

    public RoleMenuAdmin(int roleId, int menuId) {
        this.menuId = menuId;
        this.roleId = roleId;
    }
}
