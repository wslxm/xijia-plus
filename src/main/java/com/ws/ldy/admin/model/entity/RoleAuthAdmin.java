package com.ws.ldy.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.base.model.entity.BaseAdminEntity;
import lombok.Data;

/**
 * TODO  角色菜单权限
 * @author  wangsong
 * @WX-QQ  1720696548
 * @date  Mon Nov 25 11:38:29 CST 2019
 */
@Data
@TableName(value = "t_admin_role_auth")
public class RoleAuthAdmin extends BaseAdminEntity {

    private static final long serialVersionUID = 0L;

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

