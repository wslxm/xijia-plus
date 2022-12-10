package io.github.wslxm.springbootplus2.config.gateway.singfilter;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.github.wslxm.springbootplus2.common.cache.AuthCacheKeyUtil;
import io.github.wslxm.springbootplus2.common.cache.ConfigCacheKey;
import io.github.wslxm.springbootplus2.common.cache.XjCacheUtil;
import io.github.wslxm.springbootplus2.config.gateway.singfilter.util.RequestWrapper;
import io.github.wslxm.springbootplus2.config.gateway.singfilter.util.SignUtil;
import io.github.wslxm.springbootplus2.core.result.Result;
import io.github.wslxm.springbootplus2.core.result.ResultType;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.Authority;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 验签过滤器
 * <p>
 * 1、文件不验签
 * 2、query参数根据 key排序拼接成字符串进行md5 生成sign 进行加密验证
 * 3、body‘参数根据 key排序(包括使用子级) 后转成json字符串使用md5生成sign 进行加密验证
 * </P>
 *
 * @author wangsong
 * @version 1.0.1
 * @mail 1720696548@qq.com
 * @date 2021/3/29 0029 19:49
 */
@Component
@Slf4j
public class SysSingFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long startTime = System.currentTimeMillis();
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        // 是否需要验签(总开关)
        boolean isSignAll = XjCacheUtil.findConfigBooleanByCode(ConfigCacheKey.IS_SIGN);
        if (!isSignAll) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // 1.1、判断接口是否被管理,没有被管理直接放行
        Map<String, Authority> authMap = XjCacheUtil.findAuthAllToMap();
        String cacheKey = AuthCacheKeyUtil.getAuthCacheKey(request.getMethod(), request.getRequestURI());
        if (!authMap.containsKey(cacheKey)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // 1.2、判断接口是否需要验签
        Boolean isSignInterface = authMap.get(cacheKey).getIsSign();
        if (!isSignInterface) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // 2、如果是文件直接放行
        String contentType = servletRequest.getContentType();
        String contentTypeFile = "multipart/form-data";
        if (contentType != null && contentType.contains(contentTypeFile)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // 3、验签, 判断是query参数还是body参数, 不同参数进行不同的验证方式, body参数验签处理 Wrapper 为处理body只能获取一次参数的问题
        Result<Boolean> singResult = null;
        RequestWrapper requestWrapper = null;
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (!parameterMap.isEmpty()) {
            // query 参数
            singResult = this.isSing(null, parameterMap, request);
        } else {
            // body 参数或无参数
            requestWrapper = new RequestWrapper(request);
            singResult = this.isSing(requestWrapper.getBody(), null, request);
        }
        log.info("接口：{} 验签结果：{} , 耗时: {}", request.getRequestURI(), singResult.getMsg(), System.currentTimeMillis() - startTime);
        // 4、result
        if (singResult.getCode().equals(ResultType.SYS_SUCCESS.getValue())) {
            filterChain.doFilter(requestWrapper == null ? servletRequest : requestWrapper, servletResponse);
        } else {
            servletResponse.setContentType("application/json;charset=utf-8");
            servletResponse.getWriter().write(JSON.toJSONString(singResult));
        }
    }


    /**
     * 参数验签, 防止抓包篡改
     *
     * @param body         body 参数
     * @param parameterMap query参数
     * @return io.github.wslxm.common.result.Result<java.lang.Boolean>
     * @author wangsong
     * @date 2021/4/1 0001 19:50
     * @version 1.0.1
     */
    public Result<Boolean> isSing(String body, Map<String, String[]> parameterMap, HttpServletRequest request) {
        // 验证签名和时间戳参数以及接口是否超时
        Object sign = request.getHeader(SignUtil.SIGN);
        Object timestamp = request.getHeader(SignUtil.TIMESTAMP);
        if (sign == null || timestamp == null) {
            return Result.error(ResultType.PARAM_IS_NO_SIGN);
        }
        if (!SignUtil.isTimeVerify(Long.parseLong(timestamp.toString()))) {
            return Result.error(ResultType.PARAM_TIME_OUT);
        }

        // 判断是否传递参数
        boolean isBody = StringUtils.isNotBlank(body);
        boolean isQuery = parameterMap != null && !parameterMap.isEmpty();

        // 验签结果(true-验签成功-放行)
        Boolean isSing = null;
        if (isQuery) {
            // 获取query参数加签，在验证前后端加签结果是否一致
            Map<String, String> verifyMap = SignUtil.toVerifyMap(parameterMap, false);
            verifyMap.put(SignUtil.SIGN, sign.toString());
            verifyMap.put(SignUtil.TIMESTAMP, timestamp.toString());
            isSing = SignUtil.verify(verifyMap);
        } else if (isBody) {
            // 获取body 参数加签，在验证前后端加签结果是否一致 (使用json自带排序,参数根据字母大小写排序,注意前后端时间格式要统一)
            String verifyBody = JSONObject.toJSONString(JSON.parseObject(body, Object.class), SerializerFeature.MapSortField);
            Map<String, String> verifyMap = new HashMap<>(4, 1);
            verifyMap.put(SignUtil.BODY, verifyBody);
            verifyMap.put(SignUtil.SIGN, sign.toString());
            verifyMap.put(SignUtil.TIMESTAMP, timestamp.toString());
            isSing = SignUtil.verify(verifyMap);
        } else {
            // 没有请求参数 (默认使用时间戳参数进行加签验证)
            Map<String, String> verifyMap = new HashMap<>(4, 1);
            verifyMap.put(SignUtil.SIGN, sign.toString());
            verifyMap.put(SignUtil.TIMESTAMP, timestamp.toString());
            isSing = SignUtil.verify(verifyMap);
        }
        if (!isSing) {
            return Result.error(ResultType.PARAM_SIGN_ERROR);
        }
        return Result.success(true);
    }
}
