package com.github.wslxm.springbootplus2.starter.aliyun.sms.controller;

import com.github.wslxm.springbootplus2.core.result.R;
import com.github.wslxm.springbootplus2.starter.aliyun.sms.util.AliSmsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 阿里云OSS 文件上传下载
 *
 * @author peter 2018/10/20 21:32
 */
@RestController
@Api(value = "AliSmsController", tags = "AliYun  --> SMS短信服务")
@RequestMapping("/api/open/aliSms")
@Slf4j
public class AliSmsController {


    @Autowired
    private AliSmsUtil aliSmsUtil;

    @ApiOperation("SMS--发送测试短信")
    @RequestMapping(value = "/sendSms", method = RequestMethod.POST)
    public R<String> downloadNet(@RequestParam String phone) {
        return aliSmsUtil.sendCode(phone);
    }
}

