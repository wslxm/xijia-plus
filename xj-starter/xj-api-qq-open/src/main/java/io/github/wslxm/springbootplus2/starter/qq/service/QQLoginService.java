package io.github.wslxm.springbootplus2.starter.qq.service;

import io.github.wslxm.springbootplus2.starter.qq.model.vo.QQLoginVO;

/**
 * qq登录
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2021/6/5 0005 22:30
 * @version 1.0.1
 */
public interface QQLoginService {


    /**
     * 获得跳转到qq登录页的url,前台直接a连接访问
     * @throws Exception
     */
    public String getQQLoginUrl();


    /**
     * 通过 code 获取 AccessToken
     */
    public String getAccessToken(String code);


    /**
     * 通过 AccessToken 获取openId
     * @param code
     * @return
     */
    public String getOpenId(String code);



    /**
     * 根据 openId获取用户信息
     */
    public QQLoginVO getUserInfo(String code);

}
