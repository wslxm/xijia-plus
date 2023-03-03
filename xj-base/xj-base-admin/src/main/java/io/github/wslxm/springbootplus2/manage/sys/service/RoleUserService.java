package io.github.wslxm.springbootplus2.manage.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.RoleUser;

import java.util.List;

/**
 *   角色+用户管理
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 15:10
 */
public interface RoleUserService extends IService<RoleUser> {

    /**
     * 用户角色重分配
     *
     * @param userId
     * @param roleIds
     * @return boolean
     * @author wangsong
     * @date 2022/8/18 14:31
     */
    boolean updUserRole(String userId, List<String> roleIds);


    /**
     * 删除指定用户 关联的角色
     *
     * @param userId
     * @return boolean
     * @author wangsong
     * @date 2022/8/18 14:31
     */
    boolean delByUserId(String userId);


    /**
     * 删除指定角色 关联的用户
     *
     * @param roleId
     * @return boolean
     * @author wangsong
     * @date 2022/8/18 14:31
     */
    boolean delByRoleId(String roleId);
}