package com.ws.ldy.modules.sys.base.controller;

import cn.hutool.system.*;
import com.ws.ldy.constant.BaseConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BaseConstant.Uri.apiOpen + "/jvm")
@Api(value = "JvmController", tags = "base-jvm")
public class JvmController {


    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ApiOperation(value = "查询jvm信息", notes = "")
    public void info() {
        //  Java Virtual Machine Specification信息
        JvmSpecInfo jvmSpecInfo = SystemUtil.getJvmSpecInfo();
        //  Java Virtual Machine Implementation信息
        JvmInfo jvmInfo = SystemUtil.getJvmInfo();
        //  Java Specification信息
        JavaSpecInfo javaSpecInfo = SystemUtil.getJavaSpecInfo();
        //  Java Implementation信息
        JavaInfo javaInfo = SystemUtil.getJavaInfo();
        //  Java运行时信息,jdk信息
        JavaRuntimeInfo javaRuntimeInfo = SystemUtil.getJavaRuntimeInfo();
        // 系统信息
        OsInfo osInfo = SystemUtil.getOsInfo();
        // 计算机信息,项目位置
        UserInfo userInfo = SystemUtil.getUserInfo();
        // 主机ip信息
        HostInfo hostInfo = SystemUtil.getHostInfo();
        // 运行时信息，包括内存总大小、已用大小、可用大小等
        RuntimeInfo runtimeInfo = SystemUtil.getRuntimeInfo();
        System.out.println("");
    }
}
