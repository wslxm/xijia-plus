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
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query", example = "20"),
    })
    public R<IPage<UserAdminVo>> findPage(
            @ApiParam(value = "数据Id", required = false) @RequestParam(required = false) Integer id,
            @ApiParam(value = "账号/手机号", required = false) @RequestParam(required = false) String username,
            @ApiParam(value = "姓名/用户名", required = false) @RequestParam(required = false) String fullName) {
        Page<UserAdmin> page = baseService.page(this.getPage(), new LambdaQueryWrapper<UserAdmin>()
                .orderByAsc(UserAdmin::getId)
                .eq(id != null, UserAdmin::getId, id)
                .eq(StringUtils.isNotBlank(fullName), UserAdmin::getFullName, fullName)
                .like(StringUtils.isNotBlank(username), UserAdmin::getUsername, username)
        );
        return R.success(BeanDtoVoUtil.pageVo(page, UserAdminVo.class));
    }


    @RequestMapping(value = "/findRoleUser", method = RequestMethod.GET)
    @ApiOperation("查询角色当前用户(isChecked=true)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色Id", required = false, paramType = "query"),
            @ApiImplicitParam(name = "username", value = "用户名", required = false, paramType = "query")
    })
    public R<List<UserAdminVo>> findByRoleId(String roleId, String username) {
        List<UserAdmin> userList = null;
        if (StringUtils.isNotBlank(username)) {
            userList = baseService.list(new LambdaQueryWrapper<UserAdmin>()
                    .like(UserAdmin::getUsername, username)
            );
        } else {
            // 查询所有
            userList = baseService.list();
        }
        //角色选中状态处理
        List<UserAdminVo> userAdminVos = roleUserAdminService.roleUserChecked(userList, roleId);
        return R.success(userAdminVos);
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation("添加")
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
    @ApiOperation("单删除")
    public R<Void> del(@RequestParam String id) {
        baseService.removeById(id);
        return R.successDelete();
    }


    @RequestMapping(value = "/delByIds", method = RequestMethod.DELETE)
    @ApiOperation("批量删除")
    public R<Void> delByIds(@RequestParam String[] ids) {
        baseService.removeByIds(Arrays.asList(ids));
        return R.successDelete();
    }


    @RequestMapping(value = "/updByPassword", method = RequestMethod.PUT)
    @ApiOperation("密码修改")
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
