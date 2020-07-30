package com.ws.ldy.modules.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.others.base.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO  角色菜单权限
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Mon Nov 25 11:38:29 CST 2019
 */
@Data
@TableName(value = "t_admin_role_auth")
@AllArgsConstructor
@NoArgsConstructor
public class RoleAuthAdmin extends BaseEntity {

    private static final long serialVersionUID = 0L;
    /**
     * url权限id
     */
    private String authId;
    /**
     * 角色id
     */
    private String roleId;
}

