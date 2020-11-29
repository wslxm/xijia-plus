package com.ws.ldy.config.aspect;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.ws.ldy.XijiaServer;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.config.auth.entity.JwtUser;
import com.ws.ldy.config.auth.util.JwtUtil;
import com.ws.ldy.config.error.GlobalExceptionHandler;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.enums.Enums;
import com.ws.ldy.modules.admin.controller.AdminBlacklistController;
import com.ws.ldy.modules.admin.model.entity.AdminAuthority;
import com.ws.ldy.modules.admin.model.entity.AdminBlacklist;
import com.ws.ldy.modules.admin.model.entity.AdminLog;
import com.ws.ldy.modules.admin.model.vo.AdminBlacklistVO;
import com.ws.ldy.modules.admin.service.AdminAuthorityService;
import com.ws.ldy.modules.admin.service.AdminBlacklistService;
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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;


/**
 * Controller 请求操作/记录/权鉴
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/11/15 0015 11:16
 * @version 1.0.0
 */
@Slf4j
@Aspect
@Component
public class SysAspect {
    /**
     * 接口权限
     */
    @Autowired
    private AdminAuthorityService adminAuthorityService;
    /**
     * 黑名单/白名单
     */
    @Autowired
    private AdminBlacklistService adminBlacklistService;
    /**
     * 日志
     */
    @Autowired
    private AdminLogService adminLogService;
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
    private static List<String> excludeUriList = new ArrayList<String>() {{
        add("/bootAdmin/instances");  // springbootAdmin监控相关
        add("/bootAdmin");            //
        add("/actuator");             // 系统监控相关
        add("/druid/");               // sql监控相关
        add("/page/");                // 页面跳转(路由)
        add("/error");                // 模板解析错误
        add("/admin/adminLog/");      // 日志相关
        add("/swagger-resources/");   // swagger访问
    }};


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
     * 日志记录
     * <P>
     *  // startTime1 = 程序开始执行时间, 由RequestFilter 过滤器中添加
     *  // startTime2 = 业务代码开始执行时间
     *  // endTime1   = 程序结束时间
     * </P>
     * @author wang-song
     * @param proceed
     * @date 2020/7/14 0014 14:14
     * @return java.lang.Object
     * @version 1.0.0
     */
    private Object run(ProceedingJoinPoint proceed) throws Throwable {
        long startTime1 = System.currentTimeMillis();
        // 获取请求参数
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        HttpServletResponse response = sra.getResponse();
        String uri = sra.getRequest().getRequestURI();
        // 1、排除不需要处理的请求
        for (String excludeUri : excludeUriList) {
            if (uri.contains(excludeUri)) {
                // 直接执行返回
                return proceed.proceed();
            }
        }
        // 2、记录请求日志, 将异步执行(与业务代码并行处理),不影响程序响应, future 为线程的返回值，用于后面异步执行响应结果
        Future<AdminLog> future = executorService.submit(new Callable<AdminLog>() {
            @Override
            public AdminLog call() {
                // 使用json 序列化进行深拷贝,防止proceed引用被修改
                // ProceedingJoinPoint proceedingJoinPoint = JSON.parseObject(JSON.toJSONString(proceed), ProceedingJoinPoint.class);
                return log(proceed, request);
            }
        });
        // 3、黑/白名单认证
        String ipAddress = getIpAddress(request);
        R blacklistR = blacklistAuth( ipAddress);
        if (!blacklistR.getCode().equals(RType.SYS_SUCCESS.getValue())) {
            this.updLog(future, 0, (System.currentTimeMillis() - startTime1), 0L, uri, blacklistR);
            return blacklistR;
        }

        // 3、登录授权认证
        R<JwtUser> jwtUserR = loginAuth(request, response);
        if (!jwtUserR.getCode().equals(RType.SYS_SUCCESS.getValue())) {
            this.updLog(future, 0, (System.currentTimeMillis() - startTime1), 0L, uri, jwtUserR);
            return jwtUserR;
        }

        // 4、调用业务方法并记录执行时间
        long startTime2 = System.currentTimeMillis();
        Object obj = null;
        try {
            obj = proceed.proceed();
        } catch (Exception e) {
            // 业务代码异常, 这里try后, 全局异常将不生效，在直接主动调用(如果没有try exceptionHandler在异常时会自动进行拦截,在这里拦截主要是记录响应结果信息)
            obj = globalExceptionHandler.exceptionHandler(e);
        }

        // 5、记录响应结果和记录响应时间(state=1-成功,等待请求线程执行完毕立即执行)
        long endTime1 = System.currentTimeMillis();
        this.updLog(future, 1, (endTime1 - startTime1), (endTime1 - startTime2), uri, obj);

        // 6、返回结果
        return obj;
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
    private AdminLog log(ProceedingJoinPoint proceed, HttpServletRequest request) {
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
        // 记录到数据库
        AdminLog log = new AdminLog();
        // 记录用户信息
        log = setJwtUser(request, log);
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
            log.setRequestData("无法解析");
        }
        log.setResponseData(null);     // 返回数据
        log.setState(0);               // 默认失败
        adminLogService.save(log);
        // 打印请求日志
        printLog(ip, host, port, className, url, args, classDesc, methodDesc);
        return log;
    }


