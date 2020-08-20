package com.ws.ldy.modules.admin.controller;
/* 登录由 jwt+ springSecurity 来处理，config/auth包下 */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.config.auth.util.JwtUtil;
import com.ws.ldy.config.auth.util.MD5Util;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.enums.Enums;
import com.ws.ldy.modules.admin.model.entity.AdminUser;
import com.ws.ldy.modules.admin.service.AdminAuthorityService;
import com.ws.ldy.modules.admin.service.AdminUserService;
import com.ws.ldy.others.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 登录
 *
 * @author ws
 * @mail 1720696548@qq.com
 * @date 2020/3/30 0030 19:53
 * @return
 */
@RestController
@RequestMapping("/admin")
@Api(value = "AdminLoginController", tags = "登录", description = BaseConstant.InterfaceType.PC_ADMIN)
public class AdminLoginController extends BaseController<AdminUserService> {

    @Autowired
    private AdminAuthorityService adminAuthorityService;

    /**
     *  登录
     *
     * @param username 账号
     * @param password 密码
     * @return com.ws.ldy.common.result.Result<com.ws.ldy.admin.model.vo.LoginVO>
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/3/30 0030 19:50
     */
    @ApiOperation("登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "账号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query")
    })
    public R<Void> login(@RequestParam String username, @RequestParam String password) {
        // 1、判断账号
        AdminUser user = baseService.getOne(new LambdaQueryWrapper<AdminUser>().eq(AdminUser::getUsername, username));
        if (user == null) {
            throw new ErrorException(RType.LOGIN_IS_NO_ACCOUNT);
        }
        // 2、判断密码
        if (!user.getPassword().equals(MD5Util.encode(password))) {
            throw new ErrorException(RType.LOGIN_ERROR_USER_PASSWORD);
        }
        // 3、判断禁用
        if (user.getDisable() != Enums.Base.Disable.DISABLE_0.getValue()) {
            throw new ErrorException(RType.LOGIN_IS_NO_DISABLE);
        }
        // 登录成功
        // 4、获取权限列表-未禁用
        List<SimpleGrantedAuthority> auth = adminAuthorityService.findUserIdRoleAuthorityNoDisable(user.getId());
        // 5、生成jwt
        String jwtToken = JwtUtil.generateToken(user, auth);
        response.setHeader(BaseConstant.Sys.TOKEN, jwtToken);
        // 6、刷新登录时间
        AdminUser updAdminUser = new AdminUser();
        updAdminUser.setId(user.getId());
        updAdminUser.setEntTime(LocalDateTime.now());
        baseService.updateById(updAdminUser);
        return R.success();
    }
}

