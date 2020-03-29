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
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userAdmin")
@Api(value = "LoginController", tags = "登录")
public class LoginController extends BaseController {

    @Autowired
    private UserAdminServiceImpl userAdminServiceImpl;

    @GetMapping("/login")
    @ApiOperation("登录")
    public Result<LoginVo> login(@RequestParam String account, @RequestParam String password) {
        UserAdmin user = userAdminServiceImpl.findAccountPwd(account, password);
        if (user == null) {
            throw new ErrorException(ResultEnum.SYS_ERROR.getCode(), "账号或密码错误");
        }
        String token = UUIDUtil.creatUUID();
        session.setAttribute(token, user);
        return success(new LoginVo(token));
    }


    @GetMapping("/logout")
    @ApiOperation("退出登录")
    public Result logout() {
        session.removeAttribute(super.getToken());
        return success();
    }
}
