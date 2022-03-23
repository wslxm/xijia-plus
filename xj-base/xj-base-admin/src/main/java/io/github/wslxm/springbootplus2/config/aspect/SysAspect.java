package io.github.wslxm.springbootplus2.config.aspect;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.github.wslxm.springbootplus2.config.aspect.gateway.*;
import io.github.wslxm.springbootplus2.common.auth.entity.JwtUser;
import io.github.wslxm.springbootplus2.core.config.error.GlobalExceptionHandler;
import io.github.wslxm.springbootplus2.core.result.R;
import io.github.wslxm.springbootplus2.core.result.RType;
import io.github.wslxm.springbootplus2.manage.xj.model.entity.XjAdminLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


/**
 * Controller 请求操作/记录/权鉴
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/11/15 0015 11:16
 * @version 1.0.1
 */
@Slf4j
@Aspect
@Component
public class SysAspect {

    /**
     * 日志记录
     */
    @Autowired
    private SysLog sysLog;

    /**
     * 黑名单认证
     */
    @Autowired
    private SysBlacklist sysBlacklist;
    /**
     * 登录授权认证
     */
    @Autowired
    private SysAuth sysAuth;

    /**
     * 幂等验证
     */
    @Autowired
    private SysIdempotent sysIdempotent;

    /**
     * 参数加密解密
     */
    @Autowired
    private SysEncrypt sysEncrypt;

    /**
     * 参数加密解密
     */
    @Autowired
    private SysRateLimiter sysRateLimiter;

    /**
     * 全局异常
     */
    @Autowired
    private GlobalExceptionHandler globalExceptionHandler;

    /**
     * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
     * 使用给定的初始*参数创建一个新的{@code ThreadPoolExecutor}。 *
     *  * @param corePoolSize保留在池中的线​​程数，即使它们是空闲的，除非设置了{@code allowCoreThreadTimeOut}
     *  * @param maximumPoolSize *池中允许的最大线程数
     *  * @param keepAliveTime 何时线程数大于内核数，这是多余的空闲线程在终止之前等待新任务的最长时间。
     *  * @param unit {@code keepAliveTime}参数的时间单位
     *  * @param work 在执行任务之前将队列用于保存任务。此队列将仅保存由{@code execute}方法提交的{@code Runnable} *任务。
     *  * @param threadFactory 执行程序创建新线程时要使用的工厂
     *  * @param 处理程序执行被阻塞时要使用的处理程序
     *  *因为达到了线程界限和队列容量
     *  * @throws IllegalArgumentException如果以下条件之一成立：< br>
     *    * {@code corePoolSize <0} <br>
     *    * {@code keepAliveTime <0} <br>
     *    * {@code maximumPoolSize <= 0} <br>
     *    * {@code maximumPoolSize <corePoolSize} * @如果抛出{ @code workQueue} *或{@code threadFactory}或{@code handler}为空
     *
     */
    ExecutorService executorService = new ThreadPoolExecutor(3, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>()
            , new ThreadFactoryBuilder().setNameFormat("thread-call-runner-%d").build());


    /**
     * 不需要记录日志的 uri 集, 静态资源, css, js ,路由等等, 只要uri包含以下定义的内容, 将直接跳过改过滤器
     */
    private final List<String> excludeUriList = new ArrayList<>();

    public SysAspect() {
        // springbootAdmin监控相关
        excludeUriList.add("/bootAdmin/instances");
        // 系统监控相关
        excludeUriList.add("/bootAdmin");
        // 系统监控相关
        excludeUriList.add("/actuator");
        // sql监控相关
        excludeUriList.add("/druid/");
        // 页面跳转(路由)
        excludeUriList.add("/page/");
        // 模板解析错误
        excludeUriList.add("/error");
        // 日志相关
        excludeUriList.add("/api/admin/adminLog/");
        // swagger访问
        excludeUriList.add("/swagger-resources/");
        // 获取jvm信息
        excludeUriList.add("/api/admin/xj/jvm/jvmInfo");
    }

    /**
     * 拦截范围
     * @param proceed
     * @return
     * @throws Throwable
     */
    @Around("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public Object aroundGet(ProceedingJoinPoint proceed) throws Throwable {
        return run(proceed);
    }

    @Around("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public Object aroundSave(ProceedingJoinPoint proceed) throws Throwable {
        return run(proceed);
    }

