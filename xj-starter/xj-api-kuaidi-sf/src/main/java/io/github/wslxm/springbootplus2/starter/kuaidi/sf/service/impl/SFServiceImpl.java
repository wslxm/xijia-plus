package io.github.wslxm.springbootplus2.starter.kuaidi.sf.service.impl;

import com.alibaba.fastjson.JSON;
import io.github.wslxm.springbootplus2.core.result.R;
import io.github.wslxm.springbootplus2.core.result.RType;
import io.github.wslxm.springbootplus2.starter.kuaidi.sf.config.SFProperties;
import io.github.wslxm.springbootplus2.starter.kuaidi.sf.entity.placeAnOrder.request.SFOrder;
import io.github.wslxm.springbootplus2.starter.kuaidi.sf.entity.placeAnOrder.response.MsgData;
import io.github.wslxm.springbootplus2.starter.kuaidi.sf.service.SFService;
import io.github.wslxm.springbootplus2.starter.kuaidi.sf.util.SFUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;


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
    public R<String> placeAnOrder(SFOrder en) {
        // 相关设置
        en.setIsDocall(1);                                         // 1-要求上门取 (并打印电子面单)
        en.setPayMethod(3);                                        // 3- 使用第三方付
        en.setMonthlyCard(sfProperties.getCustomerMonthlyCard());  // 客户正式月结卡号
        en.setLanguage("zh_CN");                   // 请求返回： zh-CN * 表示中文简体，   * zh-TW或zh-HK或   * zh-MO表示中文繁体
        // 请求下单
        R<String> rMsgData = sfUtil.http("EXP_RECE_CREATE_ORDER", JSON.toJSONString(en));
        if (!rMsgData.getCode().equals(RType.SYS_SUCCESS.getValue())) {
            return R.error(rMsgData.getCode(), rMsgData.getMsg());
        }
        String msgDataJson = rMsgData.getData();
        // 成功下单
        MsgData msgData = JSON.parseObject(msgDataJson, MsgData.class);
        // 返回顺丰订单号
        String waybillNo = msgData.getWaybillNoInfoList().get(0).getWaybillNo();
        return R.success(waybillNo);
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
    public R<String> cancelOrder(String orderId) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("orderId", orderId);   // 客户订单号
        param.put("dealType", 2);        //  1:确认订单 2:取消订单
        R<String> rMsgData = sfUtil.http("EXP_RECE_UPDATE_ORDER", JSON.toJSONString(param));
        if (!rMsgData.getCode().equals(RType.SYS_SUCCESS.getValue())) {
            return R.error(rMsgData.getCode(), rMsgData.getMsg());
        }
        //
        String msgDataJson = rMsgData.getData();
        return R.success(msgDataJson);
    }


}
