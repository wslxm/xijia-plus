package com.ws.ldy.modules.sys.pay.service;

import com.ws.ldy.common.result.R;
import com.ws.ldy.modules.sys.pay.model.dto.EntPayDTO;
import com.ws.ldy.modules.sys.pay.model.dto.PayOrderDTO;
import com.ws.ldy.modules.sys.pay.model.vo.EntPayResultVO;
import com.ws.ldy.modules.sys.pay.model.vo.PayOrderResultVO;
import com.ws.ldy.modules.sys.pay.model.vo.PayRecordVO;

/**
 * 支付 Service
 */
public interface PayService {


    /**
     * 用户--创建订单
     * @author wangsong
     * @date 2021/1/5 0005 9:14
     * @return void
     * @version 1.0.0
     */
    public PayOrderResultVO createOrder(PayOrderDTO dto);


    /**
     * 用户--微信支付回调解析(业务端解析微信回调，调用此方法进行回调数据解析并获得对应参数)
     * @author wangsong
     * @date 2021/1/5 0005 9:14
     * @return
     * <P>
     *
     *    code=200   表示支付成功
     *    code !=200 表示支付失败, msg + errerMsg 返回错误信息
     *    code !=200 && data=null  表示未获取到支付信息或重复回调(勿处理订单错误,无法获取订单号)
     * </P>
     * @version 1.0.0
     */
    public R<PayRecordVO> orderCallback(String xmlData);


    /**
     * 企业打款 (业务方直接调用打款)
     * @date 2021/1/5 0005 9:14
     * @return void
     * @version 1.0.0
     */
    public R<EntPayResultVO> entPay(EntPayDTO entPayDTO);

}
