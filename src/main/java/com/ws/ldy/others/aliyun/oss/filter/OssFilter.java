package com.ws.ldy.others.aliyun.oss.filter;

import com.ws.ldy.others.aliyun.oss.config.OssConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.*;


/***
 *  过滤器--> 阿里云oss文件访问转发
 * <P>
 *   过滤器定义参考： https://www.cnblogs.com/ibigboy/p/11528775.html
 *   注意：需要在 MvcConfig 注册过滤器
 * </P>
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/10/29 0029 17:50
 * @version 1.0.0
 */
@Slf4j
@Component
public class OssFilter implements Filter {

    /**
     * oss uri路由第一层
     * @author wangsong
     * @date 2020/12/11 0011 16:51
     * @return
     * @version 1.0.0
     */
    final static String OSS_URI_BASE = "/oss";

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        // 判断是否为阿里云oss文件,是直接重定向到阿里云oss访问路径，不是执行下一个过滤器(如果有的话)
        String ossBaseUri = "";
        if (uri.length() > OSS_URI_BASE.length()){
            ossBaseUri = uri.substring(0, 4);
        }
        if (ossBaseUri.equals(OSS_URI_BASE)) {
            response.sendRedirect("http://" + OssConfig.bucket + uri);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}