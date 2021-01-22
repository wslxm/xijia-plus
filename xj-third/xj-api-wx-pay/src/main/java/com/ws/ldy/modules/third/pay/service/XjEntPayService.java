package com.ws.ldy.modules.third.pay.service;

import com.ws.ldy.common.result.R;
import com.ws.ldy.modules.third.pay.model.dto.WxEntPayDTO;
import com.ws.ldy.modules.third.pay.model.vo.WxEntPayResultVO;

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
