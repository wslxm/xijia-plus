package com.ws.ldy.modules.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.config.auth.util.JwtUtil;
import com.ws.ldy.config.auth.util.MD5Util;
import com.ws.ldy.enums.base.BaseConstant;
import com.ws.ldy.modules.admin.model.dto.UserAdminDto;
import com.ws.ldy.modules.admin.model.entity.UserAdmin;
import com.ws.ldy.modules.admin.model.vo.UserAdminVo;
import com.ws.ldy.modules.admin.service.RoleUserAdminService;
import com.ws.ldy.modules.admin.service.UserAdminService;
import com.ws.ldy.others.base.controller.BaseController;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 *   系统用户
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 13:38
 */
@RestController
@RequestMapping("/userAdmin")
@Api(value = "UserAdminController", tags = "用户管理", description = BaseConstant.InterfaceType.PC_ADMIN)
public class UserAdminController extends BaseController<UserAdminService> {

    @Autowired
    private RoleUserAdminService roleUserAdminService;


//    @RequestMapping(value = "/findUser", method = RequestMethod.GET)
//    @ApiOperation("当前登录用户信息")
//    public Result<UserAdminVo> findUser() {
//        return Result.successFind(AdminUserUtils.getUserAdmin().convert(UserAdminVo.class));
//    }


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query", example = "20"),
    })
    public R<IPage<UserAdminVo>> findPage(
            @ApiParam(value = "数据Id", required = false) @RequestParam(required = false) Integer id,
            @ApiParam(value = "账号/手机号", required = false) @RequestParam(required = false) String username,
            @ApiParam(value = "姓名/用户名", required = false) @RequestParam(required = false) String fullName) {
        Page<UserAdmin> page = baseService.page(this.getPage(), new LambdaQueryWrapper<UserAdmin>()
                .orderByDesc(UserAdmin::getCreateTime)
                .eq(id != null, UserAdmin::getId, id)
                .eq(StringUtils.isNotBlank(fullName), UserAdmin::getFullName, fullName)
                .like(StringUtils.isNotBlank(username), UserAdmin::getUsername, username)
        );
        return R.success(BeanDtoVoUtil.pageVo(page, UserAdminVo.class));
    }


    @RequestMapping(value = "/findRoleUser", method = RequestMethod.GET)
    @ApiOperation(value = "获取指定角色的当前用户", notes = "查询所有用户,拥有当前角色权限的给予：isChecked=true")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色Id", required = false, paramType = "query"),
    })
    public R<List<UserAdminVo>> findRoleUser(@RequestParam String roleId) {
        // TODO  此方法存在一定问题，当用户多多，性能损耗将随之增加，不应该查询所有用户信息出来进行判断操作，只查询角色当前用户信息，建议使用sql直接查询
        List<UserAdmin> userList = baseService.list();
        // 角色选中状态处理
        List<UserAdminVo> userAdminVos = roleUserAdminService.roleUserChecked(userList, roleId);
        return R.success(userAdminVos);
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "添加", notes = "")
    public R<Void> insert(@RequestBody UserAdminDto userAdminDto) {
        UserAdmin userAdmin = userAdminDto.convert(UserAdmin.class);
        userAdmin.setPassword(MD5Util.encode(userAdmin.getPassword()));
        userAdmin.setState(0);//默认启用状态
        userAdmin.setRegTime(LocalDateTime.now());
        baseService.save(userAdmin);
        return R.success();
    }


    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    @ApiOperation("ID编辑")
    public R<Void> upd(@RequestBody UserAdminDto userAdminDto) {
        baseService.updateById(userAdminDto.convert(UserAdmin.class));
        return R.successUpdate();
    }


    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    @ApiOperation(value = "ID删除", notes = "")
    public R<Void> del(@RequestParam String id) {
        baseService.removeById(id);
        return R.successDelete();
    }


    @RequestMapping(value = "/delByIds", method = RequestMethod.DELETE)
    @ApiOperation(value = "批量ID删除", notes = "")
    public R<Void> delByIds(@RequestParam String[] ids) {
        baseService.removeByIds(Arrays.asList(ids));
        return R.successDelete();
    }


    @RequestMapping(value = "/updByPassword", method = RequestMethod.PUT)
    @ApiOperation(value = "密码修改", notes = "判断原密码是否正确,不正确返回错误信息msg ,正确直接修改,密码进行MD5加密 --> val(前端输入密码值)+盐(后端规则指定)=最终密码）")
    public R<Void> updByPassword(@RequestParam String oldPassword, @RequestParam String password) {
        UserAdmin userAdmin = baseService.getById(JwtUtil.getUserId(request.getHeader(BaseConstant.Sys.TOKEN)));
        if (userAdmin.getPassword().equals(MD5Util.encode(oldPassword))) {
            userAdmin.setPassword(MD5Util.encode(password));
            baseService.updateById(userAdmin);
            return R.successUpdate();
        } else {
            return R.error(RType.ADMIN_USER_NO_PASSWORD);
        }
    }
}
