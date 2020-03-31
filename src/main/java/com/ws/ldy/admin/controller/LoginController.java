package com.ws.ldy.admin.controller;


import com.ws.ldy.admin.entity.UserAdmin;
import com.ws.ldy.admin.service.impl.UserAdminServiceImpl;
import com.ws.ldy.admin.vo.LoginVo;
import com.ws.ldy.base.controller.BaseController;
import com.ws.ldy.common.error.ErrorException;
import com.ws.ldy.common.result.Result;
import com.ws.ldy.common.result.ResultEnum;
import com.ws.ldy.common.utils.UUIDUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * TODO  登录
 * @author ws
 * @mail  1720696548@qq.com
 * @date  2020/3/30 0030 19:53
 * @return
 */
@RestController
@RequestMapping("/userAdmin")
@Api(value = "LoginController", tags = "登录")
public class LoginController extends BaseController {

    @Autowired
    private UserAdminServiceImpl userAdminServiceImpl;

    /**
     * TODO 登录
     *
     * @param account
     * @param password
     * @return com.ws.ldy.common.result.Result<com.ws.ldy.admin.vo.LoginVo>
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/3/30 0030 19:50
     */
    @ApiOperation("登录")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "账号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query")
    })
    public Result<LoginVo> login(@RequestParam String account, @RequestParam String password) {
        UserAdmin user = userAdminServiceImpl.findByAccount(account);
        if (user == null) {
            throw new ErrorException(ResultEnum.SYS_ERROR.getCode(), "没有用户信息");
        }
        if (!user.getPassword().equals(password)) {
            throw new ErrorException(ResultEnum.SYS_ERROR.getCode(), "密码错误");
        }
        String token = UUIDUtil.creatUUID();
        session.setAttribute(token, user);
        return success(new LoginVo(token));
    }




    /**
     * TODO  登录
     *
     * @return com.ws.ldy.common.result.Result
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/3/30 0030 19:51
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ApiOperation("退出登录")
    public Result<Void> logout() {
        session.removeAttribute(super.getToken());
        return success();
    }
}
