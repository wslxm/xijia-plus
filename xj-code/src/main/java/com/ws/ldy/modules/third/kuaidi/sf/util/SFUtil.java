package com.ws.ldy.modules.third.kuaidi.sf.util;

import com.alibaba.fastjson.JSON;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.modules.third.kuaidi.sf.config.SFProperties;
import com.ws.ldy.modules.third.kuaidi.sf.entity.placeAnOrder.base.ApiResultData;
import com.ws.ldy.modules.third.kuaidi.sf.entity.placeAnOrder.base.SFReturnData;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import sun.misc.BASE64Encoder;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.UUID;

@Component
@Slf4j
public class SFUtil {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SFProperties sfProperties;

    /**
     * 向顺丰(丰桥)API接口发起请求
     * @param serviceCode 请求接口
     * @param mgsData 请求参数： json 参数
     *   <P>
     * //  1*	下快运订单	EXP_RECE_CREATE_ORDER
     *   </P>
     * @return SFReturnData
     */
    public String http(String serviceCode, String mgsData) {
        String timestamp = System.currentTimeMillis() + "";
        String msgDigest = genDigest(mgsData, timestamp, sfProperties.getCustomerCheckWord());
        // 发送快递参数处理
        MultiValueMap<String, Object> sendBody = new LinkedMultiValueMap<>();
        sendBody.add("partnerID", sfProperties.getCustomerCode());                 // 合作伙伴编码（由顺丰分配）
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
        ResponseEntity<String> result = restTemplate.postForEntity(sfProperties.getUrl(), formEntity, String.class);
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
