package com.ws.ldy.admin.service;


import com.ws.ldy.admin.entity.RoleAdmin;
import com.ws.ldy.admin.vo.RoleAdminVo;
import com.ws.ldy.base.service.BaseService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * TODO  角色
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 15:10
 */
public interface RoleAdminService extends BaseService<RoleAdmin, Integer> {

    /**
     * TODO  查询所有角色--用户当前拥有角色赋予checked=true
     *
     * @param userId
     * @return void
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/4/10 0010 0:45
     */
    List<RoleAdminVo> findRoleChecked(String userId);


    /**
     * TODO  修改用户角色
     *
     * @param userId
     * @param roleIds
     * @return void
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/4/10 0010 2:25
     */
    boolean updUserRole(@RequestParam Integer userId, @RequestParam Integer[] roleIds);

}