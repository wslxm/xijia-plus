package com.ws.ldy.baseadmin.controller;

import com.ws.ldy.admincore.common.vo.ResponseData;
import com.ws.ldy.admincore.controller.BaseController;
import com.ws.ldy.baseadmin.entity.RoleAdmin;
import com.ws.ldy.baseadmin.service.impl.RoleAdminServiceImpl;
import com.ws.ldy.baseadmin.service.impl.RoleAuthAdminServiceImpl;
import com.ws.ldy.baseadmin.service.impl.RoleMenuAdminServiceImpl;
import com.ws.ldy.baseadmin.service.impl.RoleUserAdminServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 13:38
 */
@RestController
@RequestMapping("/roleAdmin")
@Api(tags = {"Admin-Role"}, description = "角色管理")
public class RoleAdminController extends BaseController {

    @Autowired
    private RoleAdminServiceImpl roleAdminServiceImpl;
    @Autowired
    private RoleMenuAdminServiceImpl roleMenuAdminServiceImpl;
    @Autowired
    private RoleAuthAdminServiceImpl roleAuthAdminServiceImpl;
    @Autowired
    private RoleUserAdminServiceImpl roleUserAdminServiceImpl;


    @GetMapping("/findAll/{type}")
    @ApiOperation("分页查询/查询所有")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "type", value = "1 分页查询  =2 所有查询", required = true, paramType = "query"),
    })
    public ResponseData findAll(@PathVariable Integer type, Integer page, Integer limit) {
        if (type == 1) {
            Map<String, Object> param = new HashMap<>(2);
            param.put("id", getString("id", ""));
            Sort sort = new Sort(Sort.Direction.ASC, "id");
            Page<RoleAdmin> roles = roleAdminServiceImpl.page(page, limit, param, sort);
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
     */
    @PostMapping("/save/{type}")
    @ApiOperation("添加/修改")
    public ResponseData save(@PathVariable Integer type, RoleAdmin role) {
        if (type == 1) {
            roleAdminServiceImpl.save(role);
        } else {
            roleAdminServiceImpl.save(role);
        }
        return ResponseData.success("success");
    }


    @PostMapping("/delete")
    @ApiOperation("批量删除/单删除")
    public ResponseData delete(Integer[] ids) {
        roleAdminServiceImpl.deleteByIds(ids);
        return ResponseData.success("success");
    }


    @PostMapping("/updRoleMenu")
    @ApiOperation("角色菜单权限分配")
    public ResponseData updRoleMenu(Integer roleId, Integer[] menuIds, Integer pid) {
        roleMenuAdminServiceImpl.roleMenuAuth(roleId, menuIds, pid);
        return ResponseData.success("success");
    }



    @PostMapping("/updRoleUrlAuth")
    @ApiOperation("角色URL权限分配")
    public ResponseData updRoleUrlAuth(Integer roleId, Integer[] authIds) {
        roleAuthAdminServiceImpl.roleUrlAuth(roleId, authIds);
        return ResponseData.success("success");
    }



    @PostMapping("/updRoleUser")
    @ApiOperation("角色用户分配")
    public ResponseData updRoleUser(Integer roleId, Integer[] userIds) {
        roleUserAdminServiceImpl.updRoleUser(roleId, userIds);
        return ResponseData.success("success");
    }
}
