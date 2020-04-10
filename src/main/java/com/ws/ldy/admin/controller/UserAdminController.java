package com.ws.ldy.admin.controller;

import com.ws.ldy.admin.dto.UserAdminDto;
import com.ws.ldy.admin.entity.UserAdmin;
import com.ws.ldy.admin.service.impl.RoleUserAdminServiceImpl;
import com.ws.ldy.admin.service.impl.UserAdminServiceImpl;
import com.ws.ldy.admin.vo.UserAdminVo;
import com.ws.ldy.base.controller.BaseController;
import com.ws.ldy.base.entity.BeanDtoVoUtils;
import com.ws.ldy.base.query.QueryCriteria;
import com.ws.ldy.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private UserAdminServiceImpl userAdminServiceImpl;
    @Autowired
    private RoleUserAdminServiceImpl roleUserAdminServiceImpl;


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "id", value = "数据Id", required = false, paramType = "path"),
            @ApiImplicitParam(name = "username", value = "用户名", required = false, paramType = "path"),
    })
    public Result<Page<UserAdminVo>> findPage(Integer id, String username) {
        Page<UserAdmin> userPage = userAdminServiceImpl.page(this.getPage(), new QueryCriteria()
                .eq(id != null, "id", id)
                .like(StringUtils.isNotBlank(username), "username", username)
                .orderByAsc("id")
        );
        return success(this.pageVoStream(userPage, UserAdminVo.class));
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
            userList = userAdminServiceImpl.list(new QueryCriteria().like("username", username));
        } else {
            // 查询所有
            userList = userAdminServiceImpl.findAll();
        }
        //角色选中状态处理
        List<UserAdminVo> userAdminVos = roleUserAdminServiceImpl.roleUserChecked(userList, roleId);
        return success(BeanDtoVoUtils.listVoStream(userAdminVos, UserAdminVo.class));
    }


    /***
     * TODO  添加/修改
     * @param type t=1 添加，=2修改
     * @param userAdminDto 对象数据
     * @date 2019/11/14 17:34
     * @return java.lang.String
     */
    @RequestMapping(value = "/save/{type}", method = RequestMethod.POST)
    @ApiOperation("添加/修改")
    public Result<Void> save(@PathVariable Integer type, @RequestBody UserAdminDto userAdminDto) {
        if (type == 1) {
            userAdminDto.setTime(new Date());
            userAdminServiceImpl.save(userAdminDto.convert(UserAdmin.class));
        } else {
            userAdminServiceImpl.save(userAdminDto.convert(UserAdmin.class));
        }
        return success();
    }


    /**
     * TODO  批量删除/单删除
     *
     * @param ids 要删除的数据Id数组
     * @author 王松
     * @WX-QQ 1720696548
     * @date 2019/11/14 18:17
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ApiOperation("批量删除/单删除")
    public Result<Void> delete(@RequestBody Integer[] ids) {
        userAdminServiceImpl.deleteByIds(ids);
        return success();
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
            return success();
        } else {
            return error(500, "原密码错误");
        }
    }
}
