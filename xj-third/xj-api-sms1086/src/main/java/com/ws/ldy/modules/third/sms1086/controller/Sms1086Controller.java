package com.ws.ldy.modules.third.sms1086.controller;

import com.ws.ldy.common.result.R;
import com.ws.ldy.modules.third.sms1086.util.Sms1086Util;
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
@Api(value = "Sms1086Controller", tags = "Sms1086  --> SMS短信服务")
@RequestMapping("/api/open/sms1086")
@Slf4j
public class Sms1086Controller {


    @Autowired
    private Sms1086Util sms1086Util;

    @ApiOperation("SMS--发送验证码")
    @RequestMapping(value = "/sendCode", method = RequestMethod.POST)
    public R<String> sendCode(@RequestParam String phone) {
        return sms1086Util.sendCode(phone);
    }


    @ApiOperation("SMS--发送内容")
    @RequestMapping(value = "/sendMsg", method = RequestMethod.POST)
    public R<String> sendMsg(@RequestParam String phone,String msg) {
        return sms1086Util.sendMsg(phone,msg);
    }
}

