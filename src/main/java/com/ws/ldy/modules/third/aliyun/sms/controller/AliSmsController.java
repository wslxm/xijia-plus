package com.ws.ldy.modules.third.aliyun.sms.controller;

import com.ws.ldy.common.result.R;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import com.ws.ldy.modules.third.aliyun.sms.util.AliSmsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/aliSms")
@Slf4j
public class AliSmsController extends BaseController {




    @ApiOperation("SMS--发送测试短信")
    @RequestMapping(value = "/sendSms", method = RequestMethod.POST)
    public R<String> downloadNet(@RequestParam String phone) {
        String code = AliSmsUtil.sendTest(phone);
        return R.success(code);
    }
}

