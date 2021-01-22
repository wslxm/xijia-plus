package com.ws.ldy.modules.third.wechat.mq.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ws.ldy.common.result.R;
import com.ws.ldy.modules.third.wechat.mq.config.WxMqProperties;
import com.ws.ldy.modules.third.wechat.mq.config.WxMqUrl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


/**
 * 获取微信的 Token 并缓存 (调用微信相关接口需要Token信息, 注意此 access_token并非网页授权的access_token, 此token 仅用于调用微信相关接口)
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/9/30 0030 17:33
 * @version 1.0.0
 */
@Component
@Slf4j
public class WeChetAccessTokenUtil {


    //rpc
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WxMqProperties wxMqProperties;

    /**
     * accessToken
     */
    private static String token = null;
    /**
     *  accessToken 过期时间(获取的token 默认有效期2小时）
     */
    private static Long expiration = 0L;


    /**
     * 获取 accessToken （token有效期为2小时, 设置缓存, 过期并重新获取 accessToken）
     * <p>
     * 微信请求正确返回 {\"access_token\":\"37_B7bSN7N0VoqOVhf5rOk7NOHY6aMoxvE15VxNIcnD3f2kXvZkc0HOU-9rhfGZyWAoYkVfLrPzxTMhdcf86kgQeabfWSV-DH0hUYD8YMBF9vcbASzwRlEE3zJbKW2PuHJIl5Nu4BLouY4rUSFwTCBbAHAIRQ\",\"expires_in\":7200}
     * 微信请求错误返回 {"errcode":40013,"errmsg":"invalid appid"}
     * </p>
     * <P>
     *     流程：
     *     1、 判断token是否过期
     *     2、 获取 url 并拼接参数 APPID + APPSECRET
     *     3、 发起请求
     *     4、 判断请求是否成功
     *     5、 缓存accessToken + accessToken过期时间到jvm 内存中
     * </P>
     * @author wangsong
     * @date 2019年6月19日 下午5:55:11
     * @return
     */
    public R<String> getToken() {
        if (expiration <= System.currentTimeMillis()) {
            String url = WxMqUrl.TemplateUrl.ACCESS_TOKEN_URL
                    .replace("APPID", wxMqProperties.getAppId())
                    .replace("APPSECRET", wxMqProperties.getSecret());
            //http
            ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);//222.211.239.190
            // 注意公众号平台 开发--> 基本配置 --> Ip白名单 -中设置当前外网Ip地址为白名单
            log.info("获取access_token：" + forEntity.getBody());
            JSONObject jsonObject = JSON.parseObject(forEntity.getBody());
            Object errcode = jsonObject.get("errcode");
            if (errcode != null && "40013".equals(errcode.toString())) {
                return R.error(40013, "获取公众号openId失败");
            }
            // 设置为1小时59分钟有效期，防止空指针异常
            token = jsonObject.get("access_token").toString();
            expiration = ((Integer.parseInt(jsonObject.get("expires_in").toString()) - 1) * 1000) + System.currentTimeMillis();
        }
        return R.success(token);
    }
}
