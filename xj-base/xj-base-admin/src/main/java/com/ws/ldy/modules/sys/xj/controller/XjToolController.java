package com.ws.ldy.modules.sys.xj.controller;

import cn.hutool.system.*;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.ws.ldy.common.result.R;
import com.ws.ldy.constant.BaseConstant;
import com.ws.ldy.modules.sys.xj.model.dto.XjJavaCodeDTO;
import com.ws.ldy.modules.sys.xj.util.JavaCodeRunV2Util;
import com.ws.ldy.modules.sys.xj.util.TransformUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin + "/xj/tool/")
@Api(value = "XjToolController", tags = "base-plus--系统小工具")
public class XjToolController {


    @ApiOperation("1、符号转文字工具")
    @RequestMapping(value = "/fhConvert", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "content", value = "内容", required = false, paramType = "query"),
            @ApiImplicitParam(name = "narrow", value = "缩小倍数（建议3或4）", required = false, paramType = "query")
    })
    public R<String> fhConvert(@RequestParam String content, @RequestParam Integer narrow, @RequestParam String fill) {
        String transform = null;
        if (StringUtils.isNotBlank(content)) {
            transform = TransformUtil.transform(content, narrow, fill);
        }
        return R.success(transform);
    }


    @RequestMapping(value = "/javaCodeRun", method = RequestMethod.POST)
    @ApiOperation(value = "2、java代码运行器", notes = "")
    public R<String> upd(@RequestBody XjJavaCodeDTO dto) {
        return R.success(JavaCodeRunV2Util.run(dto.getJavaCode()));
    }


    @RequestMapping(value = "/jvmInfo", method = RequestMethod.GET)
    @ApiOperation(value = "3、系统的jvm信息", notes = "")
    public void jvmInfo() {
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
