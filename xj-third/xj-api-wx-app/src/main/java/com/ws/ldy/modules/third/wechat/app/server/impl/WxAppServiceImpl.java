package com.ws.ldy.modules.third.wechat.app.server.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaMsgServiceImpl;
import cn.binarywang.wx.miniapp.bean.WxMaCodeLineColor;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.modules.third.wechat.app.server.WxAppService;
import com.ws.ldy.modules.third.wechat.app.util.Base64ImgUtils;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class WxAppServiceImpl  implements WxAppService {


    @Autowired
    private WxMaService wxMaService;

    /**
     * 获取太阳码
     * @author wangsong
     * @date 2020/9/22 0022 14:22
     * @return java.lang.String
     * @version 1.0.0
     */
    @Override
    public R<String> createQrCode(@RequestParam String scene,
                                  @RequestParam String page,
                                  @RequestParam Integer width,
                                  @RequestParam Boolean autoColor,
                                  @RequestParam Boolean isHyaline) {

        try {
            WxMaCodeLineColor wxMaCodeLineColor = new WxMaCodeLineColor("0", "0", "0");
            File file = wxMaService.getQrcodeService().createWxaCodeUnlimit(scene, page, width, autoColor, wxMaCodeLineColor, isHyaline);
            // 返回base64
            return R.success(Base64ImgUtils.file2Base64(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 发送订阅消息
     * @author wangsong
     * @param openId 接收人openId
     * @param openId 模板Id
     * @param content 发送内容（key,value 公司）
     * @date 2020/9/22 0022 14:22
     * @return java.lang.String
     * @version 1.0.0
     */
    @Override
    public R<String> sendMsg(String openId, String templateId, Map<String, String> content) {
        //组装消息主体
        WxMaSubscribeMessage subscribeMessage = new WxMaSubscribeMessage();
        subscribeMessage.setTemplateId(templateId);
        subscribeMessage.setToUser(openId);
        // 组装消息内容, 最后的格式如: { "key1": { "value": any }, "key2": { "value": any } }
        List<WxMaSubscribeMessage.Data> msgContent = new ArrayList<>();
        for (String key : content.keySet()) {
            WxMaSubscribeMessage.Data msgElement = new WxMaSubscribeMessage.Data();
            msgElement.setName(key);
            msgElement.setValue(content.get(key));
            msgContent.add(msgElement);
        }
        subscribeMessage.setData(msgContent);
        // 非必传-点击跳转，点击跳转小程序指定页面
        subscribeMessage.setPage(null);
        WxMaMsgServiceImpl msg = new WxMaMsgServiceImpl(wxMaService);
        try {
            msg.sendSubscribeMsg(subscribeMessage);
            return R.success("ok");
        } catch (WxErrorException e) {
            e.printStackTrace();
            return R.success("error");
        }

    }


    /**
     * 通过code 获取用户openId
     * @author wangsong
     * @param code
     * @date 2020/9/22 0022 14:22
     * @return java.lang.String
     * @version 1.0.0
     */
    @Override
    public R<String> getOpenId(String code) {
        try {
            WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(code);
            return R.success(session.getOpenid());
        } catch (WxErrorException e) {
            e.printStackTrace();
            throw new ErrorException(RType.WX_APP_ERROR.getValue(), e.getError().getErrorCode() + ":" + e.getError().getErrorMsg());
        }
    }
}
