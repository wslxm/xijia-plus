package com.ws.ldy.core.base.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BaseController<S extends IService> {

    @Autowired
    protected S baseService;
    @Autowired
    protected HttpSession session;
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;
    @Autowired
    protected RestTemplate restTemplate;

    /**
     * 获取分页对象   === mybatis-plus
     *
     * @return
     */
    protected <T> com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> getPage() {
        // 分页参数
        String current = request.getParameter("current");
        String size = request.getParameter("size");
        // 页数
        Integer cursor = current == null ? 1 : Integer.parseInt(current);
        Integer limit = size == null ? 20 : Integer.parseInt(size);
        // 分页大小
        return new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(cursor, limit);
    }


    /**
     * 获取请求地址
     * @author wang-song
     * @param request
     * @date 2020/7/14 0014 14:16
     * @return java.lang.String
     * @version 1.0.0
     */
    protected static String getIpAddress(HttpServletRequest request) {
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
     * 获取项目的Ip 地址+端口 或者域名
     * @return
     */
    public static String getBaseUrl(HttpServletRequest request) {
        // 接口名
        String interfaceName = request.getServletPath();
        // 完整url
        String url = request.getRequestURL().toString();
        // 去调接口后url
        String baseUrl = url.replace(interfaceName, "");
        return baseUrl;
    }
}
