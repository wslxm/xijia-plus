package com.ws.ldy.modules.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.others.base.model.BaseEntity;
import lombok.Data;
import lombok.ToString;

/**
 *   角色-菜单关联表
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/14 20:49
 */
@Data
@ToString(callSuper = true)
@TableName(value = "t_admin_role_menu")
public class AdminRoleMenu extends BaseEntity {

    private static final long serialVersionUID = 7936919715202241575L;

    /**
     * 菜单id
     */
    private String menuId;

    /**
     * 角色id
     */
    private String roleId;

    public AdminRoleMenu() {
    }

    public AdminRoleMenu(String roleId, String menuId) {
        this.menuId = menuId;
        this.roleId = roleId;
    }
}
