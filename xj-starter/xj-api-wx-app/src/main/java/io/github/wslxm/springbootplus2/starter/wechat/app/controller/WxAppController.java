package io.github.wslxm.springbootplus2.starter.wechat.app.controller;


import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.R;
import io.github.wslxm.springbootplus2.starter.wechat.app.model.WxMaJscode2SessionResultVO;
import io.github.wslxm.springbootplus2.starter.wechat.app.model.WxMaPhoneNumberInfoVO;
import io.github.wslxm.springbootplus2.starter.wechat.app.server.WxAppService;
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
 * @version 1.0.1
 */
@RestController
@RequestMapping(BaseConstant.Uri.API_OPEN +"/wx/app")
@Api(value = "WxAppController", tags = "WX  -->  APP小程序")
public class WxAppController {

    @Autowired
    private WxAppService wxAppService;


    @RequestMapping(value = "/auth/login", method = RequestMethod.GET)
    @ApiOperation(value = "小程序登录, 通过code 获取openId以及sessionKey",
            notes = "详见微信文档下的 auth.code2Session 接口说明：https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/login.html")
    public R<WxMaJscode2SessionResultVO> login(@RequestParam String code) {
        return wxAppService.login(code);
    }


    @RequestMapping(value = "/auth/phone", method = RequestMethod.GET)
    @ApiOperation(value = "获取微信绑定的手机号",
            notes = "1、先登录获取sessionKey 存放到本地\r\n" +
                    "2、小程序端使用 button 组件 open-type 的值设置为 getPhoneNumber，让用户点击并同意获取参数\r\n" +
                    "3、将获取到的参数 encryptedData + iv 以及sessionKey传递到本接口,返回微信绑定的手机号" +
                    "详见微信文档：https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/getPhoneNumber.html")
    public R<WxMaPhoneNumberInfoVO> phone(@RequestParam String sessionKey,
                                          @RequestParam String encryptedData,
                                          @RequestParam String iv) {
        return wxAppService.phone(sessionKey, encryptedData, iv);
    }


    @RequestMapping(value = "/qrcode/create", method = RequestMethod.GET)
    @ApiOperation(value = "获取图形太阳二维码(返回base64图片)",
            notes = "详见微信文档：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/qr-code/wxacode.getUnlimited.html")
    public R<String> createQrCode(@RequestParam String scene,
                                  @RequestParam String page,
                                  @RequestParam Integer width,
                                  @RequestParam Boolean autoColor,
                                  @RequestParam Boolean isHyaline) {
        return wxAppService.createQrCode(scene, page, width, autoColor, isHyaline);
    }


    @RequestMapping(value = "/subscribe/sendMsgTest", method = RequestMethod.POST)
    @ApiOperation(value = "订阅消息发送测试" +
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