package com.ws.ldy.baseadmin.config;

import com.ws.ldy.admincore.common.Enum.ErrorConstantEnum;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * TODO  登录拦截处理(后台通用模板)
 *
 * @author 王松
 * @mail 1720696548@qq.com
 * @date 2020/1/13 0013 20:20
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("user");
        //访问接口
        String servletPath = request.getServletPath();
        if (servletPath.equals("/") || servletPath.equals("") ) {
            response.sendRedirect(request.getContextPath() + "/page/console_user_login");
            return false;
        }
        //已登录,放行
        if (user != null) {
            return true;
        }
        // Admin后台管理未登陆（如前端调用返回此信息,请查看调用接口是否为api或open开头）
        response.sendRedirect(request.getContextPath() + "/error/" + ErrorConstantEnum.ADMIN_IS_NO_LOGIN.getCode());
        return false;
        //修改登录页url/请同步修改url拦截放行，否则出现无线拦截/重定向过多
        //response.sendRedirect(request.getContextPath() + "/page/console_user_login");
    }
}
