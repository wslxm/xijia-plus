package io.github.wslxm.springbootplus2.config.gateway.accessauthaop.accessauth;


import com.google.common.util.concurrent.RateLimiter;
import io.github.wslxm.springbootplus2.common.annotation.XjCurrentLimit;
import io.github.wslxm.springbootplus2.common.cache.AuthCacheKeyUtil;
import io.github.wslxm.springbootplus2.core.config.error.ErrorException;
import io.github.wslxm.springbootplus2.core.result.Result;
import io.github.wslxm.springbootplus2.core.result.ResultType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 限流 （基于 com.google.guava 的令牌桶）
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2021/11/28 0028 9:49
 * @version 1.0.0
 */
@Slf4j
@Component
public class SysRateLimiter {

    /**
     * private RateLimiter rateLimiter = RateLimiter.create(1);
     */
    private ConcurrentHashMap<String, RateLimiter> rateLimiters = new ConcurrentHashMap();

    @Autowired
    private HttpServletRequest request;


    /**
      * 限流， 接口服务超出直接提示 访问人数过多
      * @author wangsong
      * @mail  1720696548@qq.com
      * @date  2021/11/28 0028 11:28
      * @version 1.0.0      
      */
    public Result run(ProceedingJoinPoint proceed) {
        // 获取请求参数
        MethodSignature signature = (MethodSignature) proceed.getSignature();
        XjCurrentLimit xjCurrentLimit = signature.getMethod().getAnnotation(XjCurrentLimit.class);
        if (xjCurrentLimit == null) {
            return Result.success();
        }
        // 获取请求url， 充当限流 name（name = cacheKey）
        String cacheKey = AuthCacheKeyUtil.getAuthCacheKey(request.getMethod(), request.getRequestURI());
        RateLimiter rateLimiter = rateLimiters.get(cacheKey);
        int qbs = xjCurrentLimit.qbs();
        String errorMsg = xjCurrentLimit.errorMsg();
        if (rateLimiter == null) {
            rateLimiter = RateLimiter.create(qbs);
            rateLimiters.put(cacheKey, rateLimiter);
        }
        // 开始限流
        boolean result = rateLimiter.tryAcquire();
        if (!result) {
            throw new ErrorException(ResultType.SYS_CURRENT_LIMIT.getValue(),errorMsg);
        }
        return Result.success();
    }
}
