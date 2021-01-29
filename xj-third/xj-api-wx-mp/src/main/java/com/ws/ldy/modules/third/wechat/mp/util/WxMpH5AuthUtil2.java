package com.ws.ldy.modules.third.wechat.mp.util;//package com.ws.ldy.modules.third.wechat.mq.util;
//
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.ws.ldy.common.result.R;
//import com.ws.ldy.common.result.RType;
//import com.ws.ldy.modules.third.wechat.mq.config.WxMpProperties;
//import com.ws.ldy.modules.third.wechat.mq.config.WxMqUrl;
//import com.ws.ldy.modules.third.wechat.mq.entity.WxMpAccessTokenVO;
//import com.ws.ldy.modules.third.wechat.mq.entity.WxMpUserInfoVO;
//import lombok.SneakyThrows;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//
///**
// * 微信 H5网页授权
// * <P>
// *    获取微信用户信息(必须用户手动确认)
// * </P>
// * @author wangsong
// * @date 2020/9/22 0022 11:49
// * @return
// * @version 1.0.0
// */
//@Service
//@Slf4j
//public class WxMpH5AuthUtil {
//
//    //rpc
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Autowired
//    private WxMpProperties wxMpProperties;
//
//    /**
//     * 获取 url连接,前端获取后再微信中调用 可立即调起授权, 并触发  /weChatAuthCallback 接口,
//     * 注意：接口域名需外网可访问，并需要【网页授权获取用户基本信息】填写对应域名
//     *
//     * @author wangsong
//     * @date 2019年6月19日 下午5:55:36
//     * @param  type=1 主动授权    type=2 静默授权
//     *   <P>
//     *       appid：公众号的唯一标识
//     *       redirect_uri：授权后重定向的回调链接地址
//     *       response_type：返回类型，填写code
//     *       scope：应用授权作用域 snsapi_userinfo=弹窗点击授权(可获取用户信息)  snsapi_base=静默授权(只能获取openId)
//     *       state：非必传，重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
//     *       wechat_redirect：无论直接打开还是做页面302重定向时候，必须带此参数
//     *   </P>
//     * @return URL 地址-> 使用微信、微信开发者工具打开
//     */
//    public R<String> getAuthCodeUrl(Integer type, String callback) {
//        try {
//            callback = URLEncoder.encode(callback, "utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        String scope = null;
//        if (type == 1) {
//            scope = "snsapi_userinfo";
//        } else if (type == 2) {
//            scope = "snsapi_base";
//        }
//        // 拼接url
//        return R.success(WxMqUrl.AuthUrl.AUTH_CODE_URL
//                .replace("APPID", wxMpProperties.getAppId())
//                .replace("REDIRECT_URI", callback)
//                .replace("RESPONSE_TYPE", "code")
//                .replace("SCOPE", scope)
//                .replace("STATE", ""));
//    }
//
//
//    /**
//     *  通过code获取 accessToken, 此接口会获取用户openId + accessToken
//     *  <P>
//     *     成功回调
//     *    {
//     *      "access_token":"ACCESS_TOKEN",
//     *      "expires_in":7200,
//     *      "refresh_token":"REFRESH_TOKEN",
//     *      "openid":"OPENID",
//     *      "scope":"SCOPE"
//     *    }
//     *    失败回调： {"errcode":40029,"errmsg":"invalid code"}
//     *  </P>
//     * @author wangsong
//     * @param code
//     * @date 2020/9/22 0022 14:22
//     * @return java.lang.String
//     * @version 1.0.0
//     */
//    public R<WxMpAccessTokenVO> getAccessToken(String code) {
//        // 拼接url
//        String url = WxMqUrl.AuthUrl.AUTH_ACCESS_TOKEN_URL
//                .replace("APPID", wxMpProperties.getAppId())
//                .replace("SECRET", wxMpProperties.getSecret())
//                .replace("CODE", code);
//
//        // 发起请求
//        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
//
//        // 处理结果并返回
//        JSONObject jsonObject = JSON.parseObject(forEntity.getBody());
//        Object errcode = jsonObject.get("errcode");
//        if (errcode != null) {
//            log.info(forEntity.getBody());
//            return R.error(Integer.valueOf(errcode.toString()), jsonObject.get("errmsg").toString());
//        }
//        //
//        WxMpAccessTokenVO wxAccessTokenVO = JSON.parseObject(jsonObject.toJSONString(), WxMpAccessTokenVO.class);
//        // 返回 openId
//        return R.success(wxAccessTokenVO);
//    }
//
//
//    /**
//     * 获取用户信息
//     * @author wangsong
//     * @param code  传递code
//     * @date 2020/9/22 0022 11:40
//     * @return com.alibaba.fastjson.JSONObject
//     * @version 1.0.0
//     */
//    @SneakyThrows
//    public R<WxMpUserInfoVO> getUserInfo(String code) {
//        // 1、获取openId 和 accessToken
//        R<WxMpAccessTokenVO> wxAccessTokenVOData = this.getAccessToken(code);
//        if (!wxAccessTokenVOData.getCode().equals(RType.SYS_SUCCESS.getValue())) {
//            return R.error(wxAccessTokenVOData.getCode(), wxAccessTokenVOData.getMsg());
//        }
//        String accessToken = wxAccessTokenVOData.getData().getAccess_token();
//        String openId = wxAccessTokenVOData.getData().getOpenid();
//
//        // 2、拼接url
//        String url = WxMqUrl.AuthUrl.AUTH_USER_INFO_URL
//                .replace("ACCESS_TOKEN", accessToken)
//                .replace("OPENID", openId);
//
//        // 3、发起请求(并处理返回数据乱码问题))
//        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
//        String resJson = new String(forEntity.getBody().getBytes("ISO-8859-1"), "UTF-8");
//        JSONObject jsonObject = JSON.parseObject(resJson);
//
//        // 4、处理结果并返回 | 成功返回 { "errcode":0,"errmsg":"ok"}
//        Object errcode = jsonObject.get("errcode");
//        WxMpUserInfoVO wxUserInfoVO = null;
//        if (errcode != null) {
//            // 没有授权-返回openId
//            log.info(forEntity.getBody());
//            wxUserInfoVO = new WxMpUserInfoVO();
//            wxUserInfoVO.setOpenid(openId);
//        } else {
//            // 授权成功-返回用户信息
//            wxUserInfoVO = JSON.parseObject(jsonObject.toJSONString(), WxMpUserInfoVO.class);
//        }
//        return R.success(wxUserInfoVO);
//    }
//}