package com.ws.ldy.config.auth.filter;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 * 请求参数 过滤器
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/10/29 0029 17:50
 * @version 1.0.0
 */
@Slf4j
public class RequestFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 记录请求时间,用于后续记录请求耗时,请将该过滤器执行顺序为最高,会优先执行, 也以便于处理请求参数
        response.setHeader("startTime", System.currentTimeMillis() + "");
        filterChain.doFilter(servletRequest, servletResponse);
    }


    @Override
    public void destroy() {

    }
}