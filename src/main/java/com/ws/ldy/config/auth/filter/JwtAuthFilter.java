//package com.ws.ldy.config.auth.filter;
//
//import com.alibaba.fastjson.JSON;
//import com.ws.ldy.common.result.R;
//import com.ws.ldy.common.result.RType;
//import com.ws.ldy.config.auth.entity.JwtUser;
//import com.ws.ldy.config.auth.service.FilterService;
//import com.ws.ldy.config.auth.util.JwtUtil;
//import com.ws.ldy.enums.BaseConstant;
//import com.ws.ldy.enums.Enums;
//import com.ws.ldy.modules.admin.model.entity.AdminAuthority;
//import com.ws.ldy.modules.admin.service.AdminAuthorityService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
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
// * 登录授权 过滤器
// * <P>
// *   过滤器定义参考： https://www.cnblogs.com/ibigboy/p/11528775.html
// * </P>
// * @author wangsong
// * @mail 1720696548@qq.com
// * @date 2020/10/29 0029 17:50
// * @version 1.0.0
// */
//@Slf4j
//@Component
//public class JwtAuthFilter implements Filter {
//
//
//    private FilterService filterService;
//
//
//    public JwtAuthFilter(FilterService filterService) {
//        this.filterService = filterService;
//    }
//
//
//    @Override
//    public void init(FilterConfig filterConfig) {
//    }
//
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//    }
//
//
//    @Override
//    public void destroy() {
//
//    }
//}