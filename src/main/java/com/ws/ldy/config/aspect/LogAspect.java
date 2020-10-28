package com.ws.ldy.config.aspect;

import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.config.auth.filter.JwtFilter;
import com.ws.ldy.config.auth.filter.LogFilter;
import com.ws.ldy.modules.admin.model.entity.AdminLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户操作日志
 *
 * @author wbg
 * @date 2019-11-23 9:49
 */
@Slf4j
@Aspect
@Component
public class LogAspect {


    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private LogFilter logFilter;


    @Around("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public Object aroundGet(ProceedingJoinPoint proceed) throws Throwable {
        return saveOpLogs(proceed);
    }

    @Around("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public Object aroundSave(ProceedingJoinPoint proceed) throws Throwable {
        return saveOpLogs(proceed);
    }

    @Around("@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public Object aroundUpdate(ProceedingJoinPoint proceed) throws Throwable {
        return saveOpLogs(proceed);
    }

    @Around("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public Object aroundDelete(ProceedingJoinPoint proceed) throws Throwable {
        return saveOpLogs(proceed);
    }

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object aroundRequest(ProceedingJoinPoint proceed) throws Throwable {
        return saveOpLogs(proceed);
    }

    /**
     * 日志记录
     * @author wang-song
     * @param proceed
     * @date 2020/7/14 0014 14:14
     * @return java.lang.Object
     * @version 1.0.0
     */
    private Object saveOpLogs(ProceedingJoinPoint proceed) throws Throwable {
        // 获取请求参数
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        HttpServletResponse response = sra.getResponse();
        String uri = request.getRequestURI();
        // 排除--springbootAdmin监控相关
        if (uri.indexOf("bootAdmin/instances") != -1) {
            return proceed.proceed();
        }
        // 排除--页面跳转(路由)
        if (uri.indexOf("/page") != -1) {
            return proceed.proceed();
        }
        if (uri.indexOf("/error") != -1) {
            return proceed.proceed();
        }
        // 1、记录请求日志
        AdminLog log = logFilter.log(proceed, request, response);
        // 2、登录认证
        R result = jwtFilter.doFilterInternal(request, response);
        if (!result.getCode().equals(RType.SYS_SUCCESS.getValue())) {
            // 记录请求结果
            logFilter.updLog(log.getId(), 0, result);
            return result;
        }
        // 3、调用业务方法
        Object obj = proceed.proceed();
        // 记录请求结果
        logFilter.updLog(log.getId(), 1, obj);
        // 4、返回
        return obj;
    }
}

