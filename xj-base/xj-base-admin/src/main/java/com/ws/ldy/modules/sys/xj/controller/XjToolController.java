package com.ws.ldy.modules.sys.xj.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.ws.ldy.common.result.R;
import com.ws.ldy.constant.BaseConstant;
import com.ws.ldy.modules.sys.xj.model.dto.XjJavaCodeDTO;
import com.ws.ldy.modules.sys.xj.model.vo.XjToolJvmInfoVO;
import com.ws.ldy.modules.sys.xj.service.XjToolServer;
import com.ws.ldy.modules.sys.xj.util.JavaCodeRunV2Util;
import com.ws.ldy.modules.sys.xj.util.TransformUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin + "/xj/tool/")
@Api(value = "XjToolController", tags = "base-plus--系统小工具")
public class XjToolController {


    @Autowired
    private XjToolServer xjToolServer;

    @ApiOperation("1、符号转文字工具")
    @RequestMapping(value = "/fhConvert", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "content", value = "内容", required = false, paramType = "query"),
            @ApiImplicitParam(name = "narrow", value = "缩小倍数（建议3或4）", required = false, paramType = "query")
    })
    public R<String> fhConvert(@RequestParam String content, @RequestParam Integer narrow, @RequestParam String fill) throws UnsupportedEncodingException {
        String transform = null;
        if (StringUtils.isNotBlank(content)) {
            transform = TransformUtil.transform(content, narrow, java.net.URLDecoder.decode(fill, "UTF-8"));
        }
        return R.success(transform);
    }

    @RequestMapping(value = "/javaCodeRun", method = RequestMethod.POST)
    @ApiOperation(value = "2、java代码运行器", notes = "")
    public R<String> upd(@RequestBody XjJavaCodeDTO dto) {
        return R.success(JavaCodeRunV2Util.run(dto.getJavaCode()));
    }

    @SneakyThrows
    @RequestMapping(value = "/jvmInfo", method = RequestMethod.GET)
    @ApiOperation(value = "3、系统的jvm信息", notes = "")
    public R<XjToolJvmInfoVO> jvmInfo() {
        return R.success(xjToolServer.jvmInfo());
    }
}
