package com.ws.ldy.modules.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.modules.admin.model.entity.AdminRole;
import com.ws.ldy.modules.admin.model.vo.AdminRoleVO;

import java.util.List;

/**
 *   角色
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 15:10
 */
public interface AdminRoleService extends IService<AdminRole> {

    /**
     *  查询用户角色| 用户当前拥有角色赋予 isChecked=true
     *
     * @param userId
     * @return void
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/4/10 0010 0:45
     */
    List<AdminRoleVO> findByUserIdRoleChecked(String userId);


    /**
     * 分配用户角色
     *
     * @param userId
     * @param roleIds
     * @return void
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/4/10 0010 2:25
     */
    boolean updUserRole(String userId, String[] roleIds);

    /**
     *  分配角色菜单权限
     *
     * @param roleId  角色id
     * @param menuIds 菜单id集
     * @return void
     * @date 2019/11/17 0017 2:08
     */
    boolean roleMenuAuth(String roleId, String[] menuIds);


    /***
     *  分配角色url权限
     * @param roleId
     * @param authIds
     * @date 2019/11/18 15:45
     * @return void
     */
    boolean roleUrlAuth(String roleId, String[] authIds);

}