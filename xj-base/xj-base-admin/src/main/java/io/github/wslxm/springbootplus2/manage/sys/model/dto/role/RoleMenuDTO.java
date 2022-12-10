package io.github.wslxm.springbootplus2.manage.sys.model.dto.role;


import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 用户菜单分配
 *
 * @author wangsong
 * @version 1.0.1
 * @mail 1720696548@qq.com
 * @date 2020/9/23 0023 11:51
 */
@Data
@ToString(callSuper = true)
public class RoleMenuDTO {

    /**
     * 角色Id
     */
    private String roleId;

    /**
     * 菜单Id
     */
    private List<String> menuIds;
}
