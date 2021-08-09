package com.ws.ldy.manage.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.core.base.model.BaseEntity;
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
@TableName(value = "t_admin_role_user")
public class AdminRoleUser extends BaseEntity {


    private static final long serialVersionUID = 5610627763663770761L;
    /**
     * 不使用逻辑删除 --> 逻辑删除字段 int 默认值为0，(0、正常，1、删除) (mybatis-plus 策略, 添加了 @TableLogic 注解自动为逻辑删除)
     */
    private Integer deleted;
    /**
     * 用户id
     */
    private String userId;

    /**
     * 角色id
     */
    private String roleId;


    public AdminRoleUser() {
    }

    public AdminRoleUser(String roleId, String userId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}
