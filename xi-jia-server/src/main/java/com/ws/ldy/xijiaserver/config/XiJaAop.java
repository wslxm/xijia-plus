package com.ws.ldy.xijiaserver.config;

import com.ws.ldy.baseadmin.dao.RoleAuthAdminDao;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
  * TODO  URL权限, 验签（只针对 /public 接口），xss攻击防御，URL特殊字符转译，防盗链, 允许跨域，log4j接口调用日志记录
  * @author 王松
  * @mail  1720696548@qq.com
  * @date  2020/1/13 0013 20:20
  */
@SuppressWarnings("all")
@Component
@Aspect
public class XiJaAop {
    private static final Logger log = LoggerFactory.getLogger(XiJaAop.class);
    @Resource
    private RoleAuthAdminDao roleAuthAdminDao;
    /**
     * TODO  拦截当前项目的 controller 接口, 环绕通知：可以实现前置通知，后置通知，返回通知，例外通知的所有功能
     * @Around("execution(* com.ws.ldy.adminconsole.controller.*.*(..))")
     * @Around("execution(* com.ws.ldy..*.*(..))")
     *
     * @author 王松
     * @mail  1720696548@qq.com
     * @param jp 指定的连接点（拦截的业务方法）
     * @date  2020/1/13 0013 20:18
     * @return java.lang.Object
     */
    @Around("execution(* com.ws.ldy.xijiaserver.xjservice.*.*(..))")
    public Object doAroundAdvice(ProceedingJoinPoint jp) throws Throwable {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        response.setHeader("Access-Control-Allow-Origin", "*");
        //log.info("  URL:[{}] -----> REQUEST:[{}] \r\n---------------------------> RESPONSE:[{}]", request.getServletPath(), args, obj);
        log.info("URL:[{}]", request.getServletPath());
        // 获取请求参数
        Object[] args = jp.getArgs();
        // 调用业务逻辑，并记录日志
        Object obj = obj = jp.proceed(args);
        return obj;
    }
}
