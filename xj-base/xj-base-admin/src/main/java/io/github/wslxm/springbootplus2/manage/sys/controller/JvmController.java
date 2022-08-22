package io.github.wslxm.springbootplus2.manage.sys.controller;

import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.Result;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.ToolJvmInfoVO;
import io.github.wslxm.springbootplus2.manage.sys.service.ToolServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  @author wangsong
 */
@RestController
@RequestMapping(BaseConstant.Uri.API_ADMIN + "/sys/jvm")
@Api(value = "JvmController", tags = "base--sys--jvm信息获取")
public class JvmController {


    @Autowired
    private ToolServer toolServer;

    @GetMapping(value = "/jvmInfo")
    @ApiOperation(value = "获取系统的jvm信息")
    public Result<ToolJvmInfoVO> jvmInfo() {
        return Result.success(toolServer.jvmInfo());
    }
}