    @Around("@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public Object aroundUpdate(ProceedingJoinPoint proceed) throws Throwable {
        return run(proceed);
    }

    @Around("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public Object aroundDelete(ProceedingJoinPoint proceed) throws Throwable {
        return run(proceed);
    }

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object aroundRequest(ProceedingJoinPoint proceed) throws Throwable {
        return run(proceed);
    }


    /**
     * 数据请求入口
     * <P>
     *  // startTime1 = 程序开始执行时间, 由RequestFilter 过滤器中添加
     *  // startTime2 = 业务代码开始执行时间
     *  // endTime1   = 程序结束时间
     * </P>
     * @author wang-song
     * @param proceed
     * @date 2020/7/14 0014 14:14
     * @return java.lang.Object
     * @version 1.0.1
     */
    private Object run(ProceedingJoinPoint proceed) throws Throwable {
        long startTime1 = System.currentTimeMillis();
        // 获取请求参数
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        String uri = request.getRequestURI();
        String method = request.getMethod();

        // 1、排除不需要处理的请求
        for (String excludeUri : excludeUriList) {
            if (uri.contains(excludeUri)) {
                // 直接执行返回
                return proceed.proceed();
            }
        }
        // 验签, 已被移动到 SysSingFilter 过滤器中进行, 验证失败不会进入到aop
        // 2、记录请求日志, 将异步执行(与业务代码并行处理),不影响程序响应, future 为线程的返回值，用于后面异步执行响应结果
        Future<XjAdminLog> future = executorService.submit(() -> sysLog.requestLogCollectAndPrint(proceed, request));

        // 3、限流
        R rateLimiter = sysRateLimiter.run(proceed);
        if (!rateLimiter.getCode().equals(RType.SYS_SUCCESS.getValue())) {
            // 7、记录响应结果
            sysLog.responseLogAndSave(future, 0, (System.currentTimeMillis() - startTime1), 0L, method, uri, rateLimiter);
            return rateLimiter;
        }

        // 4、幂等验证
        R apiIdempotentR = sysIdempotent.run(proceed);
        if (!apiIdempotentR.getCode().equals(RType.SYS_SUCCESS.getValue())) {
            // 7、记录响应结果
            sysLog.responseLogAndSave(future, 0, (System.currentTimeMillis() - startTime1), 0L, method, uri, apiIdempotentR);
            return apiIdempotentR;
        }

        // 5、黑/白名单认证
        R blacklistR = sysBlacklist.blacklistAuth();
        if (!blacklistR.getCode().equals(RType.SYS_SUCCESS.getValue())) {
            // 7、记录响应结果
            sysLog.responseLogAndSave(future, 0, (System.currentTimeMillis() - startTime1), 0L, method, uri, blacklistR);
            return blacklistR;
        }

        // 6、登录授权认证
        R<JwtUser> jwtUserR = sysAuth.loginAuth();
        if (!jwtUserR.getCode().equals(RType.SYS_SUCCESS.getValue())) {
            // 7、记录响应结果
            sysLog.responseLogAndSave(future, 0, (System.currentTimeMillis() - startTime1), 0L, method, uri, jwtUserR);
            return jwtUserR;
        }

        // 7、调用业务方法并记录执行时间
        long startTime2 = System.currentTimeMillis();
        Object obj = null;
        try {
            // 6.1、请求核心参数解密
            R<Object[]> rArgs = sysEncrypt.decrypt(proceed);
            // 6.2、请求接口
            obj = proceed.proceed(rArgs.getData());
            // 6.3、响应核心参数加密
            obj = sysEncrypt.encrypt(obj);
        } catch (Exception e) {
            // 记录 业务代码异常, 这里try后, 全局异常将不生效，在直接主动调用(如果没有try exceptionHandler在异常时会自动进行拦截,在这里拦截主要是响应结果信息)
            obj = globalExceptionHandler.exceptionHandler(e);
        }

        // 8、记录响应结果和记录响应时间(state=1-成功,等待请求线程执行完毕立即执行)
        long endTime1 = System.currentTimeMillis();
        sysLog.responseLogAndSave(future, 1, (endTime1 - startTime1), (endTime1 - startTime2), method, uri, obj);

        // 9、返回结果
        return obj;
    }
}

