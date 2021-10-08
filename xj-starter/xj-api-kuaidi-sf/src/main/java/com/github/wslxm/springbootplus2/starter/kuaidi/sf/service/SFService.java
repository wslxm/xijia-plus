package com.github.wslxm.springbootplus2.starter.kuaidi.sf.service;

import com.github.wslxm.springbootplus2.core.result.R;
import com.github.wslxm.springbootplus2.starter.kuaidi.sf.entity.placeAnOrder.request.SFOrder;

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
