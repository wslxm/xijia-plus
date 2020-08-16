package com.ws.ldy.config.auth.jwt.filter;

import com.alibaba.fastjson.JSON;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.config.auth.springSecurity.entity.SecurityUser;
import com.ws.ldy.config.auth.util.JwtUtil;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.enums.BaseConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/***
 * 登录  ===> POST请求（ 账号:username=?, 密码：password=?）
 *
 * 登录会调用springSecurity的登录方法进行验证
 *<p>
 * ===== 登录成功
 * http状态status状态返回200，并且自定义响应状态code返回200，响应头存放token，key = token，value = jwt生成的token内容
 * ===== 登录失败
 * http状态status状态返回401，并且自定义响应状态code返回401，并提示对应的内容
 * ===== 权限不足
 *  http状态status状态返回403，并且自定义响应状态code返回403，并提示对应的内容
 * </p>
 * @author 王松
 * @mail 1720696548@qq.com
 * @date 2020/7/5 0005 17:29
 */
public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    /**
     * 获取授权管理, 创建JWTLoginFilter时获取
     */
    private AuthenticationManager authenticationManager;

    /**
     * 异常处理类
     */
    private HandlerExceptionResolver resolver;


    /**
     * 创建JWTLoginFilter,构造器，定义后端登陆接口-【/auth/login】，当调用该接口直接执行 attemptAuthentication 方法
     *
     * @param authenticationManager
     */
    public JWTLoginFilter(AuthenticationManager authenticationManager, HandlerExceptionResolver resolver) {
        this.authenticationManager = authenticationManager;
        this.resolver = resolver;
        super.setFilterProcessesUrl("/auth/login");
    }


    /**
     * 一旦调用登录接口 /auth/login，立即执行该方法
     *
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @SuppressWarnings("unchecked")
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        SecurityUser user = null;
        Map<String, String> map = null;
        try {
            // 封装请求参数到 map 中
            StringBuffer data = new StringBuffer();
            String line = null;
            BufferedReader reader = request.getReader();
            while (null != (line = reader.readLine())) {
                data.append(line);
            }
            map = JSON.parseObject(data.toString(), Map.class);
        } catch (Exception e) {
            resolver.resolveException(request, response, null, new ErrorException(RType.SYSTEM_PARAMETER_IS_NO));
            return null;
        }
        // 获取请求参数
        String username = map.get("username");
        String password = map.get("password");
        // 参数判断
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            resolver.resolveException(request, response, null, new ErrorException(RType.SYSTEM_PARAMETER_IS_NO));
            return null;
        }
        // 调用springSecurity的 XiJiaUserDetailsServiceImpl 的 loadUserByUsername 方法进行登录认证，传递账号密码
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>()));
    }


    /**
     * 一旦调用 springSecurity认证登录成功，立即执行该方法
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {

        // 生成jwt 放入 Header
        SecurityUser userEntity = (SecurityUser) authResult.getPrincipal();
        String jwtToken = JwtUtil.generateToken(userEntity);
        response.addHeader(BaseConstant.Sys.TOKEN, jwtToken);

        // 响应
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out = response.getWriter();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 200);
        map.put("message", "登录成功!");
        out.write(JSON.toJSONString(map));
        out.flush();
        out.close();
    }


    /**
     *  一旦调用 springSecurity认证失败 ，立即执行该方法
     *
     * @param request
     * @param response
     * @param ex
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) {
        if (ex instanceof UsernameNotFoundException || ex instanceof BadCredentialsException) {
            // 账号密码错误
            resolver.resolveException(request, response, null, new ErrorException(RType.LOGIN_ERROR_USER_PASSWORD));
        } else if (ex instanceof InternalAuthenticationServiceException) {
            // 账号不存在
            resolver.resolveException(request, response, null, new ErrorException(RType.LOGIN_IS_NO_ACCOUNT));
        } else if (ex instanceof DisabledException) {
            // 账号禁用
            resolver.resolveException(request, response, null, new ErrorException(RType.LOGIN_IS_NO_DISABLE));
        } else {
            // 登录失败
            resolver.resolveException(request, response, null, new ErrorException(RType.LOGIN_FAILED));
        }
    }
}