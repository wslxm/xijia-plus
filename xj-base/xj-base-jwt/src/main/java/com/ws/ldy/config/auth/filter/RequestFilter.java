//package com.ws.ldy.config.auth.filter;
//
//
//import com.alibaba.fastjson.JSON;
//import com.ws.ldy.XijiaServer;
//import com.ws.ldy.common.result.R;
//import com.ws.ldy.common.result.RType;
//import com.ws.ldy.config.aspect.LogAspect;
//import com.ws.ldy.config.auth.entity.JwtUser;
//import com.ws.ldy.config.auth.service.FilterService;
//import com.ws.ldy.enums.BaseConstant;
//import lombok.extern.slf4j.Slf4j;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.lang.reflect.Method;
//
///***
// * 请求参数 过滤器
// * @author wangsong
// * @mail 1720696548@qq.com
// * @date 2020/10/29 0029 17:50
// * @version 1.0.0
// */
//@Slf4j
//public class RequestFilter implements Filter {
//
//    private FilterService filterService;
//
//
//    public RequestFilter(FilterService filterService) {
//        this.filterService = filterService;
//    }
//
//
//    @Override
//    public void init(FilterConfig filterConfig) {
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        // 记录请求时间,用于后续记录请求耗时,请将该过滤器执行顺序为最高,会优先执行, 也以便于处理请求参数
//        response.setHeader("startTime", System.currentTimeMillis() + "");
//        //  1、排除
//        String uri = request.getRequestURI();
//        if (!filterService.excludeUri(uri)) {
//            filterChain.doFilter(servletRequest, servletResponse);
//            return;
//        }
//        if (!BaseConstant.Cache.AUTH_MAP.containsKey(uri)) {
//            log.info("检测到未被管理的请求uri=" + uri);
//        }
//        //  2、登录授权认证
//        R<JwtUser> result = filterService.loginAuth(request, response);
//        //  3、判断登录授权结果(失败直接返回,成功调用 doFilter 向下走)
//        if (result.getCode().equals(RType.SYS_SUCCESS.getValue())) {
//            filterChain.doFilter(servletRequest, servletResponse);
//        } else {
//            getMethod();
//            // 记录请求日志（这里验证错误是进入不了aop的日志记录的, 在这里主动记录请求日志）
//            long startTimeOrder1 = Long.parseLong(response.getHeader("startTime"));
//            long endTimeOrder1 = System.currentTimeMillis();
//            filterService.saveLog(request,result, endTimeOrder1 - startTimeOrder1);
//            // 返回错误
//            filterService.error(response, result);
//        }
//    }
//
//
//    @Override
//    public void destroy() {
//
//    }
//
//
//    /**
//     * 获取执行类和方法
//     */
//    public Class<?> getMethod() {
//        // 获取启动类的包路径/ 当前类的包路径+类名
//        String fatherPackagePath = XijiaServer.class.getPackage().getName();
//        String thisCalssName = LogAspect.class.getName();
//        java.lang.StackTraceElement[] classArray = new Exception().getStackTrace();
//        Class<?> clazz = null;
//        Method method = null;
//        for (int i = 0; i < classArray.length; i++) {
//            // 只查询出启动类下自编写的调用 + 不执行当前类的调用链方法
//            if (classArray[i].getClassName().indexOf(fatherPackagePath) != -1 && !classArray[i].getClassName().equals(thisCalssName)) {
//                String classname = classArray[i].getClassName();
//                String methodname = classArray[i].getMethodName();
//                log.debug("调用数据源的类名：【" + classname + "】，方法名：【" + methodname + "】");
//            }
//        }
//        if (method == null) {
//            clazz = null;
//        }
//        return clazz;
//    }
//}