package com.ws.ldy.adminconsole.template.impl;

import com.ws.ldy.adminconsole.template.CheckAopFilter;
import com.ws.ldy.admincore.controller.vo.ResponseData;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO  5、调用业务逻辑，并记录日志 log4j
 */
@Component
public class AccessRecordLogFilter extends CheckAopFilter {

    private static final Logger log = LoggerFactory.getLogger(AccessRecordLogFilter.class);
    @Override
    public ResponseData start(ProceedingJoinPoint jp, HttpServletRequest request, Object[] args) {
        // 调用业务逻辑，并记录日志
        Object obj = null;
        try {
            obj = jp.proceed(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        //log.info("  URL:[{}] -----> REQUEST:[{}] \r\n---------------------------> RESPONSE:[{}]", request.getServletPath(), args, obj);
        log.info("URL:[{}] -----> REQUEST:[{}]", request.getServletPath(), args);
        return ResponseData.success(obj);
    }
}
