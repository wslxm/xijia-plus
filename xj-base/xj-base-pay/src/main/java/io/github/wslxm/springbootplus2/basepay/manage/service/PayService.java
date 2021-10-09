package io.github.wslxm.springbootplus2.basepay.manage.service;

import io.github.wslxm.springbootplus2.core.result.R;
import io.github.wslxm.springbootplus2.basepay.manage.model.dto.EntPayDTO;
import io.github.wslxm.springbootplus2.basepay.manage.model.dto.PayOrderDTO;
import io.github.wslxm.springbootplus2.basepay.manage.model.dto.PayRefundDTO;
import io.github.wslxm.springbootplus2.basepay.manage.model.vo.PayOrderResultVO;
import io.github.wslxm.springbootplus2.basepay.manage.model.vo.PayRecordVO;

/**
 * 支付 Service
 */
public interface PayService {


    /**
     * 用户--创建订单
     * @author wangsong
     * @date 2021/1/5 0005 9:14
     * @return PayOrderResultVO 返回支付秘钥信息
     * @version 1.0.0
     */
    public R<PayOrderResultVO> createOrder(PayOrderDTO dto);


    /**
     * 用户--微信支付回调数据解析(业务端解析微信回调，调用此方法进行回调数据解析并获得对应参数)
     * @author wangsong
     * @date 2021/1/5 0005 9:14
     * @return
     * <P>
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
     * @return Boolean
     * @version 1.0.0
     * <P>
     *    code=200  表示支成功  | code !=200  表示失败
     *    data=true 表示成功    | data=false 表示失败
     *    msg       微信错误提示
     *    errerMsg  自定义错误提示
     * </P>
     */
    public R<Boolean> entPay(EntPayDTO entPayDTO);


    /**
     * 微信退款 (支付订单退款原路返回)
     * @author wangsong
     * @param refundDTO
     * @date 2021/3/26 0026 9:12
     * @return Boolean
     * <P>
     *    code=200  表示支成功  | code !=200  表示失败
     *    data=true 表示成功    | data=false 表示失败
     *    msg       微信错误提示
     *    errerMsg  自定义错误提示
     * </P>
     */
    public R<Boolean> refund(PayRefundDTO refundDTO);

}
