package com.ws.ldy.modules.third.wechat.app.config;

/**
 * 微信小程序
 */
public interface WxAppUrl {


    /**
     * 登录请求接口
     */
    interface AuthUrl {


        /**
         * 小程序登录接口
         * <P>
         *   APPID	string		是	小程序 appId
         *   SECRET	string		是	小程序 appSecret
         *   JSCODE	string		是	登录时获取的 code
         *   grant_type	        固定值
         * </P>
         */
        String AUTH_CODE_URL = "https://api.weixin.qq.com/sns/jscode2session" +
                "?appid=APPID" +
                "&secret=SECRET" +
                "&js_code=JSCODE" +
                "&grant_type=authorization_code";
    }


    /**
     * 发送消息
     */
    interface SubscribeUrl {

        /**
         * 获取小程序 ACCESS_TOKEN
         * <P>
         *  APPID       小程序 appId
         *  APPSECRET   小程序 appSecret
         *  grant_type  固定值
         * </P>
         */
        String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token" +
                "?grant_type=client_credential" +
                "&appid=APPID" +
                "&secret=APPSECRET";


        /**
         * 微信订阅消息推送
         * <P>
         *   ACCESS_TOKEN	 小程序 appId
         * </P>
         * 返回值
         */
        String SUBSCRIBE_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send" +
                "?access_token=ACCESS_TOKEN";
    }
}
