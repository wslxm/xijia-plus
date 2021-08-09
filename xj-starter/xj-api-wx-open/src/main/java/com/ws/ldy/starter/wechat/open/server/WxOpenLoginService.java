package com.ws.ldy.starter.wechat.open.server;

import com.alibaba.fastjson.JSONObject;
import com.ws.ldy.starter.wechat.open.model.vo.WxUserInfoVO;


/**
 * 微信三方登录
 *
 * @auther 王杉文
 */
public interface WxOpenLoginService {

    /**
     * 获得跳转到wx登录页的url,前台直接a连接访问
     * @throws Exception
     */
    String getWxOpenLoginUrl();


    /**
     * 通过 code 获取 AccessToken  |  获取 AccessToken 和 openId
     */
    JSONObject getAccessToken(String code);


    /**
     * 根据 openId获取用户信息
     */
    WxUserInfoVO getUserInfo(String code);
}
