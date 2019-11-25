package com.ws.ldy.adminconsole.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截处理
 *
 * @author wangsong
 * @date 2019年4月10日 下午4:24:41
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("user");
        if (user != null) {
            return true;
        }
        //修改登录页url/请同步修改url拦截放行，否则出现无线拦截/重定向过多
        response.sendRedirect(request.getContextPath() + "/page/console_user_login");
        return false;
    }
}
