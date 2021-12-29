package io.github.wslxm.springbootplus2.basepay.manage.service;

import io.github.wslxm.springbootplus2.core.result.R;
import io.github.wslxm.springbootplus2.basepay.manage.model.dto.EntPayDTO;
import io.github.wslxm.springbootplus2.basepay.manage.model.dto.PayOrderDTO;
import io.github.wslxm.springbootplus2.basepay.manage.model.dto.PayRefundDTO;
import io.github.wslxm.springbootplus2.basepay.manage.model.vo.PayOrderResultVO;
import io.github.wslxm.springbootplus2.basepay.manage.model.vo.PayRecordVO;

/**
 *  @author wangsong
 * 支付 Service
 */
public interface PayService {


    /**
     * 用户--创建订单
     *
     * @param dto dto
     * @return io.github.wslxm.springbootplus2.core.result.R<io.github.wslxm.springbootplus2.basepay.manage.model.vo.PayOrderResultVO>
     * @version 1.0.0
     */
    R<PayOrderResultVO> createOrder(PayOrderDTO dto);


    /**
     * 用户--微信支付回调数据解析(业务端解析微信回调，调用此方法进行回调数据解析并获得对应参数)
     *
     * <p>
     * code=200   表示支付成功
     * code !=200 表示支付失败, msg + errerMsg 返回错误信息
     * code !=200 && data=null  表示未获取到支付信息或重复回调(勿处理订单错误,无法获取订单号)
     * </P>
     *
     * @param xmlData xmlData
     * @return io.github.wslxm.springbootplus2.core.result.R<io.github.wslxm.springbootplus2.basepay.manage.model.vo.PayRecordVO>
     * @version 1.0.0
     */
    R<PayRecordVO> orderCallback(String xmlData);


    /**
     * 企业打款 (业务方直接调用打款)
     * <p>
     * code=200  表示支成功  | code !=200  表示失败
     * data=true 表示成功    | data=false 表示失败
     * msg       微信错误提示
     * errerMsg  自定义错误提示
     * </P>
     *
     * @param entPayDTO entPayDTO
     * @return io.github.wslxm.springbootplus2.core.result.R<java.lang.Boolean>
     * @version 1.0.0
     */
    R<Boolean> entPay(EntPayDTO entPayDTO);


    /**
     * 微信退款 (支付订单退款原路返回)
     * <p>
     * code=200  表示支成功  | code !=200  表示失败
     * data=true 表示成功    | data=false 表示失败
     * msg       微信错误提示
     * errerMsg  自定义错误提示
     * </P>
     *
     * @param refundDTO refundDTO
     * @return io.github.wslxm.springbootplus2.core.result.R<java.lang.Boolean>
     * @version 1.0.0
     */
    R<Boolean> refund(PayRefundDTO refundDTO);

}
