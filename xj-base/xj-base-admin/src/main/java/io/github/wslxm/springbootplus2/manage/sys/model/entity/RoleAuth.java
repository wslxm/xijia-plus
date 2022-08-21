package io.github.wslxm.springbootplus2.manage.sys.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.github.wslxm.springbootplus2.core.base.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *   角色菜单权限
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Mon Nov 25 11:38:29 CST 2019
 */
@Data
@ToString(callSuper = true)
@TableName(value = "t_sys_role_auth")
@AllArgsConstructor
@NoArgsConstructor
public class RoleAuth extends BaseEntity {

    private static final long serialVersionUID = 0L;
    /**
     * 不使用逻辑删除 --> 逻辑删除字段 int 默认值为0，(0、正常，1、删除) (mybatis-plus 策略, 添加了 @TableLogic 注解自动为逻辑删除)
     */
    private Integer deleted;
    /**
     * url权限id
     */
    private String authId;
    /**
     * 角色id
     */
    private String roleId;

    public RoleAuth(String authId, String roleId) {
        this.authId = authId;
        this.roleId = roleId;
    }
}

