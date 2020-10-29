package com.ws.ldy.config.aspect;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.config.auth.entity.JwtUser;
import com.ws.ldy.config.auth.util.JwtUtil;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.admin.model.entity.AdminAuthority;
import com.ws.ldy.modules.admin.model.entity.AdminLog;
import com.ws.ldy.modules.admin.service.AdminLogService;
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
import java.util.concurrent.*;

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
    private AdminLogService adminLogService;

    /**
     * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
     */
    final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);


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
        long startAopTime = System.currentTimeMillis();
        // 获取请求参数
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
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
        // 排除--模板解析错误
        if (uri.indexOf("/error") != -1) {
            return proceed.proceed();
        }
        // 排除--查看日志
        if (uri.indexOf("/admin/adminLog/") != -1) {
            return proceed.proceed();
        }
        // 1、记录请求日志, 异步执行, future 为线程的返回值，用于后面异步执行响应结果
        Future<AdminLog> future = fixedThreadPool.submit(new Callable<AdminLog>() {
            @Override
            public AdminLog call() {
                return log(proceed, request, response);
            }
        });
        // 2、调用业务方法
        long startTime = System.currentTimeMillis();
        Object obj = proceed.proceed();
        String startTime1 = response.getHeader("startTime");
        // 3、记录响应结果(state=1-成功，异步执行,不影响程序响应)
        this.updLog(future, 1, System.currentTimeMillis() - Long.parseLong(startTime1), System.currentTimeMillis() - startTime, obj);
        // 4、返回
        return obj;
    }


    /**
     * 记录响应日志
     * @author wangsong
     * @mail 1720696548@qq.com
     * @date 2020/10/29 0029 11:24 
     * @version 1.0.0
     */
    private void updLog(Future<AdminLog> future, Integer state, Long executeTime, Long businessTime, Object obj) {
        fixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    // 判断记录请求日志是否记录完成(true=完成)
                    if (future.isDone()) {
                        AdminLog log = null;
                        try {
                            log = future.get();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        updLog(log.getId(), state, executeTime, businessTime, obj);
                        break;
                    }
                }
            }
        });
    }


    /**
     * 打印日志到控制台 / 记录访问日志到数据库
     * <P>
     *  如需统一日志收集,在此收集内容到统一日志收集器中
     * </P>
     * @author wangsong
     * @mail 1720696548@qq.com
     * @date 2020/10/28 0028 15:04
     * @version 1.0.0
     */
    private AdminLog log(ProceedingJoinPoint proceed, HttpServletRequest request, HttpServletResponse response) {
        // 获取域名(服务器路径)
        String serverName = request.getServerName();
        // 请求来源(发起者当前页面路径)
        String referer = request.getHeader("referer");
        // 获取用户真实ip(发起者)
        String ip = getIpAddress(request);
        // 请求行中的接口名称
        String uri = request.getRequestURI();
        // 请求方式(get/post)
        String method = request.getMethod();
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
        Object[] args = proceed.getArgs(); // uri ： 接口  包： packageName,  请求类： 接口+类描叙+接口描叙
        // 控制台打印
        log.info("用户ip:[{}] --> 设备名:[{}] --> 端口：[{}] -->  请求类:[{}]  -->  URL: [{}] -->  PARAM:[{}]  -->  模块:[{}] -- [{}]",
                ip,
                host,
                port,
                String.format("%-65s", className),
                String.format("%-65s", url),
                args,
                classDesc,
                methodDesc
        );
        // 获取登录用户信息
        R<JwtUser> jwtUserR = JwtUtil.getJwtUserR(request);
        // 记录到数据库
        AdminLog log = new AdminLog();
        // 记录日志时不管token是否过期等，是否有效等, 能获取到用户信息表示已登录,否则表示未登录
        if (jwtUserR.getCode().equals(RType.SYS_SUCCESS.getValue())) {
            // 已登录
            JwtUser jwtUser = jwtUserR.getData();
            log.setFullName(jwtUser.getFullName());
            log.setUserId(jwtUser.getUserId());
            log.setType(jwtUser.getType());
        } else {
            // 未登录
            log.setFullName("未登录用户");
            log.setUserId("0");
            AdminAuthority adminAuthority = BaseConstant.Cache.AUTH_MAP.get(uri);
            if (adminAuthority != null) {
                log.setType(adminAuthority.getType());
            } else {
                log.setType(-1);
            }
        }
        log.setReferer(referer);
        log.setUrl(url);
        log.setUri(uri);
        log.setIp(ip);
        log.setHost(host);
        log.setMethod(method);
        log.setServerName(serverName);
        log.setPort(port + "");
        log.setPackageName(packageName);
        log.setClassName(className);
        log.setClassDesc(classDesc);
        log.setMethodDesc(methodDesc);
        // 请求数据
        try {
            String jsonParam = JSON.toJSONString(args);
            log.setRequestData(jsonParam);
        } catch (Exception e) {
            log.setRequestData("无法解析该数据");
        }
        log.setResponseData(null);     // 返回数据
        log.setState(0);               // 默认失败
        adminLogService.save(log);
        return log;
    }


    /**
     * 访问结束后添加日志结果到数据库
     * @param id :
     * @param state=0 失败 (默认)  type=1 成功
     * @param obj 返回数据
     * @param executeTime aop 执行总耗时
     * @param businessTime 业务执行总耗时
     * @author wangsong
     * @mail 1720696548@qq.com
     * @date 2020/10/28 0028 20:03
     * @version 1.0.0
     */
    private void updLog(String id, Integer state, Long executeTime, Long businessTime, Object obj) {
        String data = "";
        try {
            data = JSON.toJSONString(obj);
        } catch (Exception e) {
            data = "无法解析该数据";
        }
        // log.info("状态：{} ,返回数据：{} ", state, obj);
        // 记录返回数据
        adminLogService.update(new LambdaUpdateWrapper<AdminLog>()
                .set(AdminLog::getExecuteTime, executeTime)
                .set(AdminLog::getBusinessTime, businessTime)
                .set(AdminLog::getState, state)
                .set(AdminLog::getResponseData, data)
                .eq(AdminLog::getId, id)
        );
    }


    /**
     * 获取请求地址
     * @author wang-song
     * @param request
     * @date 2020/7/14 0014 14:16
     * @return java.lang.String
     * @version 1.0.0
     */
    private String getIpAddress(HttpServletRequest request) {
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
}

