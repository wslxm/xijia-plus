package com.ws.ldy.starter.pay.service;

import com.ws.ldy.core.result.R;
import com.ws.ldy.starter.pay.model.dto.WxEntPayDTO;
import com.ws.ldy.starter.pay.model.vo.WxEntPayResultVO;

/**
 * <pre>
 * 企业付款相关
 * Created by Binary Wang on 2018/9/27.
 * </pre>
 *
 *
 */

public interface XjEntPayService {


    /**
     * 企业打款
     * @param wxEntPayDTO
     * @return
     */
    public R<WxEntPayResultVO> entPay(WxEntPayDTO wxEntPayDTO);
}
