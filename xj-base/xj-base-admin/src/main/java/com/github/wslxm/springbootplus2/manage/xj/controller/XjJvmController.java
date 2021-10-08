package com.github.wslxm.springbootplus2.manage.xj.controller;

import com.github.wslxm.springbootplus2.core.constant.BaseConstant;
import com.github.wslxm.springbootplus2.core.result.R;
import com.github.wslxm.springbootplus2.manage.xj.model.vo.XjToolJvmInfoVO;
import com.github.wslxm.springbootplus2.manage.xj.service.XjToolServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin + "/xj/jvm")
@Api(value = "XjJvmController", tags = "base-plus--jvm信息获取")
public class XjJvmController {


    @Autowired
    private XjToolServer xjToolServer;

    @GetMapping(value = "/jvmInfo")
    @ApiOperation(value = "3、系统的jvm信息")
    public R<XjToolJvmInfoVO> jvmInfo() {
        return R.success(xjToolServer.jvmInfo());
    }
}
