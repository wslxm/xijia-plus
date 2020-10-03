package com.ws.ldy.others.wechat.wxbase.service;

import com.ws.ldy.others.wechat.wxbase.util.WeChetAccessTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * 微信通用service层
 */
public class BaseWeChat {
    // rpc
    @Autowired
    protected RestTemplate restTemplate;

    // 获取token
    @Autowired
    protected WeChetAccessTokenUtil weChetAccessToken;
}
