package io.github.wslxm.springbootplus2.config.aspect.gateway;


import com.alibaba.fastjson.JSON;
import io.github.wslxm.springbootplus2.cache.XjCacheUtil;
import io.github.wslxm.springbootplus2.common.auth.entity.JwtUser;
import io.github.wslxm.springbootplus2.common.auth.util.JwtUtil;
import io.github.wslxm.springbootplus2.core.result.R;
import io.github.wslxm.springbootplus2.core.result.RType;
import io.github.wslxm.springbootplus2.manage.admin.model.entity.AdminAuthority;
import io.github.wslxm.springbootplus2.manage.xj.model.entity.XjAdminLog;
import io.github.wslxm.springbootplus2.manage.xj.service.XjAdminLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 日志记录
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2021/1/23 0023 9:17
 * @version 1.0.1
 */
@SuppressWarnings("all")
@Slf4j
@Component
public class SysLog {


    @Autowired
    private XjAdminLogService adminLogService;


    /**
     * 日志入库数据记录范围(丰改访问的请求方式将不记录到数据库)
     */
    @Value("${logging.methods:GET,POST,PUT,DELETE}")
    private String methods;


    /**
     * 请求日志收集 / 打印日志到控制台
     * <P>
     *  如需统一日志收集,在此收集内容到统一日志收集器中
     * </P>
     * @param request 改方法为异步执行,request 必须调用方传递,也无法在 proceed中获取
     * @author wangsong
     * @mail 1720696548@qq.com
     * @date 2020/10/28 0028 15:04
     * @version 1.0.1
     */
    public XjAdminLog requestLogCollectAndPrint(ProceedingJoinPoint proceed, HttpServletRequest request) {
        String serverName = request.getServerName();         // 获取域名(服务器路径)
        String referer = request.getHeader("referer");     // 请求来源(发起者当前页面路径)
        String ip = getIpAddress(request);                   // 获取用户真实ip(发起者)
        String uri = request.getRequestURI();                // 请求行中的接口名称
        String method = request.getMethod();                 // 请求方式(get/post)
        String url = request.getRequestURL().toString();     // 获得客户端发送请求的完整url
        String host = request.getRemoteHost();               // 请求的客户机的主机名
        int port = request.getRemotePort();                  // 请求的客户机的端口号
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
        // uri ： 接口  包： packageName,  请求类： 接口+类描叙+接口描叙
        Object[] args = proceed.getArgs();

        // 记录日志信息
        XjAdminLog log = new XjAdminLog();
        log = setJwtUser(log, request);
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
        // 响应数据
        log.setResponseData(null);
        // 默认失败,接口访问成功后修改为成功
        log.setState(0);
        // 请求数据
        try {
            String jsonParam = JSON.toJSONString(args);
            log.setRequestData(jsonParam);
        } catch (Exception e) {
            log.setRequestData("the request data cannot be parsed");
        }
        // 打印请求日志
        this.printLog(log);
        // 数据是否入库,根据请求方式判断,yml 日志配置中配置
        if (methods.indexOf(method) != -1) {
            // adminLogService.save(log);
            return log;
        } else {
            return null;
        }
    }


    /**
     * 响应日志记录 / 添加日志数据持久化到数据库
     * @param1 future 请求日志记录线程
     * @param state=0 失败 (默认)  type=1 成功
     * @param obj 返回数据
     * @param executeTime aop 执行总耗时
     * @param businessTime 业务执行总耗时
     * @author wangsong
     * @mail 1720696548@qq.com
     * @date 2020/10/28 0028 20:03
     * @version 1.0.1
     */
    public void responseLogAndSave(Future<XjAdminLog> future, Integer state, Long executeTime, Long businessTime, String method, String uri, Object obj) {
        // 判断是否记录到数据库,根据请求方式区分
        if (methods.indexOf(method) == -1) {
            return;
        }
        // 记录响应
        long time = System.currentTimeMillis();
        while (true) {
            // 如果记录请求信息没有回来，避免死循环，让其5秒后自动跳出
            if ((System.currentTimeMillis() - time) > 5000) {
                log.info("Note: The program is not executed normally within 5 seconds, log record fails, request uri = " + uri);
                break;
            }
            // 判断记录请求日志是否记录完成(true=完成)
            if (future.isDone()) {
                XjAdminLog logs = null;
                String data = "";
                try {
                    logs = future.get();
                    data = JSON.toJSONString(obj);
                    if (data.length() > 65534) {
                        data = data.substring(0, 65534);
                    }
                } catch (InterruptedException | ExecutionException e) {
                    log.info("future.get() failed to get data： " + uri);
                    break;
                } catch (Exception e) {
                    data = "无法解析";
                }
                // 记录返回数据
                if (logs != null) {
                    logs.setExecuteTime(executeTime);
                    logs.setBusinessTime(businessTime);
                    logs.setState(state);
                    logs.setResponseData(data);
                    adminLogService.save(logs);
                } else {
                    log.info("note logging failed logs null request uri " + uri);
                }
                // log.info(logs.getClassDesc() + logs.getUrl() + "  --> " + data);
                break;
            }
            // 防止cpu飚高, 5毫秒循环一次
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 日志中记录用户信息
     * <p>
     *  1、 从 JwtUtil 中获取用户信息
     *  2、 把用户信息放入log并返回，如果没有用户信息则用户名为： ╥﹏╥ ，用户id为：0
     * <p/>
     * @return
     */
    private XjAdminLog setJwtUser(XjAdminLog log, HttpServletRequest request) {
        String uri = request.getRequestURI();
        // 获取登录用户信息
        R<JwtUser> jwtUserR = JwtUtil.getJwtUserR(request, null);
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

            Map<String, AdminAuthority> authMap = XjCacheUtil.findListAllToMap();
            AdminAuthority adminAuthority = authMap.get(uri);
            // AdminAuthority adminAuthority = CacheUtil.getAuthMap().get(uri);
            if (adminAuthority != null) {
                log.setType(adminAuthority.getType());
            } else {
                log.setType(-1);
            }
        }
        return log;
    }


    /**
     * 打印请求信息
     * @author wangsong
     * @param adminlog
     * @date 2020/11/9 0009 16:33
     * @return void
     * @version 1.0.1
     */
    private void printLog(XjAdminLog adminlog) {
        // 控制台打印
        log.info("" +
                        "\r\n|---------------------------------------------------------------------------------------------------------------------------------------------------------------------|" +
                        "\r\n| 请求人姓名: [{}]  请求人ID: [{}]  模块: [{}]--[{}]  类: [{}]" +
                        "\r\n| URL: [{}]   PARAM: [{}] " +
                        "\r\n|---------------------------------------------------------------------------------------------------------------------------------------------------------------------|",
                adminlog.getFullName(), adminlog.getUserId(), adminlog.getClassDesc(), adminlog.getMethodDesc(), adminlog.getClassName(),
                String.format("%-65s", adminlog.getUrl()), adminlog.getRequestData()
        );
        // 用户ip:[{}]   设备名:[{}]   端口：[{}]
        // adminlog.getIp(), adminlog.getHost(), adminlog.getPort()
    }


    /**
     * 获取请求地址
     * @author wang-song
     * @param request
     * @date 2020/7/14 0014 14:16
     * @return java.lang.String
     * @version 1.0.1
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
