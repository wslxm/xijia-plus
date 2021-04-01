package com.ws.ldy.config.sing;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 验签过滤器
 * <P>
 *     1、文件不验签
 *     2、query参数根据 key排序拼接成字符串进行md5 生成sign 进行加密验证
 *     3、body‘参数根据 key排序(包括使用子级) 后转成json字符串使用md5生成sign 进行加密验证
 * </P>
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2021/3/29 0029 19:49 
 * @version 1.0.0
 */
@Component
@Slf4j
public class SysSingFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 1、如果是生成的文件直接放行
        String contentType = servletRequest.getContentType();
        if (contentType != null && contentType.contains("multipart/form-data")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        // 2、处理body只能获取一次参数的问题
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        MyRequestWrapper requestWrapper = new MyRequestWrapper(request);
        // 3、获取参数
        String body = requestWrapper.getBody();
        Map<String, String[]> parameterMap = request.getParameterMap();
        // 4、验签
        R<Boolean> sing = isSing(body, parameterMap, request);
        if (sing.getCode().equals(RType.SYS_SUCCESS.getValue())) {
            // 5、成功放行
            filterChain.doFilter(requestWrapper, servletResponse);
        } else {
            // 5、失败返回错误
            servletResponse.setContentType("text/html;charset=utf-8");
            servletResponse.getWriter().write(JSON.toJSONString(sing));
        }
    }


    /**
     * 参数验签, 防止抓包篡改
     * @author wangsong
     * @param body  body 参数
     * @param parameterMap query参数
     * @date 2021/4/1 0001 19:50
     * @return com.ws.ldy.common.result.R<java.lang.Boolean>
     * @version 1.0.0
     */
    public R<Boolean> isSing(String body, Map<String, String[]> parameterMap, HttpServletRequest request) {
        // 判断是否传递参数
        if (StringUtils.isBlank(body) && parameterMap.isEmpty()) {
            return R.success(true);
        }
        //Object[] args = proceed.getArgs();
        // 1、获取签名和时间戳
        Object sign = request.getHeader(SignUtil.SIGN);
        Object timestamp = request.getHeader(SignUtil.TIMESTAMP);
        if (sign == null || timestamp == null) {
            return R.error(RType.PARAM_SIGN_ERROR);
        }
        // 2、判断请求是否超时
        if (!SignUtil.isTimeVerify(Long.parseLong(timestamp.toString()))) {
            return R.error(RType.PARAM_TIME_OUT);
        }
        boolean isSing = true;
        Map<String, String> verifyMap = null;
        /**
         * 处理query 参数
         */
        if (!parameterMap.isEmpty()) {
            // 3、获取加签参数
            verifyMap = SignUtil.toVerifyMap(parameterMap, false);
            verifyMap.put("sign", sign.toString());
            verifyMap.put("timestamp", timestamp.toString());
            // 4、验证前端的加签和后端的是否一致
            isSing = SignUtil.verify(verifyMap);
        }
        /**
         * 处理body 参数
         * 如果没有query参数,验证body参数
         */
        if (StringUtils.isNotBlank(body)) {
            // 3、获取请求的body 参数,注意前后端时间格式要统一，排序,处理时间格式
            Object obj = JSON.parseObject(body,Object.class);
            body = JSONObject.toJSONString(obj, SerializerFeature.MapSortField);
            //String newBody = JSON.toJSONStringWithDateFormat(jsonObject, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat);
            // 4、获取加签参数
            verifyMap = new HashMap<>();
            verifyMap.put("body", body);
            verifyMap.put("sign", sign.toString());
            verifyMap.put("timestamp", timestamp.toString());
            // 5、验证参数
            isSing = SignUtil.verify(verifyMap);
        }
        if (isSing) {
            return R.success(true);
        } else {
            // 过滤空值、sign
            Map<String, String> sParaNew = SignUtil.paraFilter(verifyMap);
            // 获取待签名字符串
            String preSignStr = SignUtil.createLinkString(sParaNew);
            return R.error(RType.PARAM_SIGN_ERROR, null, "请检查参数或参数数据类型是否正确,接收参数: " + preSignStr);
        }
    }
}
