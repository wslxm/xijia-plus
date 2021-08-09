package com.ws.ldy.starter.wechat.open.server.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ws.ldy.core.config.error.ErrorException;
import com.ws.ldy.starter.wechat.open.config.WxOpenProperties;
import com.ws.ldy.starter.wechat.open.model.vo.WxUserInfoVO;
import com.ws.ldy.starter.wechat.open.server.WxOpenLoginService;
import com.ws.ldy.starter.wechat.open.util.URLEncodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;

/**
 * 微信三方登录
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2021/7/27 0027 19:51
 * @version 1.0.0
 */
@Slf4j
@Component
public class WxOpenLoginServiceImpl implements WxOpenLoginService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WxOpenProperties wxOpenProperties;


    @Override
    public String getWxOpenLoginUrl() {
        return "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=" + wxOpenProperties.getAppId() +
                "&redirect_uri=" + URLEncodeUtil.getURLEncoderString(wxOpenProperties.getCallbackUrl()) +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=0" +
                "#wechat_redirect";
    }


    /**
     * 获取 AccessToken 和 openId
     * <P>
     *   返回参数示例：
     *   {
     *     "access_token":"ACCESS_TOKEN",
     *     "expires_in":7200,
     *     "refresh_token":"REFRESH_TOKEN",
     *     "openid":"OPENID",
     *     "scope":"SCOPE",
     *     "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL"
     *   }
     * </P>
     * @author wangsong
     * @param code
     * @date 2021/7/8 0008 14:25
     * @return com.alibaba.fastjson.JSONObject
     * @version 1.0.0
     */
    @Override
    public JSONObject getAccessToken(String code) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=" + wxOpenProperties.getAppId() +
                "&secret=" + wxOpenProperties.getAppSecret() +
                "&code=" + code +
                "&grant_type=authorization_code";
        String forObject = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = JSON.parseObject(forObject, JSONObject.class);
        if (jsonObject.get("errcode") != null) {
            throw new ErrorException(Integer.parseInt(jsonObject.get("errcode").toString()), jsonObject.get("errmsg").toString());
        }
        return jsonObject;
    }


    @Override
    public WxUserInfoVO getUserInfo(String code) {
        JSONObject accessTokens = getAccessToken(code);
        String openid = accessTokens.get("openid").toString();
        String accessToken = accessTokens.get("access_token").toString();
        String url = "https://api.weixin.qq.com/sns/userinfo" +
                "?access_token=" + accessToken +
                "&openid=" + openid;
        String forObject = restTemplate.getForObject(url, String.class);
        try {
            // 处理中文乱码
            forObject = new String(forObject.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = JSON.parseObject(forObject, JSONObject.class);
        if (jsonObject.get("errcode") != null) {
            throw new ErrorException(Integer.parseInt(jsonObject.get("errcode").toString()), jsonObject.get("errmsg").toString());
        }
        return JSON.parseObject(forObject, WxUserInfoVO.class);
    }
}
