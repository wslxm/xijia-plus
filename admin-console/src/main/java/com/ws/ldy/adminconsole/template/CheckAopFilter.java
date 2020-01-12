package com.ws.ldy.adminconsole.template;


import com.ws.ldy.admincore.controller.vo.ResponseData;
import org.aspectj.lang.ProceedingJoinPoint;

import javax.servlet.http.HttpServletRequest;

public abstract class CheckAopFilter {

    /**
     * @param jp      aop 参数
     * @param request 请求
     * @param args    请求参数
     * @return
     */
    public abstract ResponseData start(ProceedingJoinPoint jp, HttpServletRequest request, Object[] args);

}
