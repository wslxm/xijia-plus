package com.ws.ldy.baseadmin.controller;

import com.ws.ldy.admincore.common.annotation.LdyAuthority;
import com.ws.ldy.admincore.common.vo.ResponseData;
import com.ws.ldy.admincore.controller.BaseController;
import com.ws.ldy.baseadmin.entity.UserAdmin;
import com.ws.ldy.baseadmin.service.impl.RoleUserAdminServiceImpl;
import com.ws.ldy.baseadmin.service.impl.UserAdminServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO  系统用户
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 13:38
 */
@SuppressWarnings("all")
@RestController
@RequestMapping("/userAdmin")
@LdyAuthority(value = {"userAdmin", "系统用户"})
@Api(tags = {"Admin-User"}, description = "用户管理")
public class UserAdminController extends BaseController {

    @Autowired
    private UserAdminServiceImpl userAdminServiceImpl;
    @Autowired
    private RoleUserAdminServiceImpl roleUserAdminServiceImpl;


    @GetMapping("/findAll/{type}")
    @LdyAuthority(value = {"user:findAll", "查询"})
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "roleId", value = "角色Id,当type=2时必传", required = false, paramType = "query"),
            @ApiImplicitParam(name = "type", value = "1=用户列表查询  2=用户角色分配列表查询(赋予选中状态)", required = true, paramType = "path"),
    })
    public ResponseData findAll(@PathVariable Integer type, int page, int limit, Integer roleId) {
        Map<String, Object> param = new HashMap<>(2);
        Page<UserAdmin> userPages = null;
        param.put("id", getString("id", ""));
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        if (type == 1) {
            //查询所有
            userPages = userAdminServiceImpl.page(page, limit, param, sort);
            return ResponseData.success(userPages.getContent(), userPages.getTotalPages());
        } else {
            //查询所有
            userPages = userAdminServiceImpl.page(page, 999, param, sort);
            //角色选中状态处理
            List<UserAdmin> users = userPages.getContent();
            users = roleUserAdminServiceImpl.RoleUserChecked(users, roleId);
            return ResponseData.success(users, userPages.getTotalPages());
        }
    }


    /***
     * TODO  添加/修改
     * @param type t=1 添加，=2修改
     * @param user 对象数据
     * @date 2019/11/14 17:34
     * @return java.lang.String
     */
    @PostMapping("/save/{type}")
    @LdyAuthority(value = {"user:save", "添加/修改"})
    @ApiOperation("添加/修改")
    public ResponseData save(@PathVariable Integer type, UserAdmin user) {
        if (type == 1) {
            user.setTime(new Date());
            userAdminServiceImpl.save(user);
        } else {
            userAdminServiceImpl.save(user);
        }
        return ResponseData.success("success");
    }


    /**
     * TODO  批量删除/单删除
     *
     * @param ids 要删除的数据Id数组
     * @author 王松
     * @WX-QQ 1720696548
     * @date 2019/11/14 18:17
     */
    @PostMapping("/delete")
    @LdyAuthority(value = {"user:delete", "删除"})
    @ApiOperation("批量删除/单删除")
    public ResponseData delete(Integer[] ids) {
        userAdminServiceImpl.deleteByIds(ids);
        return ResponseData.success("success");
    }


    /***
     * TODO  账号登录
     * @param account
     * @param password
     * @date 2019/11/18 10:13
     * @return java.lang.String
     */
    @PostMapping("/login")
    @LdyAuthority(value = {"user:login", "登录"})
    @ApiOperation("账号登录")
    public ResponseData login(String account, String password) {
        UserAdmin user = userAdminServiceImpl.findAccountPwd(account, password);
        if (user != null) {
            session.setAttribute("user", user);
            return ResponseData.success("success");
        } else {
            return ResponseData.success("no");
        }
    }


    /**
     * TODO  退出登录
     *
     * @return java.lang.String
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/2/9 0009 16:08
     */
    @PostMapping("/logout")
    @ApiOperation("退出登录")
    public ResponseData logout() {
        session.removeAttribute("user");
        return ResponseData.success("success");
    }

    /***
     * TODO  密码修改
     * @param password
     * @date 2019/11/18 10:13
     * @return java.lang.String
     */
    @PostMapping("/updPwd")
    @LdyAuthority(value = {"user:updPwd", "密码修改"})
    @ApiOperation("密码修改")
    public ResponseData updPwd(String oldPassword, String password) {
        UserAdmin user = (UserAdmin) session.getAttribute("user");
        if (user.getPassword().equals(oldPassword)) {
            user.setPassword(password);
            userAdminServiceImpl.save(user);
            return ResponseData.success("success");
        } else {
            return ResponseData.success("no");
        }
    }
}
