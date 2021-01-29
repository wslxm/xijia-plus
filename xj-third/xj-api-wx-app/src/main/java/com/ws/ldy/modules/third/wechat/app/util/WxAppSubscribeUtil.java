package com.ws.ldy.modules.third.wechat.app.util;

import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.modules.third.wechat.app.config.WxAppProperties;
import com.ws.ldy.modules.third.wechat.app.config.WxAppUrl;
import com.ws.ldy.modules.third.wechat.app.entity.WxAppMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 发送微信小程序订阅消息
 * <P>
 *    微信文档地址： https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/subscribe-message.html
 * </P>
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2021/1/18 0018 15:02 
 * @version 1.0.0
 */
@SuppressWarnings("all")
@Service
@Slf4j
public class WxAppSubscribeUtil {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WxAppProperties wxAppProperties;

    @Autowired
    private WxAppAccessTokenUtil wxAppAccessTokenUtil;


    /**
     *通过code 获取用户openId
     * @author wangsong
     * @param openId
     * @date 2020/9/22 0022 14:22
     * @return java.lang.String
     * @version 1.0.0
     */
    public R<String> sendMsg(String openId, String templateId, Map<String, WxAppMsg> data) {
        R<String> accessTokenData = wxAppAccessTokenUtil.getToken();
        if (!accessTokenData.getCode().equals(RType.SYS_SUCCESS.getValue())) {
            return R.error(accessTokenData.getCode(), accessTokenData.getMsg());
        }
        String accessToken = accessTokenData.getData();
        String url = WxAppUrl.SubscribeUrl.SUBSCRIBE_SEND_URL.replace("ACCESS_TOKEN", accessToken);
        //拼接base参数
        Map<String, Object> sendBody = new HashMap<>();
        sendBody.put("access_token", accessToken);    // * accessToken(公众号或小程序accessToken)
        sendBody.put("touser", openId);               // * openId
        sendBody.put("template_id", templateId);      // * 所需下发的订阅模板id
        sendBody.put("page", null);                   // 非必传-点击跳转，点击跳转小程序指定页面
        sendBody.put("miniprogram_state", null);      // 非必传-跳转类型， 跳转类型：developer为开发版；trial为体验版；formal为正式版
        sendBody.put("lang", null);                   // 非必传-进入小程序查看”的语言类型，支持zh_CN(简体中文)、en_US(英文)、zh_HK(繁体中文)、zh_TW(繁体中文)，默认为zh_CN
        sendBody.put("data", data);                   // * 模板内容，格式形如 { "key1": { "value": any }, "key2": { "value": any } }
        ResponseEntity<String> forEntity = restTemplate.postForEntity(url, sendBody, String.class);
        log.info("发送订阅消息 openid:{}  结果：{}", forEntity.getBody());
        return R.success(forEntity.getBody());
    }



}
