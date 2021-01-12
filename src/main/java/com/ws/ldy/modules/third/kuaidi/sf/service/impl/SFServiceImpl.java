package com.ws.ldy.modules.third.kuaidi.sf.service.impl;

import com.alibaba.fastjson.JSON;
import com.ws.ldy.modules.third.kuaidi.sf.config.SFProperties;
import com.ws.ldy.modules.third.kuaidi.sf.entity.placeAnOrder.request.SFOrder;
import com.ws.ldy.modules.third.kuaidi.sf.entity.placeAnOrder.response.MsgData;
import com.ws.ldy.modules.third.kuaidi.sf.service.SFService;
import com.ws.ldy.modules.third.kuaidi.sf.util.SFUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


/**
 * 顺丰
 * <p>
 *  * 文档地址: http://qiao.sf-express.com/pages/developDoc/index.html?level2=791563&level3=568519
 *  * 请求头必须添加： "Content-type","application/x-www-form-urlencoded;charset=UTF-8”
 *  * 在线查询物流信息地址： https://www.sf-express.com/cn/sc/dynamic_function/waybill/#search/bill-number/SF1193907051991
 * </p>
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/9/15 0015 17:24 
 * @version 1.0.0
 */
@Service
@Slf4j
public class SFServiceImpl implements SFService {


    @Autowired
    private SFUtil sfUtil;


    @Autowired
    private SFProperties sfProperties;

    /**
     * 顺丰下单（月结+上门取件）
     * <P>
     *  需添加的en 参数（收寄地址 + 货物名称+ 订单号orderId）
     *  注意: 取消订单后不能再使用相同的 orderId 重新寄件,建议寄件时生成唯一寄件订单号放入 自己的订单表,重新寄件重新生成
     * </P>
     * @param en
     * @return
     */
    @Override
    public String placeAnOrder(SFOrder en) {
        // 相关设置
        en.setIsDocall(1);                                         // 1-要求上门取 (并打印电子面单)
        en.setPayMethod(3);                                        // 3- 使用第三方付
        en.setMonthlyCard(sfProperties.getCustomerMonthlyCard());  // 客户正式月结卡号
        en.setLanguage("zh_CN");                   // 请求返回： zh-CN * 表示中文简体，   * zh-TW或zh-HK或   * zh-MO表示中文繁体
        // 请求下单
        String msgDataJson = sfUtil.http("EXP_RECE_CREATE_ORDER", JSON.toJSONString(en));
        // 成功下单
        MsgData msgData = JSON.parseObject(msgDataJson, MsgData.class);
        // 返回顺丰订单号
        String waybillNo = msgData.getWaybillNoInfoList().get(0).getWaybillNo();
        return waybillNo;
    }


    /**
     * 取消订单
     * @author wangsong
     * @param orderId
     * @date 2020/9/24 0024 9:16
     * @return java.lang.String
     * @version 1.0.0
     */
    @Override
    public String cancelOrder(String orderId) {
        Map<String, Object> param = new HashMap<String, Object>() {{
            put("orderId", orderId);   // 客户订单号
            put("dealType", 2);        //  1:确认订单 2:取消订单
        }};
        //
        String msgDataJson = sfUtil.http("EXP_RECE_UPDATE_ORDER", JSON.toJSONString(param));
        return msgDataJson;
    }


}
