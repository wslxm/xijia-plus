package com.ws.ldy.others.wechat.pay.service.impl;

import com.ws.ldy.others.wechat.pay.service.XjEntPayService;
import com.ws.ldy.others.wechat.pay.wxApi.EntPayApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * 企业付款相关接口
 * Created by Binary Wang on 2018/9/27.
 * </pre>
 *
 */

@Service
public class XjEntPayServiceImpl implements XjEntPayService {

    @Autowired
    private EntPayApi entPayApi;

}
