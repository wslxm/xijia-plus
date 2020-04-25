package com.ws.ldy.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.admin.model.dto.UserAdminDto;
import com.ws.ldy.admin.model.entity.UserAdmin;
import com.ws.ldy.admin.model.vo.UserAdminVo;
import com.ws.ldy.admin.service.impl.RoleUserAdminServiceImpl;
import com.ws.ldy.admin.service.impl.UserAdminServiceImpl;
import com.ws.ldy.base.controller.BaseController;
import com.ws.ldy.config.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * TODO  系统用户
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 13:38
 */
@RestController
@RequestMapping("/userAdmin")
@Api(value = "UserAdminController", tags = "用户管理")
public class UserAdminController extends BaseController {

    @Resource
    private UserAdminServiceImpl userAdminServiceImpl;
    @Resource
    private RoleUserAdminServiceImpl roleUserAdminServiceImpl;


    @RequestMapping(value = "/findUser", method = RequestMethod.GET)
    @ApiOperation("当前登录用户信息")
    public Result<UserAdminVo> findUser() {
        return successFind(getUserAdmin().convert(UserAdminVo.class));
    }


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "id", value = "数据Id", required = false, paramType = "path"),
            @ApiImplicitParam(name = "username", value = "用户名", required = false, paramType = "path"),
            @ApiImplicitParam(name = "account", value = "账号", required = false, paramType = "path"),
    })
    public Result<IPage<UserAdminVo>> findPage(Integer id, String username, String account) {
        Page<UserAdmin> page = userAdminServiceImpl.page(this.getPage(), new LambdaQueryWrapper<UserAdmin>()
                .orderByAsc(UserAdmin::getId)
                .eq(id != null, UserAdmin::getId, id)
                .eq(StringUtils.isNotBlank(account), UserAdmin::getAccount, account)
                .like(StringUtils.isNotBlank(username), UserAdmin::getUsername, username)
        );
        return successFind(pageVo(page, UserAdminVo.class));
    }


    @RequestMapping(value = "/findRoleIdList", method = RequestMethod.GET)
    @ApiOperation("查询指定角色下的所有用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色Id", required = false, paramType = "query"),
            @ApiImplicitParam(name = "username", value = "用户名", required = false, paramType = "query")
    })
    public Result<List<UserAdminVo>> findRoleIdList(Integer roleId, String username) {
        List<UserAdmin> userList = null;
        if (StringUtils.isNotBlank(username)) {
            userList = userAdminServiceImpl.list(new LambdaQueryWrapper<UserAdmin>()
                    .like(UserAdmin::getUsername, username)
            );
        } else {
            // 查询所有
            userList = userAdminServiceImpl.list();
        }
        //角色选中状态处理
        List<UserAdminVo> userAdminVos = roleUserAdminServiceImpl.roleUserChecked(userList, roleId);
        return successFind(userAdminVos);
    }


    /***
     * TODO  添加
     * @param userAdminDto 对象数据
     * @date 2019/11/14 17:34
     * @return java.lang.String
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation("添加")
    public Result<Void> insert(@RequestBody UserAdminDto userAdminDto) {
        UserAdmin userAdmin = userAdminDto.convert(UserAdmin.class);
        userAdmin.setTime(new Date());
        userAdminServiceImpl.save(userAdmin);
        return successInsert();
    }


    /***
     * TODO  修改
     * @param userAdminDto 对象数据
     * @date 2019/11/14 17:34
     * @return java.lang.String
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ApiOperation("编辑")
    public Result<Void> update(@RequestBody UserAdminDto userAdminDto) {
        userAdminServiceImpl.updateById(userAdminDto.convert(UserAdmin.class));
        return successUpdate();
    }


    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ApiOperation("单行删除")
    public Result<Void> delete(@RequestParam Integer id) {
        userAdminServiceImpl.removeById(id);
        return successDelete();
    }


    @RequestMapping(value = "/deleteByIds", method = RequestMethod.DELETE)
    @ApiOperation("批量删除")
    public Result<Void> deleteByIds(@RequestParam Integer[] ids) {
        userAdminServiceImpl.removeByIds(Arrays.asList(ids));
        return successDelete();
    }


    /***
     * TODO  密码修改
     * @param password
     * @date 2019/11/18 10:13
     * @return java.lang.String
     */
    @RequestMapping(value = "/updPwd", method = RequestMethod.PUT)
    @ApiOperation("密码修改")
    public Result<Void> updPwd(@RequestParam String oldPassword, @RequestParam String password) {
        UserAdmin userAdmin = this.getUserAdmin();
        if (userAdmin.getPassword().equals(oldPassword)) {
            userAdmin.setPassword(password);
            userAdminServiceImpl.save(userAdmin);
            return successUpdate();
        } else {
            return error(500, "原密码错误");
        }
    }
}
