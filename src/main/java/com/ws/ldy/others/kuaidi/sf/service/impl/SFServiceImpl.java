package com.ws.ldy.others.kuaidi.sf.service.impl;

import com.alibaba.fastjson.JSON;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.others.kuaidi.sf.entity.placeAnOrder.base.ApiResultData;
import com.ws.ldy.others.kuaidi.sf.entity.placeAnOrder.base.SFReturnData;
import com.ws.ldy.others.kuaidi.sf.entity.placeAnOrder.request.SFOrder;
import com.ws.ldy.others.kuaidi.sf.entity.placeAnOrder.response.MsgData;
import com.ws.ldy.others.kuaidi.sf.service.SFService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import sun.misc.BASE64Encoder;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


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
    private RestTemplate restTemplate;

    // 个人账号沙盒测试地址(17628689969/ ws/19960302)
    private static final String URL = "https://sfapi-sbox.sf-express.com/std/service";    // 请求地址
    private static final String CUSTOMER_CODE = "WSXX";                                   // 客户编码
    private static final String CUSTOMER_CHECK_WORD = "sKfq2WO8V38rRFsyverQrnuuPfI0DS87"; // 客户校检码
    private static final String CUSTOMER_MONTHLY_CARD = "7551234567";                     // 客户月结卡号（测试卡号）


    /**
     * 账号：      密码：
     * 月结号    0286790603
     * 调用地址: https://sfapi-sbox.sf-express.com/std/service
     * 顾客编码:
     * 校检码：
     */
//    private static final String CUSTOMER_CODE = "";                                   // 客户编码
//    private static final String CUSTOMER_CHECK_WORD = "";   // 客户校检码
//    private static final String URL = "https://sfapi-sbox.sf-express.com/std/service";      // 请求沙盒地址
//    // private static final String CUSTOMER_MONTHLY_CARD = "7551234567";                    // 客户月结卡号（测试卡号）
//    // private static final String URL = "https://sfapi.sf-express.com/std/service";        // 请求正式地址
//    private static final String CUSTOMER_MONTHLY_CARD = "";                                 // 正式月结号


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
    public String placeAnOrder(SFOrder en) {
        // 相关设置
        en.setIsDocall(1);                         // 1-要求上门取 (并打印电子面单)
        en.setPayMethod(3);                        // 3- 使用第三方付
        en.setMonthlyCard(CUSTOMER_MONTHLY_CARD);  // 客户正式月结卡号
        en.setLanguage("zh_CN");                   // 请求返回： zh-CN * 表示中文简体，   * zh-TW或zh-HK或   * zh-MO表示中文繁体
        // 请求下单
        String msgDataJson = this.http("EXP_RECE_CREATE_ORDER", JSON.toJSONString(en));
        // 成功下单
        MsgData msgData = JSON.parseObject(msgDataJson, MsgData.class);
        // 返回顺丰订单号
        String waybillNo = msgData.getWaybillNoInfoList().get(0).getWaybillNo();
        return waybillNo;
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
    public String cancelOrder(String orderId) {
        Map<String, Object> param = new HashMap<String, Object>() {{
            put("orderId", orderId);   // 客户订单号
            put("dealType", 2);        //  1:确认订单 2:取消订单
        }};
        //
        String msgDataJson = http("EXP_RECE_UPDATE_ORDER", JSON.toJSONString(param));
        return msgDataJson;
    }


    /**
     * 向顺丰(丰桥)API接口发起请求
     * @param serviceCode 请求接口
     * @param mgsData 请求参数： json 参数
     *   <P>
     * //  1*	下快运订单	EXP_RECE_CREATE_ORDER
     *   </P>
     * @return SFReturnData
     */
    private String http(String serviceCode, String mgsData) {
        String timestamp = System.currentTimeMillis() + "";
        String msgDigest = genDigest(mgsData, timestamp, CUSTOMER_CHECK_WORD);
        // 发送快递参数处理
        MultiValueMap<String, Object> sendBody = new LinkedMultiValueMap<>();
        sendBody.add("partnerID", CUSTOMER_CODE);                 // 合作伙伴编码（由顺丰分配）
        sendBody.add("requestID", UUID.randomUUID().toString());  // 请求唯一号UUID
        sendBody.add("serviceCode", serviceCode);                 // 接口服务代码取消订单
        sendBody.add("timestamp", timestamp);                     // 调用接口时间戳
        sendBody.add("msgDigest", msgDigest);                     // 数字签名
        sendBody.add("msgData", mgsData);                         // 业务数据
        // 设置请求头参数
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");
        // 发送请求
        HttpEntity<MultiValueMap<String, Object>> formEntity = new HttpEntity<>(sendBody, headers);
        ResponseEntity<String> result = restTemplate.postForEntity(URL, formEntity, String.class);
        //
        SFReturnData sfReturnData = JSON.parseObject(result.getBody(), SFReturnData.class);
        // 判断是否请求成功
        if (!"A1000".equals(sfReturnData.getApiResultCode())) {
            //失败
            log.info(sfReturnData.toString());
            throw new ErrorException(10099, sfReturnData.getApiErrorMsg());
        }
        // 请求成功判断验证是否通过
        ApiResultData apiResultData = JSON.parseObject(sfReturnData.getApiResultData(), ApiResultData.class);
        if (!apiResultData.isSuccess()) {
            //失败
            log.info(sfReturnData.toString());
            throw new ErrorException(10099, apiResultData.getErrorMsg());
        }
        return apiResultData.getMsgData();
    }


    /**
     * 业务数据加密   将业务报文+时间戳+校验码组合成需加密的字符串(注意顺序)
     * @param mgsData 业务数据
     * @param timestamp 时间戳
     * @param checkWord 客户校验码
     * @return
     * @throws Exception
     */
    @SneakyThrows
    private String genDigest(String mgsData, String timestamp, String checkWord) {
        //将业务报文+时间戳+校验码组合成需加密的字符串(注意顺序)
        String toVerifyText = mgsData + timestamp + checkWord;
        //因业务报文中可能包含加号、空格等特殊字符，需要urlEnCode处理
        toVerifyText = URLEncoder.encode(toVerifyText, "UTF-8");
        //进行Md5加密
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(toVerifyText.getBytes("UTF-8"));
        byte[] md = md5.digest();
        //通过BASE64生成数字签名
        String msgDigest = new String(new BASE64Encoder().encode(md));
        return msgDigest;
    }

}
