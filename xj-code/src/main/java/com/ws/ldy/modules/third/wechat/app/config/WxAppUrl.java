package com.ws.ldy.modules.third.wechat.app.config;

/**
 * 微信小程序
 */
public interface WxAppUrl {


    /**
     * H5网页授权需要用到的链接
     */
    interface AuthUrl {
        /**
         * 属性	    类型		   必填	说明
         * appid	string		是	小程序 appId
         * secret	string		是	小程序 appSecret
         * js_code	string		是	登录时获取的 code
         * grant_type	string	是	授权类型，此处只需填写 authorization_code
         * 返回值
         */
        String AUTH_CODE_URL = "https://api.weixin.qq.com/sns/jscode2session" +
                "?appid=APPID" +
                "&secret=SECRET" +
                "&js_code=JSCODE" +
                "&grant_type=authorization_code";

    }
}
