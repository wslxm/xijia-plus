package io.github.wslxm.springbootplus2.starter.wechat.app.server;

import io.github.wslxm.springbootplus2.core.result.R;
import io.github.wslxm.springbootplus2.starter.wechat.app.model.WxMaJscode2SessionResultVO;
import io.github.wslxm.springbootplus2.starter.wechat.app.model.WxMaPhoneNumberInfoVO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 *  @author wangsong
 */
public interface WxAppService {


    /**
     * 获取太阳码
     *
     * @param scene     scene
     * @param page      page
     * @param width     width
     * @param autoColor autoColor
     * @param isHyaline isHyaline
     * @return io.github.wslxm.springbootplus2.core.result.R<java.lang.String>
     * @version 1.0.0
     */
    R<String> createQrCode(@RequestParam String scene,
                           @RequestParam String page,
                           @RequestParam Integer width,
                           @RequestParam Boolean autoColor,
                           @RequestParam Boolean isHyaline);


    /**
     * 发送订阅消息
     *
     * @param openId     接收人openId
     * @param templateId 模板Id
     * @param content    发送内容（key,value 公司）
     * @return io.github.wslxm.springbootplus2.core.result.R<java.lang.String>
     * @version 1.0.0
     */
    R<String> sendMsg(String openId, String templateId, Map<String, String> content);


    /**
     * 通过code 获取用户openId
     *
     * @param code
     * @return java.lang.String
     * @author wangsong
     * @date 2020/9/22 0022 14:22
     * @version 1.0.1
     */
    R<WxMaJscode2SessionResultVO> login(String code);


    /**
     * 获取用户手机号
     *
     * @param sessionKey    sessionKey
     * @param encryptedData encryptedData
     * @param iv            iv
     * @return io.github.wslxm.springbootplus2.core.result.R<io.github.wslxm.springbootplus2.starter.wechat.app.model.WxMaPhoneNumberInfoVO>
     * @version 1.0.0
     */
    R<WxMaPhoneNumberInfoVO> phone(String sessionKey, String encryptedData, String iv);


}
