package com.ws.ldy.modules.third.wechat.app.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ws.ldy.common.result.R;
import com.ws.ldy.modules.third.wechat.app.config.WxAppProperties;
import com.ws.ldy.modules.third.wechat.app.config.WxAppUrl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class WxAppUtil {


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WxAppProperties wxAppProperties;

    /**
     *  通过code 获取用户openId
     *  <P>
     *     成功回调
     *    {
     *      "access_token":"ACCESS_TOKEN",
     *      "expires_in":7200,
     *      "refresh_token":"REFRESH_TOKEN",
     *      "openid":"OPENID",
     *      "scope":"SCOPE"
     *    }
     *    失败回调： {"errcode":40029,"errmsg":"invalid code"}
     *  </P>
     * @author wangsong
     * @param code
     * @date 2020/9/22 0022 14:22
     * @return java.lang.String
     * @version 1.0.0
     */
    public R<String> getOpenId(String code) {
        // 根据code获取openId
        String url = WxAppUrl.AuthUrl.AUTH_CODE_URL
                .replace("APPID", wxAppProperties.getAppId())
                .replace("SECRET", wxAppProperties.getSecret())
                .replace("JSCODE", code);
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        JSONObject jsonObject = JSON.parseObject(forEntity.getBody());
        // 判断是否请求成功
        Object errcode = jsonObject.get("errcode");
        if (errcode != null) {
            log.info(forEntity.getBody());
            return R.error(Integer.valueOf(errcode.toString()), jsonObject.get("errmsg").toString());
        }
        return R.success(jsonObject.get("openid").toString());
    }
}
