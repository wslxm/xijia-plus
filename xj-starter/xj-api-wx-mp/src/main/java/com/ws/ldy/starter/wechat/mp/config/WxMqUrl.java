//package com.ws.ldy.modules.third.wechat.mq.config;
//
//
///**
// * 微信公众URL接口地址
// * @author wangsong
// * @date 2020/12/26 0026 17:42
// * @return
// * @version 1.0.0
// */
//public interface WxMqUrl {
//
//
//    /**
//     * H5网页授权需要用到的链接
//     */
//    interface AuthUrl {
//        /**
//         * 1、微信授权地址 1 （获取code地址） -返回的code有效期为5分钟
//         *  参数1: APPID：公众号的唯一标识
//         *  参数2: REDIRECT_URI： 授权后重定向的回调链接地址
//         *  参数3: RESPONSE_TYPE：返回类型，填写code
//         *  参数4: SCOPE：应用授权作用域，snsapi_base=静默登录（只能获取openId），snsapi_userinfo=用户点击授权获取（可获取用户基本信息）
//         *  参数5: STATE：非必传，自定义业务参数--重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
//         *  wechat_redirect：无论直接打开还是做页面302重定向时候，必须带此参数
//         */
//        String AUTH_CODE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize" +
//                "?appid=APPID" +
//                "&redirect_uri=REDIRECT_URI" +
//                "&response_type=RESPONSE_TYPE" +
//                "&scope=SCOPE" +
//                "&state=STATE" +
//                "#wechat_redirect";
//
//
//        /**
//         * 2、微信授权地址 2（通过code获取access_token） -access_token有效期7200秒 = 2小时
//         * 参数1：APPID ： 公众号的唯一标识
//         * 参数2：SECRET： 公众号的 appsecret
//         * 参数3：CODE  ： 填写第一步获取的code参数
//         * grant_type  ： 填写为authorization_code
//         */
//        String AUTH_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token" +
//                "?appid=APPID" +
//                "&secret=SECRET" +
//                "&code=CODE" +
//                "&grant_type=authorization_code";
//
//
//        /**
//         * 3、获取用户信息
//         * 参数1： ACCESS_TOKEN
//         * 参数2： OPENID
//         */
//        String AUTH_USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
//
//
//        /**
//         * 4、刷新 access_token 有效期30天
//         * 参数1：APPID  公众号的唯一标识
//         * 参数2：SECRET 公众号的 appsecret
//         * 参数3：CODE   填写第一步获取的code参数
//         * grant_type  填写为authorization_code
//         */
//        String AUTH_REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token" +
//                "?appid=appid" +
//                "&grant_type=refresh_token" +
//                "&refresh_token=refresh_token";
//    }
//}
//
