package com.ws.ldy.modules.third.wechat.app.controller;


import com.ws.ldy.common.result.R;
import com.ws.ldy.modules.third.wechat.app.server.WxAppService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信网页授权
 * @author wangsong
 * @date 2020/9/22 0022 11:49
 * @return
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/open/wx/app")
@Api(value = "WxAppController", tags = "WX  -->  APP小程序登录")
public class WxAppController {

    @Autowired
    private WxAppService wxAppService;


    @RequestMapping(value = "/auth/getOpenId", method = RequestMethod.GET)
    @ApiOperation(value = "通过code 获取openId", notes = "" +
            "详见文档下的调用 auth.code2Session 接口说明：https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/login.html")
    public R<String> getOpenId(@RequestParam String code) {
        return wxAppService.getOpenId(code);
    }


    @RequestMapping(value = "qrcode/create", method = RequestMethod.GET)
    @ApiOperation(value = "获取图形太阳二维码(返回base64图片)", httpMethod = "GET",
            notes = "参数参考微信文档：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/qr-code/wxacode.getUnlimited.html")
    public R<String> createQrCode(@RequestParam String scene,
                                  @RequestParam String page,
                                  @RequestParam Integer width,
                                  @RequestParam Boolean autoColor,
                                  @RequestParam Boolean isHyaline) {
        return wxAppService.createQrCode(scene, page, width, autoColor, isHyaline);
    }


    @RequestMapping(value = "/subscribe/sendMsgTest", method = RequestMethod.POST)
    @ApiOperation(value = "订阅消息发送测试", notes = "" +
            "1、需在微信小程序中配置模板： 微信公众平台 -> 小程序 -> 功能 -> 订阅消息 \r\n" +
            "2、小程序需发起订阅请求,用户点击同意才能发送成功\r\n" +
            "3、详见微信文档：https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/subscribe-message.html")
    public R<String> sendMsg(@RequestParam String openId) {
        // 模板Id
        String templateId = "mZlYMEKvhF1qFqjJE88B4PdHXQprWLyQh2DbLzMCCTs";
        // 模板参数
        Map<String, String> sendMsg = new HashMap<>();
        sendMsg.put("thing1", "参数1");
        sendMsg.put("thing2", "参数2");
        // 发送
        return wxAppService.sendMsg(openId, templateId, sendMsg);
    }
}