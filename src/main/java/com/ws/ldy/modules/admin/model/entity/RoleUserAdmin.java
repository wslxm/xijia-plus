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
@TableName(value = "t_admin_role_user")
public class RoleUserAdmin extends BaseEntity {


    private static final long serialVersionUID = 5610627763663770761L;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 角色id
     */
    private String roleId;


    public RoleUserAdmin() {
    }

    public RoleUserAdmin(String roleId, String userId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}
