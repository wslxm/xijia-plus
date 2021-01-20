//package com.ws.ldy.modules.third.aliyun.oss.filter;
//
//import com.ws.ldy.modules.third.aliyun.oss.config.AilYunOssProperties;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//
///***
// *  过滤器--> 阿里云oss文件访问转发
// * <P>
// *   过滤器定义参考： https://www.cnblogs.com/ibigboy/p/11528775.html
// *   注意：需要在 MvcConfig 注册过滤器
// * </P>
// * @author wangsong
// * @mail 1720696548@qq.com
// * @date 2020/10/29 0029 17:50
// * @version 1.0.0
// */
//@Slf4j
//@Component
//public class OssFilter implements Filter {
//
//
//    @Autowired
//    private AilYunOssProperties ailYunOssProperties;
//
//    /**
//     * oss uri 路由第一层
//     * @author wangsong
//     * @date 2020/12/11 0011 16:51
//     * @return
//     * @version 1.0.0
//     */
//    final static String OSS_URI_BASE = "/oss";
//
//
//    /**
//     * http://localhost:9048/oss/file/image/xray/20210107161905788642-%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20210107160230.jpg?x-oss-process=image/auto-orient,1/resize,p_8/quality,q_90
//     *
//     * 统一图片访问格式处理
//     * p_ = 图片大小比例 0-100
//     * q_ = 想对图片质量 0-100
//     */
//    final static String OSS_IMG_PROCESS = "?x-oss-process=image/auto-orient,1/resize,p_8/quality,q_100";
//
//    /**
//     * 图片上传路径
//     */
//    final static String OSS_IMG_FILE = "oss/file/image";
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
//        String uri = request.getRequestURI();
//        // 判断是否为阿里云oss文件,是直接重定向到阿里云oss访问路径，不是执行下一个过滤器(如果有的话)
//        String ossBaseUri = "";
//        if (uri.length() > OSS_URI_BASE.length()) {
//            ossBaseUri = uri.substring(0, 4);
//        }
//        if (ossBaseUri.equals(OSS_URI_BASE)) {
//            if (uri.indexOf(OSS_IMG_FILE) != -1) {
//                // 访问图片进行统一格式处理
//                response.sendRedirect("http://" + ailYunOssProperties.getBucket() + uri + OSS_IMG_PROCESS);
//            } else {
//                response.sendRedirect("http://" + ailYunOssProperties.getBucket() + uri);
//            }
//        } else {
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
//    }
//
//    @Override
//    public void destroy() {
//    }
//}