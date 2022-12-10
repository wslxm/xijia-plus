package io.github.wslxm.springbootplus2.manage.sys.model.dto.role;


import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 用户权限分配
 *
 * @author wangsong
 * @version 1.0.1
 * @date 2020/9/23 0023 11:47
 * @return
 */
@Data
@ToString(callSuper = true)
public class RoleAuthDTO {


    /**
     * 用户Id
     */
    private String roleId;

    /**
     * 角色Ids
     */
    private List<String> authIds;


}
