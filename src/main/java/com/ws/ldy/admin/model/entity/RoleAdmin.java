package com.ws.ldy.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.admin.baseModel.BaseAdminEntity;
import lombok.Data;

/**
 * TODO  角色
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/14 20:48
 */
@Data
@TableName(value = "t_admin_role")
public class RoleAdmin extends BaseAdminEntity {

    private static final long serialVersionUID = 5684918982089765949L;

    /**
     * 角色名称
     */
    private String name;
    /**
     * 描叙
     */
    @TableField(value = "`desc`")
    private String desc;
}

