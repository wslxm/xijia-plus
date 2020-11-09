package com.ws.ldy.modules.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.admin.model.dto.AdminRoleDTO;
import com.ws.ldy.modules.admin.model.dto.role.RoleAuthDTO;
import com.ws.ldy.modules.admin.model.dto.role.RoleMenuDTO;
import com.ws.ldy.modules.admin.model.dto.role.UserRoleDTO;
import com.ws.ldy.modules.admin.model.entity.AdminRole;
import com.ws.ldy.modules.admin.model.vo.AdminRoleVO;
import com.ws.ldy.modules.admin.service.AdminRoleService;
import com.ws.ldy.others.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 13:38
 */
@RestController
@RequestMapping(BaseConstant.Sys.URI_PREFIX + "/admin/adminRole")
@Api(value = "AdminRoleController", tags = "角色管理", consumes = BaseConstant.InterfaceType.PC_ADMIN)
public class AdminRoleController extends BaseController<AdminRoleService> {


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页数", required = true, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "size", value = "记录数", required = true, paramType = "query", example = "20"),
            @ApiImplicitParam(name = "name", value = "角色名称", required = false, paramType = "query"),
    })
    public R<IPage<AdminRoleVO>> findPage(String name) {
        Page<AdminRole> page = baseService.page(this.getPage(), new LambdaQueryWrapper<AdminRole>()
                .orderByAsc(AdminRole::getId)
                .like(StringUtils.isNotBlank(name), AdminRole::getName, name)
        );
        return R.successFind(BeanDtoVoUtil.pageVo(page, AdminRoleVO.class));
    }


    @RequestMapping(value = "/findList", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有", notes = "")
    public R<List<AdminRoleVO>> findList() {
        List<AdminRole> roles = baseService.list();
        return R.successFind(BeanDtoVoUtil.listVo(roles, AdminRoleVO.class));
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "添加", notes = "")
    public R<Boolean> insert(@RequestBody AdminRoleDTO dto) {
        if (StringUtils.isNotBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_FALSE);
        }
        return R.successInsert(baseService.insert(dto));
    }


    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    @ApiOperation(value = "ID编辑", notes = "")
    public R<Boolean> upd(@RequestBody AdminRoleDTO dto) {
        if (StringUtils.isBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_TRUE);
        }
        if (baseService.updateById(dto.convert(AdminRole.class))) {
            // 刷新登录中的用户角色 -> 角色权限
            BaseConstant.Cache.AUTH_VERSION++;
            return R.successUpdate(true);
        } else {
            return R.successUpdate(false);
        }
    }


    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    @ApiOperation(value = "ID删除", notes = "")
    public R<Boolean> del(@RequestParam String id) {
        return R.successDelete(baseService.removeById(id));
    }


    //=========================================================================
    //============================ 增删改查外 ===================================
    //=========================================================================
    //=========================================================================

    @RequestMapping(value = "/findUserRole", method = RequestMethod.GET)//Checked
    @ApiOperation(value = "获取用户的当前角色", notes = "用户角色分配查询到所有角色, 并使用户拥有的角色赋予 isChecked=true")
    public R<List<AdminRoleVO>> findRoleChecked(@RequestParam String userId) {
        List<AdminRoleVO> roles = baseService.findByUserIdRoleChecked(userId);
        return R.successFind(roles);
    }


//    @RequestMapping(value = "/updUserRole", method = RequestMethod.PUT)
//    @ApiOperation(value = "用户的角色分配", notes = "")
//    public R<Boolean> updUserRole(@RequestParam String userId, String[] roleIds) {
//        if (baseService.updUserRole(userId, roleIds)) {
//            // 刷新登录中的用户角色 -> 角色权限
//            BaseConstant.Cache.AUTH_VERSION++;
//            return R.successUpdate(true);
//        } else {
//            return R.successUpdate(false);
//        }
//    }
//
//    @RequestMapping(value = "/updRoleAuth", method = RequestMethod.PUT)
//    @ApiOperation(value = "角色的URL权限分配", notes = "")
//    public R<Boolean> updRoleAuth(@RequestParam String roleId, String[] authIds) {
//        if (baseService.roleUrlAuth(roleId, authIds)) {
//            // 刷新登录中的用户权限
//            BaseConstant.Cache.AUTH_VERSION++;
//            return R.successUpdate(true);
//        } else {
//            return R.successUpdate(false);
//        }
//    }
//
//    @RequestMapping(value = "/updRoleMenu", method = RequestMethod.PUT)
//    @ApiOperation(value = "角色的菜单分配", notes = "")
//    public R<Boolean> updRoleMenu(@RequestParam String roleId, String[] menuIds) {
//        // 菜单每次都是重新请求接口获取的,不用做任何配置
//        return R.successUpdate(baseService.roleMenuAuth(roleId, menuIds));
//    }


    @RequestMapping(value = "/updUserRole", method = RequestMethod.PUT)
    @ApiOperation(value = "用户的角色分配", notes = "")
    public R<Boolean> updUserRole(@RequestBody UserRoleDTO dto) {
        if (baseService.updUserRole(dto)) {
            // 刷新登录中的用户角色 -> 角色权限
            BaseConstant.Cache.AUTH_VERSION++;
            return R.successUpdate(true);
        } else {
            return R.successUpdate(false);
        }
    }

    @RequestMapping(value = "/updRoleAuth", method = RequestMethod.PUT)
    @ApiOperation(value = "角色的URL权限分配", notes = "")
    public R<Boolean> updRoleAuth(@RequestBody RoleAuthDTO dto) {
        if (baseService.roleUrlAuth(dto)) {
            // 刷新登录中的用户权限
            BaseConstant.Cache.AUTH_VERSION++;
            return R.successUpdate(true);
        } else {
            return R.successUpdate(false);
        }
    }

    @RequestMapping(value = "/updRoleMenu", method = RequestMethod.PUT)
    @ApiOperation(value = "角色的菜单分配", notes = "")
    public R<Boolean> updRoleMenu(@RequestBody RoleMenuDTO dto) {
        // 菜单每次都是重新请求接口获取的,不用做任何配置
        return R.successUpdate(baseService.roleMenuAuth(dto));
    }

    @RequestMapping(value = "/updRoleAuthAll", method = RequestMethod.PUT)
    @ApiOperation(value = "所有角色拥有所有权限", notes = "")
    public R<Boolean> updRoleAuthAll() {
        return R.successUpdate(baseService.roleAuthAll());
    }

}
