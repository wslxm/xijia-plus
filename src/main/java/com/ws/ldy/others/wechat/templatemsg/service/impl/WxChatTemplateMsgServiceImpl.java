package com.ws.ldy.others.wechat.templatemsg.service.impl;

import com.ws.ldy.others.wechat.templatemsg.entity.WeChatTemplateMsg;
import com.ws.ldy.others.wechat.templatemsg.service.WxChatTemplateMsgService;
import com.ws.ldy.others.wechat.wxbase.util.TemplateMsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信公众消息模板 --> 数据处理后向微信公众号推送消息
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/9/9 0009 13:44
 * @version 1.0.0
 */
@Service
public class WxChatTemplateMsgServiceImpl implements WxChatTemplateMsgService {


    @Autowired
    private TemplateMsgUtil templateMsgUtil;


    /**
     * 发送模板消息通知（测试模板）
     * @param openId 微信用户的openId
     * @param content 发送的内容
     */
    @Override
    public void sendTest(String openId, String content) {
        // 模板Id
        String templateId = "P6llNez3CAcxEYzhQ_MDdFJGUifXfey15ZIX7MKiB3k";
        // 模板参数
        Map<String, WeChatTemplateMsg> sendMag = new HashMap<String, WeChatTemplateMsg>();
        sendMag.put("MSG", new WeChatTemplateMsg(content));
        //sendMag.put("MSG", new WeChatTemplateMsg(content,"#173177"));
        // 发送
        templateMsgUtil.send(openId, templateId, sendMag);
    }


    /**
     * 牙贝医生通知（测试模板）
     * @param openId
     * @param
     */
    @Override
    public void sendYbDoctor(String openId,
                             String patient,
                             String logState,
                             String afterState,
                             String name,
                             String time,
                             String remarks) {
        //        患者信息:  {{ patient.DATA}}
        //        当前操作:  {{ logState.DATA}}
        //        当前状态:  {{ state.DATA}}
        //        操作人:     {{ name.DATA}}
        //        操作时间:   {{ time.DATA}}
        //        备注:      {{ remarks.DATA}}
        // 模板Id
        String templateId = "ob5bgAqe2iKwOp0cRgAAnDKk8Vv0K5XuU9JqiG2vpV0";
        // 模板参数
        Map<String, WeChatTemplateMsg> sendMag = new HashMap<String, WeChatTemplateMsg>();
        sendMag.put("patient", new WeChatTemplateMsg(patient));
        sendMag.put("logState", new WeChatTemplateMsg(logState));
        sendMag.put("state", new WeChatTemplateMsg(afterState));
        sendMag.put("name", new WeChatTemplateMsg(name));
        sendMag.put("time", new WeChatTemplateMsg(time));
        sendMag.put("remarks", new WeChatTemplateMsg(remarks));
        // 发送
        templateMsgUtil.send(openId, templateId, sendMag);
    }

    /**
     * 1、模板： 病例进度变更提醒   行业: 医药医疗  templateId: 3yr0YSXjZRLn0tUgt-lXb9Yp-bTY-ZlXKhDIBxC2pL4
     * <P>
     * //        {{first.DATA}}
     * //        病例号：{{keyword1.DATA}}
     * //        患者姓名：{{keyword2.DATA}}
     * //        当前状态：{{keyword3.DATA}}
     * //        {{remark.DATA}}
     * </P>
     * @param openId
     * @param
     */
    @Override
    public void caseProgressChangeReminder(String openId,
                                           String orderNo,
                                           String patient,
                                           String afterState,
                                           String remarkData,
                                           String title
    ) {
        // 模板Id
        String templateId = "3yr0YSXjZRLn0tUgt-lXb9Yp-bTY-ZlXKhDIBxC2pL4";
        // 模板参数
        Map<String, WeChatTemplateMsg> sendMag = new HashMap<String, WeChatTemplateMsg>();
        sendMag.put("first", new WeChatTemplateMsg(title));
        sendMag.put("keyword1", new WeChatTemplateMsg(orderNo));
        sendMag.put("keyword2", new WeChatTemplateMsg(patient));
        sendMag.put("keyword3", new WeChatTemplateMsg(afterState));
        sendMag.put("remark", new WeChatTemplateMsg(remarkData));
        // 发送
        templateMsgUtil.send(openId, templateId, sendMag);
    }
}
