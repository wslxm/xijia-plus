package com.ws.ldy.config.aspect;

import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.config.auth.entity.JwtUser;
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
    private JwtFilter jwtFilter;

    @Autowired
    private LogFilter logFilter;


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
                return logFilter.log(proceed, request, response);
            }
        });

        // 2、登录认证
        R<JwtUser> result = jwtFilter.doFilterInternal(request, response);
        if (!result.getCode().equals(RType.SYS_SUCCESS.getValue())) {
            // 记录请求结果(state=0-失败)
            this.updLog(future, 0, System.currentTimeMillis() - startAopTime, 0L, result);
            return result;
        }
        // 3、调用业务方法
        long startTime = System.currentTimeMillis();
        Object obj = proceed.proceed();

        // 4、记录响应结果(state=1-成功，异步执行,不影响程序响应)
        this.updLog(future, 1, System.currentTimeMillis() - startAopTime, System.currentTimeMillis() - startTime, obj);

        // 5、返回
        return obj;
    }


    /**
     * 记录响应日志
     * @author wangsong
     * @mail 1720696548@qq.com
     * @date 2020/10/29 0029 11:24 
     * @version 1.0.0
     */
    public void updLog(Future<AdminLog> future, Integer state, Long executeTime, Long businessTime, Object obj) {
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
                        logFilter.updLog(log.getId(), state, executeTime, businessTime, obj);
                        break;
                    }
                }
            }
        });
    }
}

