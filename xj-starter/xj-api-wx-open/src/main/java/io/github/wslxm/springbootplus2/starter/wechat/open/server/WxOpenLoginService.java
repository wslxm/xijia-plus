package io.github.wslxm.springbootplus2.starter.wechat.open.server;

import com.alibaba.fastjson.JSONObject;
import io.github.wslxm.springbootplus2.starter.wechat.open.model.vo.WxUserInfoVO;


/**
 * 微信三方登录
 *
 * @author wangsong
 * @version 1.0.0
 * @email 1720696548@qq.com
 * @date 2021/12/27 17:19
 */
public interface WxOpenLoginService {


    /**
     * 获得跳转到wx登录页的url,前台直接a连接访问
     *
     * @return java.lang.String
     * @version 1.0.0
     */
    String getWxOpenLoginUrl();


    /**
     * 通过 code 获取 AccessToken  |  获取 AccessToken 和 openId
     *
     * @param code code
     * @return com.alibaba.fastjson.JSONObject
     * @version 1.0.0
     */
    JSONObject getAccessToken(String code);


    /**
     * 根据 openId获取用户信息
     *
     * @param code code
     * @return io.github.wslxm.springbootplus2.starter.wechat.open.model.vo.WxUserInfoVO
     * @version 1.0.0
     */
    WxUserInfoVO getUserInfo(String code);
}
