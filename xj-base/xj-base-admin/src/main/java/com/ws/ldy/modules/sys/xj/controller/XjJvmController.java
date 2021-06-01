package com.ws.ldy.modules.sys.xj.controller;

import com.ws.ldy.common.result.R;
import com.ws.ldy.constant.BaseConstant;
import com.ws.ldy.modules.sys.xj.model.vo.XjToolJvmInfoVO;
import com.ws.ldy.modules.sys.xj.service.XjToolServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin + "/xj/jvm/")
@Api(value = "XjJvmController", tags = "base-plus--jvm信息获取")
public class XjJvmController {


    @Autowired
    private XjToolServer xjToolServer;

    @SneakyThrows
    @RequestMapping(value = "/jvmInfo", method = RequestMethod.GET)
    @ApiOperation(value = "3、系统的jvm信息", notes = "")
    public R<XjToolJvmInfoVO> jvmInfo() {
        return R.success(xjToolServer.jvmInfo());
    }
}
