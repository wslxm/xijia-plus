//package com.ws.ldy.config.auth.filter;
//
//import com.alibaba.fastjson.JSON;
//import com.ws.ldy.common.result.R;
//import com.ws.ldy.common.result.RType;
//import com.ws.ldy.config.auth.entity.JwtUser;
//import com.ws.ldy.config.auth.util.JwtUtil;
//import com.ws.ldy.enums.BaseConstant;
//import com.ws.ldy.enums.Enums;
//import com.ws.ldy.modules.admin.model.entity.AdminAuthority;
//import com.ws.ldy.modules.admin.service.AdminAuthorityService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.List;
//
///***
// * 访问日志
// * @author wangsong
// * @mail 1720696548@qq.com
// * @date 2020/10/29 0029 17:50
// * @version 1.0.0
// */
//@Slf4j
//public class LogFilter implements Filter {
//
//    /**
//     * 需要进行接口验证的uri 集, 静态资源, css, js ,路由等等, 只要uri包含以下定义的内容, 将直接跳过改过滤器
//     */
//    private static List<String> excludeUriList = new ArrayList<String>() {{
//        add("/bootAdmin/instances");  // springbootAdmin监控相关
//        add("/actuator");             // 系统监控相关
//        add("/page/");                // 页面跳转(路由)
//    }};
//
//
//    @Override
//    public void init(FilterConfig filterConfig) {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
//
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}