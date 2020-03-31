package com.ws.ldy.admin.controller;

import com.ws.ldy.admin.entity.UserAdmin;
import com.ws.ldy.admin.service.impl.RoleUserAdminServiceImpl;
import com.ws.ldy.admin.service.impl.UserAdminServiceImpl;
import com.ws.ldy.base.controller.BaseController;
import com.ws.ldy.common.query.IPage;
import com.ws.ldy.common.query.QueryCriteria;
import com.ws.ldy.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

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
@Api(value = "UserAdminController", tags = "用户管理")
public class UserAdminController extends BaseController {

    @Autowired
    private UserAdminServiceImpl userAdminServiceImpl;
    @Autowired
    private RoleUserAdminServiceImpl roleUserAdminServiceImpl;


    @RequestMapping(value = "/findAll/{type}", method = RequestMethod.GET)
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "roleId", value = "角色Id,当type=2时必传", required = false, paramType = "query"),
            @ApiImplicitParam(name = "type", value = "1=用户列表查询  2=用户角色分配列表查询(赋予选中状态)", required = true, paramType = "path"),
    })
    public Result findAll(@PathVariable Integer type, int page, int limit, Integer roleId) {
//        Map<String, Object> param = new HashMap<>(2);
          Page<UserAdmin> userPages = null;
//        param.put("id", getString("id", ""));
//        Sort sort = new Sort(Sort.Direction.ASC, "id");
        QueryCriteria queryCriteria = new QueryCriteria().equal("id", getString("id", "")).orderByAsc("id");
        if (type == 1) {
            //查询所有
            userPages = userAdminServiceImpl.page(this.getPage(), queryCriteria.build(), queryCriteria.getSort());
            return success(userPages.getContent(), userPages.getTotalPages());
        } else {
            //查询所有
            userPages = userAdminServiceImpl.page(new IPage(1,9999), queryCriteria.build(), queryCriteria.getSort());
            //角色选中状态处理
            List<UserAdmin> users = userPages.getContent();
            users = roleUserAdminServiceImpl.RoleUserChecked(users, roleId);
            return success(users, userPages.getTotalPages());
        }
    }


    /***
     * TODO  添加/修改
     * @param type t=1 添加，=2修改
     * @param user 对象数据
     * @date 2019/11/14 17:34
     * @return java.lang.String
     */
    @RequestMapping(value = "/save/{type}", method = RequestMethod.POST)
    @ApiOperation("添加/修改")
    public Result save(@PathVariable Integer type, UserAdmin user) {
        if (type == 1) {
            user.setTime(new Date());
            userAdminServiceImpl.save(user);
        } else {
            userAdminServiceImpl.save(user);
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
    public Result delete(Integer[] ids) {
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
    public Result updPwd(String oldPassword, String password) {
        UserAdmin user = (UserAdmin) session.getAttribute("user");
        if (user.getPassword().equals(oldPassword)) {
            user.setPassword(password);
            userAdminServiceImpl.save(user);
            return success();
        } else {
            return success();
        }
    }
}
