//package com.ws.ldy.config.auth.filter;
//
//import com.alibaba.fastjson.JSON;
//import com.ws.ldy.config.auth.service.FilterService;
//import com.ws.ldy.config.auth.wrapper.ResponseWrapper;
//import lombok.extern.slf4j.Slf4j;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//import java.lang.reflect.Method;
//
///**
// * 返回值输出过滤器，这里用来加密返回值
// *
// * @Title: ResponseFilter
// * @Description:
// * @author kokJuis
// * @date 上午9:52:42
// */
//@Slf4j
//public class ResponseFilter implements Filter {
//
//    private FilterService filterService;
//
//    public ResponseFilter(FilterService filterService) {
//        this.filterService = filterService;
//    }
//
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
//            throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        String uri = request.getRequestURI();
//        //  1、排除
//        Boolean aBoolean = filterService.excludeUri(uri);
//        if (!aBoolean) {
//            filterChain.doFilter(servletRequest, servletResponse);
//            return;
//        }
//        // 转换成代理类
//        ResponseWrapper wrapperResponse = new ResponseWrapper(response);
//        // 这里只拦截返回，直接让请求过去，如果在请求前有处理，可以在这里处理
//        filterChain.doFilter(request, wrapperResponse);
//        // 返回值字节
//        byte[] content = wrapperResponse.getContent();
//        // 判断是否有值
//        if (content.length <= 0) {
//            return;
//        }
//        // 返回值
//        String str = new String(content, "UTF-8");
//        // 调用链
//        if (!isJson(str)) {
//            // 如果不是json数据,可能是返回的数据流或下载文件,直接返回原数据
//            //把返回值输出到客户端
//            ServletOutputStream out = response.getOutputStream();
//            out.write(content);
//            out.flush();
//        } else {
//            // 处理返回数据
//            String cipherText = null;
//            try {
//                cipherText = str;
//                //......根据需要处理返回值
//            } catch (Exception e) {
//                log.debug(e.toString());
//            }
//            //把返回值输出到客户端
//            ServletOutputStream out = response.getOutputStream();
//            out.write(cipherText.getBytes());
//            out.flush();
//        }
//    }
//
//    @Override
//    public void init(FilterConfig arg0) throws ServletException {
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
//     * 判断字符串数据是否为json
//     * @param content
//     * @return
//     */
//    public static boolean isJson(String content) {
//        try {
//            JSON.parseObject(content);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//}
