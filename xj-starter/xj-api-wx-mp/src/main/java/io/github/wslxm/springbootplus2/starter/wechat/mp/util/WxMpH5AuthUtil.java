package io.github.wslxm.springbootplus2.starter.wechat.mp.util;


import io.github.wslxm.springbootplus2.core.result.R;
import io.github.wslxm.springbootplus2.starter.wechat.mp.entity.WxMpAccessTokenVO;
import io.github.wslxm.springbootplus2.starter.wechat.mp.entity.WxMpUserInfoVO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 微信 H5网页授权
 * <P>
 *    获取微信用户信息(必须用户手动确认)
 * </P>
 * @author wangsong
 * @date 2020/9/22 0022 11:49
 * @return
 * @version 1.0.0
 */
@Service
@Slf4j
public class WxMpH5AuthUtil {


    @Autowired
    private WxMpService wxMpService;


    /**
     * 获取 url连接,前端获取后再微信中调用 可立即调起授权, 并触发  /weChatAuthCallback 接口,
     * 注意：接口域名需外网可访问，并需要【网页授权获取用户基本信息】填写对应域名
     * @return
     */
    @SneakyThrows
    public R<String> getAuthCodeUrl(Integer type, String callback) {
        String scope = null;
        if (type == 1) {
            scope = WxConsts.OAuth2Scope.SNSAPI_USERINFO;
        } else if (type == 2) {
            scope = WxConsts.OAuth2Scope.SNSAPI_BASE;
        }
        String url = wxMpService.getOAuth2Service().buildAuthorizationUrl(callback, scope, null);
        return R.success(url);
    }


    /**
     *  通过code获取 accessToken, 此接口会获取用户openId + accessToken
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
    public R<WxMpAccessTokenVO> getAccessToken(String code) {
        try {
            WxOAuth2AccessToken accessToken = wxMpService.getOAuth2Service().getAccessToken(code);
            WxMpAccessTokenVO wxAccessTokenVO = new WxMpAccessTokenVO();
            wxAccessTokenVO.setAccess_token(accessToken.getAccessToken());
            wxAccessTokenVO.setExpires_in(accessToken.getExpiresIn() + "");
            wxAccessTokenVO.setRefresh_token(accessToken.getRefreshToken());
            wxAccessTokenVO.setOpenid(accessToken.getOpenId());
            wxAccessTokenVO.setScope(accessToken.getScope());
            return R.success(wxAccessTokenVO);
        } catch (WxErrorException e) {
            log.debug(e.toString());
            return R.error(e.getError().getErrorCode(), e.getError().getErrorMsg());
        }
    }


    /**
     * 获取用户信息
     * @author wangsong
     * @param code  传递code
     * @date 2020/9/22 0022 11:40
     * @return com.alibaba.fastjson.JSONObject
     * @version 1.0.0
     */
    public R<WxMpUserInfoVO> getUserInfo(String code) {
        WxOAuth2AccessToken accessToken = null;
        //
        try {
            accessToken = wxMpService.getOAuth2Service().getAccessToken(code);
        } catch (WxErrorException e) {
            log.debug(e.toString());
            return R.error(e.getError().getErrorCode(), e.getError().getErrorMsg());
        }
        WxOAuth2UserInfo userInfo = null;
        try {
            userInfo = wxMpService.getOAuth2Service().getUserInfo(accessToken, null);
        } catch (WxErrorException e) {
            log.debug(e.toString());
        }
        WxMpUserInfoVO userInfoVO = new WxMpUserInfoVO();
        userInfoVO.setOpenid(accessToken.getOpenId());
        if (userInfo != null) {
            userInfoVO.setNickname(userInfo.getNickname());
            userInfoVO.setSex(userInfo.getSex() + "");
            userInfoVO.setProvince(userInfo.getProvince());
            userInfoVO.setCity(userInfo.getCity());
            userInfoVO.setCountry(userInfo.getCountry());
            userInfoVO.setHeadimgurl(userInfo.getHeadImgUrl());
            userInfoVO.setPrivilege(userInfo.getProvince());
            userInfoVO.setUnionid(userInfo.getUnionId());
        }
        return R.success(userInfoVO);
    }
}

