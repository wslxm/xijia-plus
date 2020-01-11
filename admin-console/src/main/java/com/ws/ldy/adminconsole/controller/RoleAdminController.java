package com.ws.ldy.adminconsole.controller;

import com.ws.ldy.adminconsole.entity.RoleAdmin;
import com.ws.ldy.adminconsole.service.impl.RoleAdminServiceImpl;
import com.ws.ldy.adminconsole.service.impl.RoleAuthAdminServiceImpl;
import com.ws.ldy.adminconsole.service.impl.RoleMenuAdminServiceImpl;
import com.ws.ldy.adminconsole.service.impl.RoleUserAdminServiceImpl;
import com.ws.ldy.admincore.controller.BaseController;
import com.ws.ldy.admincore.controller.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO  角色管理
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 13:38
 */
@Controller
@RequestMapping("/roleAdmin")
public class RoleAdminController extends BaseController {


    @Autowired
    private RoleAdminServiceImpl roleAdminServiceImpl;



    @Autowired
    private RoleMenuAdminServiceImpl roleMenuAdminServiceImpl;
    @Autowired
    private RoleAuthAdminServiceImpl roleAuthAdminServiceImpl;
    @Autowired
    private RoleUserAdminServiceImpl roleUserAdminServiceImpl;
    /***
     * TODO  分页查询
     * @param  type     =1 分页查询  =2 所有查询
     * @param  page    页数
     * @param  limit   记录数
     * @param  id-xxx  查询条件参数
     * @date 2019/11/14 15:20
     * @return Map<String       ,               Object>
     */
    @RequestMapping("/findAll/{type}")
    @ResponseBody
    public ResponseData findAll(@PathVariable Integer type, Integer page, Integer limit, Integer id) {
        if (type == 1) {
            Map<String, Object> param = new HashMap<>(2);
            param.put("id", id);
            Sort sort = new Sort(Sort.Direction.ASC, "id");
            Page<RoleAdmin> roles = roleAdminServiceImpl.page( page, limit, param, sort);
            return ResponseData.success(roles.getContent(), roles.getTotalPages());
        } else {
            List<RoleAdmin> roles = roleAdminServiceImpl.findAll();
            return ResponseData.success(roles);
        }
    }


    /***
     * TODO  添加/修改
     * @param type t=1 添加，=2修改
     * @param role 对象数据
     * @date 2019/11/14 17:34
     * @return java.lang.String
     */
    @RequestMapping("/save/{type}")
    @ResponseBody
    public String save(@PathVariable Integer type, RoleAdmin role) {
        if (type == 1) {
            roleAdminServiceImpl.save( role);
        } else {
            roleAdminServiceImpl.save( role);
        }
        return "success";
    }


    /**
     * TODO  批量删除/单删除
     *
     * @param ids 要删除的数据Id数组
     * @author 王松
     * @WX-QQ 1720696548
     * @date 2019/11/14 18:17
     */
    @ResponseBody
    @RequestMapping("/delete")
    public String delete(Integer[] ids) {
        roleAdminServiceImpl.deleteByIds( ids);
        return "success";
    }

    /**
     * TODO  角色菜单权限分配
     *
     * @return java.lang.String
     * @date 2019/11/16 0016 22:46
     */
    @ResponseBody
    @RequestMapping("/updRoleMenu")
    public String updRoleMenu(Integer roleId, Integer[] menuIds,Integer pid) {
        roleMenuAdminServiceImpl.roleMenuAuth(roleId,menuIds,pid);
        return "success";
    }


    /**
     * TODO  角色URL权限分配
     *
     * @return java.lang.String
     * @date 2019/11/16 0016 22:46
     */
    @ResponseBody
    @RequestMapping("/updRoleUrlAuth")
    public String updRoleUrlAuth(Integer roleId, Integer[] authIds) {
        roleAuthAdminServiceImpl.roleUrlAuth(roleId,authIds);
        return "success";
    }


    /**
     * TODO  角色用户分配
     *
     * @return java.lang.String
     * @date 2019/11/16 0016 22:46
     */
    @ResponseBody
    @RequestMapping("/updRoleUser")
    public String updRoleUser(Integer roleId, Integer[] userIds) {
        roleUserAdminServiceImpl.updRoleUser(roleId,userIds);
        return "success";
    }
}
