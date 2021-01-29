package com.ws.ldy.modules.third.wechat.mp.util;

import com.ws.ldy.common.result.R;
import com.ws.ldy.modules.third.wechat.mp.entity.WxMpJsapiSignatureVO;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


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
