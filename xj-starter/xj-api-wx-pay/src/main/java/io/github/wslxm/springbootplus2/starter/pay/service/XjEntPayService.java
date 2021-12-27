package io.github.wslxm.springbootplus2.starter.pay.service;

import io.github.wslxm.springbootplus2.core.result.R;
import io.github.wslxm.springbootplus2.starter.pay.model.dto.WxEntPayDTO;
import io.github.wslxm.springbootplus2.starter.pay.model.vo.WxEntPayResultVO;

/**
 *  @author wangsong
 * <pre>
 * 企业付款相关
 * Created by Binary Wang on 2018/9/27.
 * </pre>
 */

public interface XjEntPayService {


    /**
     * 企业打款
     * @param wxEntPayDTO
     * @return
     */
    public R<WxEntPayResultVO> entPay(WxEntPayDTO wxEntPayDTO);
}
