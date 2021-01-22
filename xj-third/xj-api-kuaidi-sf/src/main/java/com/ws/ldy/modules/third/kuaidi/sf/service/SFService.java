package com.ws.ldy.modules.third.kuaidi.sf.service;

import com.ws.ldy.common.result.R;
import com.ws.ldy.modules.third.kuaidi.sf.entity.placeAnOrder.request.SFOrder;

/**
 * 顺丰快递
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/9/15 0015 17:22
 * @version 1.0.0
 */
public interface SFService {

    /**
     * 顺丰快递下单
     * @param en
     * @param
     * @return
     */
    public R<String> placeAnOrder(SFOrder en);

    /**
     * 顺丰快递取消下单
     * @return
     */
    public R<String> cancelOrder(String orderId);
}
