package com.ws.ldy.others.wechat.wxbase.constant;

/**
 * 微信缓存信息
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/9/9 0009 17:12 
 * @version 1.0.0
 */
public class WxChatCache {

    /**
     * 微信 accessToken缓存
     */
    public static class AccessToken {
        public static String token = null;   // accessToken
        public static Long expiration = 0L;  // accessToken 过期时间(获取的token 默认有效期2小时）
    }
}
