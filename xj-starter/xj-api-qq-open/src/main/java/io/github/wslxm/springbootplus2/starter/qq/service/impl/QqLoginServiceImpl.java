package io.github.wslxm.springbootplus2.starter.qq.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Enums;
import io.github.wslxm.springbootplus2.core.config.error.ErrorException;
import io.github.wslxm.springbootplus2.core.enums.Base;
import io.github.wslxm.springbootplus2.core.result.R;
import io.github.wslxm.springbootplus2.starter.qq.service.QqLoginService;
import io.github.wslxm.springbootplus2.starter.qq.config.ConstantsProperties;
import io.github.wslxm.springbootplus2.starter.qq.model.entity.QqUserInfo;
import io.github.wslxm.springbootplus2.starter.qq.model.vo.QqLoginVO;
import io.github.wslxm.springbootplus2.starter.qq.util.HttpClientUtils;
import io.github.wslxm.springbootplus2.starter.qq.util.UrlEncodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author wangsong
 */
@Service
@Slf4j
public class QqLoginServiceImpl implements QqLoginService {

    /**
     * QQ ：读取Appid相关配置信息静态类
     */
    @Autowired
    private ConstantsProperties constantsProperties;

    @Override
    public String getQqLoginUrl() {
        // 拼接url
        return "https://graph.qq.com/oauth2.0/authorize?" +
                "response_type=code" +
                "&redirect_uri=" + UrlEncodeUtil.getUrlEncoderString(constantsProperties.getRedirectUrl()) +
                "&state=ok" +
                "&client_id=" + constantsProperties.getAppId();
    }

    @Override
    public String getAccessToken(String code) {
        String url = "https://graph.qq.com/oauth2.0/token?grant_type=authorization_code" +
                "&client_id=" + constantsProperties.getAppId() +
                "&client_secret=" + constantsProperties.getAppSecret() +
                "&redirect_uri=" + constantsProperties.getRedirectUrl() +
                "&code=" + code;
        String result = null;
        try {
            // 获得token 正常返回数据：access_token=41A5D426CF372A6A32C1718AD13833FB&expires_in=7776000&refresh_token
            // =8D912F55416FC28F7B2FBBFE1BA91CD5
            result = HttpClientUtils.get(url, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("获取token：" + result);
        // 获取 accessToken
        String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(result, "&");
        assert items != null;
        return StringUtils.substringAfterLast(items[0], "=");
    }


    @Override
    public String getOpenId(String accessToken) {
        StringBuilder url = new StringBuilder("https://graph.qq.com/oauth2.0/me?access_token=" + accessToken);
        String result = null;
        try {
            // 正常返回数据：callback( {"client_id":"101954125","openid":"C97AC20B3C068A016E7181F4FF23284F"} )
            result = HttpClientUtils.get(url.toString(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("获取openId：" + result);
        return StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
    }


    @Override
    public QqLoginVO getUserInfo(String code) {
        // 获取token和openId
        String accessToken = getAccessToken(code);
        String openId = getOpenId(accessToken);
        //拼接url
        String url = "https://graph.qq.com/user/get_user_info" +
                "?access_token=" + accessToken +
                "&oauth_consumer_key=" + constantsProperties.getAppId() +
                "&openid=" + openId;
        // 获取qq相关数据
        String result = null;
        try {
            // 正常直接返回用户数据 json
            result = HttpClientUtils.get(url, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("获取用户信息：" + result);
        QqUserInfo qqUserInfo = JSON.parseObject(result, QqUserInfo.class);
        QqLoginVO vo = new QqLoginVO();
        if (qqUserInfo != null) {
            if (qqUserInfo.getRet() != null && qqUserInfo.getRet() == -1) {
                throw new ErrorException(qqUserInfo.getRet(), qqUserInfo.getMsg());
            }
            // 性别
            int genderCode = 0;
            if (StringUtils.isNotBlank(qqUserInfo.getGender())) {
                if (qqUserInfo.getGender().equals(Base.Gender.V1.getDesc())) {
                    genderCode = 1;
                } else if (qqUserInfo.getGender().equals(Base.Gender.V2.getDesc())) {
                    genderCode = 2;
                }
            }
            vo.setGenderCode(genderCode);
            if (StringUtils.isNotBlank(qqUserInfo.getYear())) {
                vo.setBirthday(qqUserInfo.getYear() + "-01-01");
            }
            vo.setQqOpenId(openId);
            vo.setNickname(qqUserInfo.getNickname());
            vo.setHeadPic(qqUserInfo.getFigureurlQq());
        }
        return vo;
    }
}