    /**
     * 访问结束后添加日志结果到数据库
     * @param1 future 请求日志记录线程
     * @param state=0 失败 (默认)  type=1 成功
     * @param obj 返回数据
     * @param executeTime aop 执行总耗时
     * @param businessTime 业务执行总耗时
     * @author wangsong
     * @mail 1720696548@qq.com
     * @date 2020/10/28 0028 20:03
     * @version 1.0.0
     */
    private void updLog(Future<AdminLog> future, Integer state, Long executeTime, Long businessTime, String uri, Object obj) {
        long time = System.currentTimeMillis();
        while (true) {
            // 如果没有回来，避免死循环
            if ((System.currentTimeMillis() - time) > 5000) {
                log.info("注意：程序在5秒内没有正常执行完毕, 日志记录失败, 请求uri = " + uri);
                break;
            }
            // 判断记录请求日志是否记录完成(true=完成)
            if (future.isDone()) {
                AdminLog logs = null;
                String data = "";
                try {
                    logs = future.get();
                    data = JSON.toJSONString(obj);
                } catch (InterruptedException | ExecutionException e) {
                    log.info("future.get() 获取数据失败： " + uri);
                    break;
                } catch (Exception e) {
                    data = "无法解析";
                }
                // 记录返回数据
                if (logs != null) {
                    adminLogService.update(new LambdaUpdateWrapper<AdminLog>()
                            .set(AdminLog::getExecuteTime, executeTime)
                            .set(AdminLog::getBusinessTime, businessTime)
                            .set(AdminLog::getState, state)
                            .set(AdminLog::getResponseData, data)
                            .eq(AdminLog::getId, logs.getId())
                    );
                } else {
                    log.info("注意： 日志记录失败,logs=null, 请求uri = " + uri);
                }
                // System.out.println(logs.getClassDesc() + logs.getUrl() + "  --> " + data);
                break;
            }
        }
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


    /**
     *  打印请求信息
     * @author wangsong
     * @param ip
     * @param host
     * @param port
     * @param className
     * @param url
     * @param args
     * @param classDesc
     * @param methodDesc
     * @date 2020/11/9 0009 16:33
     * @return void
     * @version 1.0.0
     */
    private void printLog(String ip, String host, Integer port, String className, String url, Object[] args, String classDesc, String methodDesc) {
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
    }


    /**
     * 日志中记录用户信息
     * <p>
     *  1、 从 JwtUtil 中获取用户信息
     *  2、 把用户信息放入log并返回，如果没有用户信息则用户名为： ╥﹏╥ ，用户id为：0
     * <p/>
     * @return
     */
    private AdminLog setJwtUser(HttpServletRequest request, AdminLog log) {
        String uri = request.getRequestURI();
        // 获取登录用户信息
        R<JwtUser> jwtUserR = JwtUtil.getJwtUserR(request);
        // 记录日志时不管token是否过期等，是否有效等, 能获取到用户信息表示已登录,否则表示未登录
        if (jwtUserR.getCode().equals(RType.SYS_SUCCESS.getValue())) {
            // 已登录
            JwtUser jwtUser = jwtUserR.getData();
            log.setFullName(jwtUser.getFullName());
            log.setUserId(jwtUser.getUserId());
            log.setType(jwtUser.getType());
        } else {
            // 未登录
            log.setFullName("╥﹏╥");
            log.setUserId("0");
            AdminAuthority adminAuthority = BaseConstant.Cache.AUTH_MAP.get(uri);
            if (adminAuthority != null) {
                log.setType(adminAuthority.getType());
            } else {
                log.setType(-1);
            }
        }
        return log;
    }


    /**
     * 登录授权认证
     * <>
     *     token认证，授权认证，
     *     没有Token 直接放行, 让请求接入权限认证, 需要授权的接口没有token当然是认证不过的啦
     *     需要授权的接口, 在token 中获取当前登录用户的权限, 当前用户没有当前请求的接口权限当然也是认证不过的啦
     *     // ===
     *     前端接口认证：暂无处理
     *     返回：
     *       - 如果需要登录, 返回的jwt存储的用户信息, 用于记录日志, 如果接口不需要登录,返回的 null
     *       - 如果登录过期或无接口权限,返回对应的错误信息，会直接返回到前端
     * </>
     * @param request
     * @param response
     */
    private R<JwtUser> loginAuth(HttpServletRequest request, HttpServletResponse response) {
        // 1、是否为绝对放行接口,是直接放行
        String uri = request.getRequestURI();
        if (BaseConstant.Sys.URIS.contains(uri)) {
            return R.success(null);
        }
        // 2、是否被权限管理, 没有直接放行，log.info("请求方式:{} 请求URL:{} ", request.getMethod(), request.getServletPath());
        if (!BaseConstant.Cache.AUTH_MAP.containsKey(uri)) {
            return R.success(null);
        }
        // 3、接口是否禁用，是直接返回禁用信息
        AdminAuthority adminAuthority = BaseConstant.Cache.AUTH_MAP.get(uri);
        if (adminAuthority.getDisable().equals(Enums.Base.Disable.DISABLE_1.getValue())) {
            //禁用
            return R.error(RType.AUTHORITY_DISABLE);
        }
        // 4、登录/授权验证
        if (adminAuthority.getState().equals(Enums.Admin.AuthorityState.AUTHORITY_STATE_0.getValue())) {
            /**
             *  0- 无需登录 (不做任何处理)
             */
            return R.success(null);
        } else if (adminAuthority.getState().equals(Enums.Admin.AuthorityState.AUTHORITY_STATE_1.getValue())) {
            /**
             *  1- 需登录 (能获取用户信息jwtUser 即成功)
             */
            R<JwtUser> result = JwtUtil.getJwtUserR(request);
            if (!result.getCode().equals(RType.SYS_SUCCESS.getValue())) {
                // error
                return result;
            }
            // 刷新token有效期
            JwtUser jwtUser = result.getData();
            JwtUtil.refreshToken(adminAuthorityService, response, jwtUser);
            return R.success(jwtUser);
        } else if (adminAuthority.getState().equals(Enums.Admin.AuthorityState.AUTHORITY_STATE_2.getValue())) {
            /**
             *  2- 需登录+授权 (100% 管理端才会进入, 验证用户信息的权限列表中是否存在当前接口，存在放行，不存在拦截返回无权限访问)
             */
            R<JwtUser> result = JwtUtil.getJwtUserR(request);
            if (!result.getCode().equals(RType.SYS_SUCCESS.getValue())) {
                // error
                return result;
            }
            // 判断权限
            JwtUser jwtUser = result.getData();
            if (jwtUser.getAuthList() == null || !jwtUser.getAuthList().contains(request.getRequestURI())) {
                return R.error(RType.AUTHORITY_NO_PERMISSION);
            }
            // 刷新token有效期
            JwtUtil.refreshToken(adminAuthorityService, response, jwtUser);
            return R.success(jwtUser);
        }
        return R.success(null);
    }


    /**
     * 黑名单/白名单验证
     * <p>
     * 认证顺序 ,   黑名单指定ip --> 白名单(*)  --> 黑名单(*)   --> 白名单指定ip
     * 优先级：
     * 1、访问ip 在黑名单ip列表，直接禁止访问
     * 2、设置了白名单（*）, 只要ip不在黑名单列表，直接放行
     * 3、访问ip没有在黑名单中，也没有设置白名单（*）的情况下，设置了黑名单(*)，那么除了白名单中的指定ip能访问接口外，其他ip都不能访问资源
     *
     * 本地 127.0.0.1 + localhost 访问直接放行
     * <p/>
     * @author wangsong
     * @date 2020/11/28 0028 0:01
     * @return com.ws.ldy.common.result.R
     * @version 1.0.0
     */
    public R blacklistAuth(String ip) {
        // 本地启用不处理黑名单/白名单
        if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
            return R.success();
        }
        // 如果没有缓存，就去数据库获取
        if (BaseConstant.Cache.BLACKLIST_CACHE == null) {
            // 如果数据库没有配置，缓存设置默认对象，让其不为空，防止无限制查询数据库
            BaseConstant.Cache.BLACKLIST_CACHE = new HashMap<>();
            List<AdminBlacklist> blacklist = adminBlacklistService.list(new LambdaQueryWrapper<AdminBlacklist>()
                    .eq(AdminBlacklist::getDisable, Enums.Base.Disable.DISABLE_0)
            );
            if (blacklist.size() > 0) {
                List<AdminBlacklistVO> adminBlacklistVOS = BeanDtoVoUtil.listVo(blacklist, AdminBlacklistVO.class);
                Map<Integer, List<AdminBlacklistVO>> blacklistGroupByType = adminBlacklistVOS.stream().collect(Collectors.groupingBy(AdminBlacklistVO::getType));
                List<AdminBlacklistVO> baiMD = blacklistGroupByType.get(Enums.Admin.BlacklistType.BLACKLIST_TYPE_1.getValue());
                List<AdminBlacklistVO> heiMD = blacklistGroupByType.get(Enums.Admin.BlacklistType.BLACKLIST_TYPE_2.getValue());
                if (baiMD != null) {
                    List<String> baiIps = baiMD.stream().map(p -> p.getIp()).collect(Collectors.toList());
                    BaseConstant.Cache.BLACKLIST_CACHE.put(Enums.Admin.BlacklistType.BLACKLIST_TYPE_1.getValue(), baiIps);
                }
                if (heiMD != null) {
                    List<String> heiIps = heiMD.stream().map(p -> p.getIp()).collect(Collectors.toList());
                    BaseConstant.Cache.BLACKLIST_CACHE.put(Enums.Admin.BlacklistType.BLACKLIST_TYPE_2.getValue(), heiIps);
                }
            }
        }
        // 1、没有配置黑/白名单，直接放行
        if (BaseConstant.Cache.BLACKLIST_CACHE.size() == 0) {
            return R.success();
        }
        // 2、没有配置黑名单，直接放行
        if (!BaseConstant.Cache.BLACKLIST_CACHE.containsKey(Enums.Admin.BlacklistType.BLACKLIST_TYPE_2.getValue())) {
            return R.success();
        }
        // 3、检查黑名单，如果被列进的黑名单，直接拦截
        if (BaseConstant.Cache.BLACKLIST_CACHE.containsKey(Enums.Admin.BlacklistType.BLACKLIST_TYPE_2.getValue())) {
            List<String> heiIps = BaseConstant.Cache.BLACKLIST_CACHE.get(Enums.Admin.BlacklistType.BLACKLIST_TYPE_2.getValue());
            if (heiIps.contains(ip)) {
                return R.error(RType.SYS_BLACK_LIST_IP.getValue(), "[" + ip + "] " + RType.SYS_BLACK_LIST_IP);
            }
        }
        // 4、如果配置了白名单( * )直接放行除了黑名单的所有ip
        if (BaseConstant.Cache.BLACKLIST_CACHE.containsKey(Enums.Admin.BlacklistType.BLACKLIST_TYPE_1.getValue())) {
            List<String> baiIps = BaseConstant.Cache.BLACKLIST_CACHE.get(Enums.Admin.BlacklistType.BLACKLIST_TYPE_1.getValue());
            if (baiIps.contains("*")) {
                return R.success();
            }
        }
        // 5、如果配置了黑名单( * )拦截除了白名单外的所有ip
        if (BaseConstant.Cache.BLACKLIST_CACHE.containsKey(Enums.Admin.BlacklistType.BLACKLIST_TYPE_2.getValue())) {
            List<String> heiIps = BaseConstant.Cache.BLACKLIST_CACHE.get(Enums.Admin.BlacklistType.BLACKLIST_TYPE_2.getValue());
            if (heiIps.contains("*")) {
                List<String> baiIps = BaseConstant.Cache.BLACKLIST_CACHE.get(Enums.Admin.BlacklistType.BLACKLIST_TYPE_1.getValue());
                if (baiIps == null || !baiIps.contains(ip)) {
                    return R.error(RType.SYS_WHITE_LIST_NO_IP.getValue(), "[" + ip + "] " + RType.SYS_WHITE_LIST_NO_IP.getMsg());
                }
            }
        }
        return R.success();
    }
}

