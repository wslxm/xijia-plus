package io.github.wslxm.springbootplus2.manage.sys.controller;

import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.Result;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.login.LoginDTO;
import io.github.wslxm.springbootplus2.manage.sys.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BaseConstant.Uri.API_ADMIN + "/sys/login")
@Api(value = " LoginController", tags = "base--sys--登录")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @ApiOperation("用户登录")
    @PostMapping
    public Result<Boolean> login(@RequestBody @Validated LoginDTO dto) {
        return Result.success(loginService.login(dto));
    }
}