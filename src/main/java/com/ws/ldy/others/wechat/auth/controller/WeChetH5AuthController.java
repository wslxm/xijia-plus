package com.ws.ldy.others.wechat.auth.controller;


import com.ws.ldy.common.result.R;
import com.ws.ldy.others.wechat.wxbase.util.WeChetH5AuthUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
@RequestMapping("/wechat/auth")
@Api(value = "WeChetH5AuthController", tags = "v-1.2 -- WeChat --> 微信网页授权")
public class WeChetH5AuthController {

    @Autowired
    private WeChetH5AuthUtil weChetAuthService;


    @RequestMapping(value = "/getAuthCodeUrl", method = RequestMethod.GET)
    @ApiOperation(value = "1、获取授权URL, 并重定向到指定页", notes = "" +
            "callback = 回调页 如: http://yabei.520ban.com/platform/#/public, 访问该接口返回的URL会自动跳转到回调也,并携带code 参数在url上" +
            "\r\n 注意： 返回的url需在微信开发者工具访问或手机微信中打开" +
            "\r\n 注意： code 有效期5分钟" +
            "\r\n 请求该接口返回的 URL[ data=url ] 微信将调用触发 callback 参数填写的url地址并携带code参数在链接后面" +
            "\r\n type=2、以scope=snsapi_userinfo为scope发起的网页授权，是用来获取用户的基本信息的。但这种授权需要用户手动同意，并且由于用户同意过，所以无须关注，就可在授权后获取该用户的基本信息" +
            "\r\n type=1、以scope=snsapi_base为scope发起的网页授权，是用来获取进入页面的用户的openid的，并且是静默授权并自动跳转到回调页的。用户感知的就是直接进入了回调页（往往是业务页面, 静默授权,用户无感知）)")
    @ApiImplicitParam(name = "type", value = "1=弹窗用户点击授权 2=静默授权", required = true, paramType = "query", example = "1")
    public R<String> getAuthCodeUrl(@RequestParam Integer type, String callback) {
        return R.success(weChetAuthService.getAuthCodeUrl(type, callback));
    }


    @RequestMapping(value = "/getOpenId", method = RequestMethod.GET)
    @ApiOperation(value = "2、通过code 获取openId", notes = "")
    public R<String> getOpenId(@RequestParam String code) {
        return R.success(weChetAuthService.getOpenId(code));
    }
}