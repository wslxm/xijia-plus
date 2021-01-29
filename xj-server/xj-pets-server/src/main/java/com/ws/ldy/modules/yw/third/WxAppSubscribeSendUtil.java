package com.ws.ldy.modules.yw.third;

import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.modules.third.wechat.app.entity.WxAppMsg;
import com.ws.ldy.modules.third.wechat.app.util.WxAppSubscribeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class WxAppSubscribeSendUtil {

    @Autowired
    private WxAppSubscribeUtil wxAppSubscribeUtil;

    /**
     * 订单支付提醒
     * @param userName    // 付款人
     * @param productName // 产品名称
     * @param amount      // 金额
     * @param time        // 日期
     * @return
     */
    public R<String> payMsg(String openId, String userName, String productName, BigDecimal amount, String time) {
        // 模板Id
        String templateId = "-Sc4vvyw2ontip0wPatcDYOfzEqaUqJUrOJvsPJmy3U";
        // 模板参数
        Map<String, WxAppMsg> sendMag = new HashMap<String, WxAppMsg>();
        sendMag.put("thing1", new WxAppMsg(userName));
        sendMag.put("thing2", new WxAppMsg(productName));
        sendMag.put("amount3", new WxAppMsg(amount.toString()));
        sendMag.put("time4", new WxAppMsg(time));
        // 发送
        R<String> resData = wxAppSubscribeUtil.sendMsg(openId, templateId, sendMag);
        if (!resData.getCode().equals(RType.SYS_SUCCESS.getValue())) {
            return R.error(resData.getCode(), resData.getMsg());
        }
        return R.success(resData.getData());
    }


    /**
     *  审核结果通知
     * @param checkContent // 审核内容
     * @param checkTime    // 审核时间
     * @param checkResult  // 审核结果
     * @param remark       // 备注
     *  @return
     */
    public R<String> checkMsg(String openId, String checkContent, String checkTime, String checkResult, String remark) {
        // 模板Id
        String templateId = "h8cZrmAuIyLPEol0TPzx0A43omEWapxy_fcJqAuXfZA";
        // 模板参数
        Map<String, WxAppMsg> sendMag = new HashMap<String, WxAppMsg>();
        sendMag.put("thing1", new WxAppMsg(checkContent));
        sendMag.put("date2", new WxAppMsg(checkTime));
        sendMag.put("phrase3", new WxAppMsg(checkResult));
        sendMag.put("thing4", new WxAppMsg(remark));
        // 发送
        R<String> resData = wxAppSubscribeUtil.sendMsg(openId, templateId, sendMag);
        if (!resData.getCode().equals(RType.SYS_SUCCESS.getValue())) {
            return R.error(resData.getCode(), resData.getMsg());
        }
        return R.success(resData.getData());
    }


    /**
     * 反馈处理结果通知
     * @param feedbackContent  // 反馈内容
     * @param replyContent     // 回复内容
     * @return
     */
    public R<String> feedbackCheckMsg(String openId, String feedbackContent, String replyContent) {
        // 模板Id
        String templateId = "mZlYMEKvhF1qFqjJE88B4PdHXQprWLyQh2DbLzMCCTs";
        // 模板参数
        Map<String, WxAppMsg> sendMag = new HashMap<String, WxAppMsg>();
        sendMag.put("thing1", new WxAppMsg(feedbackContent));
        sendMag.put("thing2", new WxAppMsg(replyContent));
        // 发送
        R<String> resData = wxAppSubscribeUtil.sendMsg(openId, templateId, sendMag);
        if (!resData.getCode().equals(RType.SYS_SUCCESS.getValue())) {
            return R.error(resData.getCode(), resData.getMsg());
        }
        return R.success(resData.getData());
    }
}
