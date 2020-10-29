package com.ws.ldy.config.auth.filter;

import com.alibaba.fastjson.JSON;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.config.auth.entity.JwtUser;
import com.ws.ldy.config.auth.util.JwtUtil;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.enums.Enums;
import com.ws.ldy.modules.admin.model.entity.AdminAuthority;
import com.ws.ldy.modules.admin.service.AdminAuthorityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/***
 * 过滤器定义参考： https://www.cnblogs.com/ibigboy/p/11528775.html
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/10/29 0029 17:50
 * @version 1.0.0
 */
@Slf4j
public class JwtAuthFilter implements Filter {

    /**
     * 需要进行接口验证的uri 集, 静态资源, css, js ,路由等等, 只要uri包含以下定义的内容, 将直接跳过改过滤器
     */
    private static List<String> excludeUriList = new ArrayList<String>() {{
        add("/bootAdmin/instances");  // springbootAdmin监控相关
        add("/actuator");             // 系统监控相关
        add("/druid/");               // sql监控相关
        add("/page/");                // 页面跳转(路由)
    }};

    @Autowired
    private AdminAuthorityService adminAuthorityService;


    @Override
    public void init(FilterConfig filterConfig) {
    }

    /**
     * 登录授权
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 记录请求时间,用于后续记录请求耗时,该过滤器执行顺序为最高,会优先执行
        response.setHeader("startTime", System.currentTimeMillis() + "");
        // 1、排除
        String uri = request.getRequestURI();
        for (String excludeUri : excludeUriList) {
            if (uri.contains(excludeUri)) {
                // doFilter将请求转发给过滤器链下一个filter , 如果没有filter那就是你请求的资源
                filterChain.doFilter(servletRequest, servletResponse);
                // 中断程序
                return;
            }
        }
        if (!BaseConstant.Cache.AUTH_MAP.containsKey(uri)) {
            log.info("检测到未被管理的请求uri=" + uri);
        }
        //  2、登录授权认证
        R<JwtUser> result = loginAuth(request, response);
        //  3、判断登录授权结果(失败直接返回,成功调用 doFilter 向下走)
        if (result.getCode().equals(RType.SYS_SUCCESS.getValue())) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            // 返回错误
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(result));
            writer.flush();
            writer.close();
        }
    }


    @Override
    public void destroy() {

    }


    /**
     * 登录授权认证
     * <>
     *     token认证，授权认证，
     *     没有Token 直接放行, 让请求接入权限认证, 需要授权的接口没有token当然是认证不过的啦
     *     需要授权的接口, 在token 中获取当前登录用户的权限, 当前用户没有当前请求的接口权限当然也是认证不过的啦
     *     // ===
     *     前端接口认证：暂无处理
     *     返回：
     *       - 如果需要登录, 返回的jwt存储的用户信息, 用于记录日志, 如果接口不需要登录,返回的 null
     *       - 如果登录过期或无接口权限,返回对应的错误信息，会直接返回到前端
     * </>
     * @param request
     * @param response
     */
    public R<JwtUser> loginAuth(HttpServletRequest request, HttpServletResponse response) {
        // 1、是否为绝对放行接口,是直接放行
        String uri = request.getRequestURI();
        if (BaseConstant.Sys.URIS.contains(uri)) {
            return R.success(null);
        }
        // 2、是否被权限管理, 没有直接放行，log.info("请求方式:{} 请求URL:{} ", request.getMethod(), request.getServletPath());
        if (!BaseConstant.Cache.AUTH_MAP.containsKey(uri)) {
            return R.success(null);
        }
        // 3、接口是否禁用，是直接返回禁用信息
        AdminAuthority adminAuthority = BaseConstant.Cache.AUTH_MAP.get(uri);
        if (adminAuthority.getDisable().equals(Enums.Base.Disable.DISABLE_1.getValue())) {
            //禁用
            return R.error(RType.AUTHORITY_DISABLE);
        }
        // 4、登录/授权验证
        if (adminAuthority.getState().equals(Enums.Admin.AuthorityState.AUTHORITY_STATE_0.getValue())) {
            /**
             *  0- 无需登录 (不做任何处理)
             */
            return R.success(null);
        } else if (adminAuthority.getState().equals(Enums.Admin.AuthorityState.AUTHORITY_STATE_1.getValue())) {
            /**
             *  1- 需登录 (能获取用户信息jwtUser 即成功)
             */
            R<JwtUser> result = JwtUtil.getJwtUserR(request);
            if (!result.getCode().equals(RType.SYS_SUCCESS.getValue())) {
                // error
                return result;
            }
            // 刷新token有效期
            JwtUser jwtUser = result.getData();
            JwtUtil.refreshToken(adminAuthorityService, response, jwtUser);
            return R.success(jwtUser);
        } else if (adminAuthority.getState().equals(Enums.Admin.AuthorityState.AUTHORITY_STATE_2.getValue())) {
            /**
             *  2- 需登录+授权 (100% 管理端才会进入, 验证用户信息的权限列表中是否存在当前接口，存在放行，不存在拦截返回无权限访问)
             */
            R<JwtUser> result = JwtUtil.getJwtUserR(request);
            if (!result.getCode().equals(RType.SYS_SUCCESS.getValue())) {
                // error
                return result;
            }
            // 判断权限
            JwtUser jwtUser = result.getData();
            if (jwtUser.getAuthList() == null || !jwtUser.getAuthList().contains(request.getRequestURI())) {
                return R.error(RType.AUTHORITY_NO_PERMISSION);
            }
            // 刷新token有效期
            JwtUtil.refreshToken(adminAuthorityService, response, jwtUser);
            return R.success(jwtUser);
        }
        return R.success(null);
    }
}