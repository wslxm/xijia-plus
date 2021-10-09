package io.github.wslxm.springbootplus2.starter.wechat.mp.util;

import io.github.wslxm.springbootplus2.core.result.R;
import io.github.wslxm.springbootplus2.starter.wechat.mp.entity.WxMpJsapiSignatureVO;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * h5 使用 jssdk 必备参数获取
 * @author wangsong
 * @date 2021/3/10 0010 11:50
 * @return
 * @version 1.0.0
 */
@Component
@Slf4j
public class WxMpJsApUtil {


    @Autowired
    private WxMpService wxMpService;


    public R<WxMpJsapiSignatureVO> createJsapiSignature(String url) throws WxErrorException {
        // 1、获取Ticket
        wxMpService.getJsapiTicket();
        // 2、获取jssdk签名参数
        WxJsapiSignature jsapiSignature = wxMpService.createJsapiSignature(url);
        WxMpJsapiSignatureVO vo = new WxMpJsapiSignatureVO();
        vo.setAppId(jsapiSignature.getAppId());
        vo.setNonceStr(jsapiSignature.getNonceStr());
        vo.setTimestamp(jsapiSignature.getTimestamp());
        vo.setUrl(jsapiSignature.getUrl());
        vo.setSignature(jsapiSignature.getSignature());
        return R.success(vo);
    }
}
