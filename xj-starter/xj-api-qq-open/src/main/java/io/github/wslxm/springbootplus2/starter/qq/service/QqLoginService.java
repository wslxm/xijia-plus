package io.github.wslxm.springbootplus2.starter.qq.service;

import io.github.wslxm.springbootplus2.starter.qq.model.vo.QqLoginVO;

/**
 * qq登录
 *
 * @author wangsong
 * @version 1.0.1
 * @mail 1720696548@qq.com
 * @date 2021/6/5 0005 22:30
 */
public interface QqLoginService {


    /**
     * 获得跳转到qq登录页的url,前台直接a连接访问
     *
     * @return java.lang.String
     * @version 1.0.0
     */
    String getQqLoginUrl();


    /**
     * 通过 code 获取 AccessToken
     *
     * @param code code
     * @return java.lang.String
     * @version 1.0.0
     */
    String getAccessToken(String code);


    /**
     * 通过 AccessToken 获取openId
     *
     * @param code code
     * @return java.lang.String
     * @version 1.0.0
     */
    String getOpenId(String code);


    /**
     * 根据 openId获取用户信息
     *
     * @param code code
     * @return io.github.wslxm.springbootplus2.starter.qq.model.vo.QqLoginVO
     * @version 1.0.0
     */
    QqLoginVO getUserInfo(String code);

}
