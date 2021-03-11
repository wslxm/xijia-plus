package com.ws.ldy.modules.third.wechat.app.controller;


import com.ws.ldy.common.result.R;
import com.ws.ldy.modules.third.wechat.app.util.WxAppQrcodeUtil;
import com.ws.ldy.modules.third.wechat.app.util.WxAppUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    private WxAppUtil wxAppUtil;

    @Autowired
    private WxAppQrcodeUtil wxAppQrcodeUtil;

//    @Autowired
//    private WxAppSubscribeSendUtil WxAppSubscribeSendUtil;

    @RequestMapping(value = "/auth/getOpenId", method = RequestMethod.GET)
    @ApiOperation(value = "通过code 获取openId", notes = "")
    public R<String> getOpenId(@RequestParam String code) {
        return wxAppUtil.getOpenId(code);
    }


    @RequestMapping(value = "qrcode/create", method = RequestMethod.GET)
    @ApiOperation(value = "获取图形太阳二维码(返回base64图片)",  httpMethod = "GET",
            notes ="参数参考文档：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/qr-code/wxacode.getUnlimited.html")
    public R<String> createQrCode(@RequestParam String scene,
                                  @RequestParam String page,
                                  @RequestParam int width,
                                  @RequestParam boolean autoColor,
                                  @RequestParam boolean isHyaline) {
        return wxAppQrcodeUtil.createQrCode(scene, page, width, autoColor, isHyaline);
    }



//    @RequestMapping(value = "/subscribe/sendMsgTest", method = RequestMethod.POST)
//    @ApiOperation(value = "订阅消息发送", notes = "")
//    public R<String> sendMsg(@RequestParam String openId) {
//        String userName = "兮家小二";
//        String productName = "月缴费";
//        BigDecimal amount = new BigDecimal("30.00");
//        String time = "2020-10-10 00:00:00";
//        return wxAppSubscribeUtil.payMsg(openId, userName, productName, amount, time);
//    }
}