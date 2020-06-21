package com.ws.ldy.admin.controller;


import com.ws.ldy.admin.model.entity.UserAdmin;
import com.ws.ldy.admin.model.vo.LoginVo;
import com.ws.ldy.admin.service.impl.UserAdminServiceImpl;
import com.ws.ldy.base.controller.BaseController;
import com.ws.ldy.base.enums.BaseConstant;
import com.ws.ldy.common.result.Result;
import com.ws.ldy.common.result.ResultEnum;
import com.ws.ldy.common.user.AdminUserUtils;
import com.ws.ldy.common.utils.UUIDUtils;
import com.ws.ldy.config.error.ErrorException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO  登录
 *
 * @author ws
 * @mail 1720696548@qq.com
 * @date 2020/3/30 0030 19:53
 * @return
 */
@RestController
@RequestMapping("/loginAdmin")
@Api(value = "LoginController", tags = "登录", description = BaseConstant.InterfaceType.PC_ADMIN)
public class LoginController extends BaseController<UserAdminServiceImpl> {

    /**
     * TODO 登录
     *
     * @param account
     * @param password
     * @return com.ws.ldy.common.result.Result<com.ws.ldy.admin.model.vo.LoginVo>
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
        UserAdmin user = baseService.findByAccount(account);
        if (user == null) {
            throw new ErrorException(ResultEnum.SYS_ERROR.getCode(), "没有用户信息");
        }
        if (!user.getPassword().equals(password)) {
            throw new ErrorException(ResultEnum.SYS_ERROR.getCode(), "密码错误");
        }
        String token = UUIDUtils.creatUUID();
        session.setAttribute(AdminUserUtils.ADMIN + token, user);
        return Result.success(new LoginVo(token, user.getUsername()));
    }


    /**
     * TODO  退出登录
     *
     * @return com.ws.ldy.common.result.Result
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/3/30 0030 19:51
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ApiOperation("退出登录")
    public Result<Void> logout() {
        session.removeAttribute(AdminUserUtils.getToken());
        return Result.success();
    }
}
