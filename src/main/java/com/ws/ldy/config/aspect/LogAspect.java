package com.ws.ldy.config.aspect;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

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
    public Object aroundAll(ProceedingJoinPoint proceed) throws Throwable {
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
        // 获取用户真实ip
        String ip = getIpAddress(request);
        // 请求行中的接口名称
        String uri = request.getRequestURI();
        // 获得客户端发送请求的完整url
        String url = request.getRequestURL().toString();
        // 请求的客户机的主机名
        String host = request.getRemoteHost();
        // 请求的客户机的端口号
        int port = request.getRemotePort();
        // 请求的类名
        String className = proceed.getTarget().getClass().getName();
        // 请求的包名
        String packageName = proceed.getTarget().getClass().getPackage().getName();
        // 方法swagger描叙
        MethodSignature signature = (MethodSignature) proceed.getSignature();
        ApiOperation methodAnnotation = signature.getMethod().getAnnotation(ApiOperation.class);
        String methodDesc = null;
        if (methodAnnotation != null) {
            methodDesc = methodAnnotation.value();
        }
        //类swagger描叙
        Api classAnnotation = proceed.getTarget().getClass().getDeclaredAnnotation(Api.class);
        String classDesc = null;
        if (classAnnotation != null) {
            classDesc = classAnnotation.tags().length > 0 ? classAnnotation.tags()[0] : classAnnotation.value();
        }
        Object[] args = proceed.getArgs(); // uri ： 接口  包： packageName,
        log.info("用户ip:[{}] --> 设备名:[{}] --> 端口：[{}] -->  请求类:[{}]  -->  URL: [{}] --> [{}] --> [{}] --> 请求参数:[{}]", ip, host, port, className, url, classDesc, methodDesc, args);

        //===========================================================================================================
        //=========================== 如需统一日志收集,在此log.info 是内容收集到统一日志收集器中 ============================
        //===========================================================================================================
        //调用方法
        Object res = proceed.proceed();
        return res;
    }


    /**
     * 获取请求地址
     * @author wang-song 
     * @param request
     * @date 2020/7/14 0014 14:16
     * @return java.lang.String
     * @version 1.0.0
     */
    private static String getIpAddress(HttpServletRequest request) {
        String unknown = "unknown";
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


    //
//    /**
//     * 允许请求跨域  跨域配置在：MvcConfig 中配置
//     */
//    public void setAllowOrigin() {
//        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
//        //HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        //此处ip地址为需要访问服务器的ip及端口号
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
//        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type,Token,Accept, Connection, User-Agent, Cookie");
//        //response.setHeader("Access-Control-Max-Age", "3628800");
//    }
}

