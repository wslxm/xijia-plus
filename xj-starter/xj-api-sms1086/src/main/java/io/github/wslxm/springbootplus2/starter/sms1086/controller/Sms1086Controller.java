package io.github.wslxm.springbootplus2.starter.sms1086.controller;

import io.github.wslxm.springbootplus2.core.result.R;
import io.github.wslxm.springbootplus2.starter.sms1086.util.Sms1086Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
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
        // 下方验证码
        R<String> rCode = sms1086Util.sendCode(phone);
        // 判断是否有效
        R<String> verifyCode = sms1086Util.verifySMS(phone, rCode.getData());
        log.debug("验证验证码：" + verifyCode.toString());
        return rCode;
    }


    @ApiOperation("SMS--发送内容")
    @RequestMapping(value = "/sendMsg", method = RequestMethod.POST)
    public R<String> sendMsg(@RequestParam String phone, String msg) {
        return sms1086Util.sendMsg(phone, msg);
    }
}

