package io.github.wslxm.springbootplus2.config.gateway.singfilter;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.github.wslxm.springbootplus2.common.cache.AuthCacheKeyUtil;
import io.github.wslxm.springbootplus2.common.cache.ConfigCacheKey;
import io.github.wslxm.springbootplus2.common.cache.XjCacheUtil;
import io.github.wslxm.springbootplus2.config.gateway.singfilter.util.RequestWrapper;
import io.github.wslxm.springbootplus2.config.gateway.singfilter.util.SignUtil;
import io.github.wslxm.springbootplus2.core.constant.BooleanConstant;
import io.github.wslxm.springbootplus2.core.result.R;
import io.github.wslxm.springbootplus2.core.result.RType;
import io.github.wslxm.springbootplus2.manage.admin.model.entity.AdminAuthority;
import io.github.wslxm.springbootplus2.manage.xj.model.vo.XjAdminConfigVO;
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
        // 是否需要验签(总开关)
        XjAdminConfigVO xjAdminConfig = XjCacheUtil.findConfigByCode(ConfigCacheKey.IS_SIGN);
        if (xjAdminConfig != null && BooleanConstant.FALSE.equals(xjAdminConfig.getContent())) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        //
        long startTime = System.currentTimeMillis();
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        // 1.1、判断接口是否被管理,没有被管理直接放行
        String uri = request.getRequestURI();

        Map<String, AdminAuthority> authMap = XjCacheUtil.findAuthAllToMap();
        String cacheKey = AuthCacheKeyUtil.getAuthCacheKey(request.getMethod(), request.getRequestURI());
        if (!authMap.containsKey(cacheKey)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // 1.2、判断接口是否需要验签
        Boolean isSign = authMap.get(cacheKey).getIsSign();
        if (!isSign) {
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

        // 3、验签, 判断是query参数还是body参数, 不同参数进行不同的验证方式，
        // -- body参数验签处理 Wrapper 为处理body只能获取一次参数的问题
        R<Boolean> sing = null;
        RequestWrapper requestWrapper = null;
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (!parameterMap.isEmpty()) {
            sing = isSing(null, parameterMap, request);
        } else {
            // body
            requestWrapper = new RequestWrapper(request);
            String body = requestWrapper.getBody();
            sing = isSing(body, null, request);
        }
        log.info("接口：{} 验签结果：{} , 耗时: {}", uri, sing.getMsg(), System.currentTimeMillis() - startTime);
        // 4、result
        if (sing.getCode().equals(RType.SYS_SUCCESS.getValue())) {
            filterChain.doFilter(requestWrapper == null ? servletRequest : requestWrapper, servletResponse);
        } else {
            servletResponse.setContentType("application/json;charset=utf-8");
            servletResponse.getWriter().write(JSON.toJSONString(sing));
        }
    }


    /**
     * 参数验签, 防止抓包篡改
     *
     * @param body         body 参数
     * @param parameterMap query参数
     * @return io.github.wslxm.common.result.R<java.lang.Boolean>
     * @author wangsong
     * @date 2021/4/1 0001 19:50
     * @version 1.0.1
     */
    public R<Boolean> isSing(String body, Map<String, String[]> parameterMap, HttpServletRequest request) {
        // 判断是否传递参数
        boolean isBody = StringUtils.isBlank(body);
        boolean isQuery = parameterMap == null || parameterMap.isEmpty();
        if (isBody && isQuery) {
            return R.success(true);
        }

        // 1、获取签名和时间戳
        Object sign = request.getHeader(SignUtil.SIGN);
        Object timestamp = request.getHeader(SignUtil.TIMESTAMP);
        if (sign == null || timestamp == null) {
            return R.error(RType.PARAM_IS_NO_SIGN);
        }
        // 2、判断请求是否超时
        if (!SignUtil.isTimeVerify(Long.parseLong(timestamp.toString()))) {
            return R.error(RType.PARAM_TIME_OUT);
        }
        boolean isSing = true;
        Map<String, String> verifyMap = null;

        // 处理query 参数
        if (parameterMap != null && !parameterMap.isEmpty()) {
            // 3、获取加签参数， 需要注意
            verifyMap = SignUtil.toVerifyMap(parameterMap, false);
            verifyMap.put(SignUtil.SIGN, sign.toString());
            verifyMap.put(SignUtil.TIMESTAMP, timestamp.toString());
            // 4、验证前端的加签和后端的是否一致
            isSing = SignUtil.verify(verifyMap);
        }

        // 处理body 参数,如果没有query参数,验证body参数
        if (StringUtils.isNotBlank(body)) {
            // 3、获取请求的body 参数,注意前后端时间格式要统一，排序,处理时间格式
            Object obj = JSON.parseObject(body, Object.class);
            body = JSONObject.toJSONString(obj, SerializerFeature.MapSortField);
            /// String newBody = JSON.toJSONStringWithDateFormat(jsonObject, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat);
            // 4、获取加签参数
            verifyMap = new HashMap<>(3,1);
            verifyMap.put(SignUtil.BODY, body);
            verifyMap.put(SignUtil.SIGN, sign.toString());
            verifyMap.put(SignUtil.TIMESTAMP, timestamp.toString());
            // 5、验证参数
            isSing = SignUtil.verify(verifyMap);
        }
        if (isSing) {
            return R.success(true);
        } else {
            return R.error(RType.PARAM_SIGN_ERROR);
        }
    }
}
