package io.github.wslxm.springbootplus2.manage.xj.controller;

import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.R;
import io.github.wslxm.springbootplus2.manage.xj.model.vo.XjToolJvmInfoVO;
import io.github.wslxm.springbootplus2.manage.xj.service.XjToolServer;
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
@RequestMapping(BaseConstant.Uri.API_ADMIN+ "/xj/jvm")
@Api(value = "XjJvmController", tags = "base--plus--jvm信息获取")
public class XjJvmController {


    @Autowired
    private XjToolServer xjToolServer;

    @GetMapping(value = "/jvmInfo")
    @ApiOperation(value = "3、系统的jvm信息")
    public R<XjToolJvmInfoVO> jvmInfo() {
        return R.success(xjToolServer.jvmInfo());
    }
}
