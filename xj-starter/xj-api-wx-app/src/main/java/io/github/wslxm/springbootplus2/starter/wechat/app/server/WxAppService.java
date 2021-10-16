package io.github.wslxm.springbootplus2.starter.wechat.app.server;

import io.github.wslxm.springbootplus2.core.result.R;
import io.github.wslxm.springbootplus2.starter.wechat.app.model.WxMaJscode2SessionResultVO;
import io.github.wslxm.springbootplus2.starter.wechat.app.model.WxMaPhoneNumberInfoVO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


public interface WxAppService {


    /**
     * 获取太阳码
     * @author wangsong
     * @date 2020/9/22 0022 14:22
     * @return java.lang.String
     * @version 1.0.1
     */
    public R<String> createQrCode(@RequestParam String scene,
                                  @RequestParam String page,
                                  @RequestParam Integer width,
                                  @RequestParam Boolean autoColor,
                                  @RequestParam Boolean isHyaline);

    /**
     * 发送订阅消息
     * @author wangsong
     * @param openId 接收人openId
     * @param openId 模板Id
     * @param content 发送内容（key,value 公司）
     * @date 2020/9/22 0022 14:22
     * @return java.lang.String
     * @version 1.0.1
     */
    public R<String> sendMsg(String openId, String templateId, Map<String, String> content);


    /**
     * 通过code 获取用户openId
     * @author wangsong
     * @param code
     * @date 2020/9/22 0022 14:22
     * @return java.lang.String
     * @version 1.0.1
     */
    public R<WxMaJscode2SessionResultVO> login(String code);


    /**
     * 获取用户手机号
     * @author wangsong
     * @date 2020/9/22 0022 14:22
     * @return java.lang.String
     * @version 1.0.1
     */
    public R<WxMaPhoneNumberInfoVO> phone(String sessionKey, String encryptedData, String iv);


}
