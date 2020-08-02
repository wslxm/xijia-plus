package com.ws.ldy.config.auth.jwt.filter;

import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.config.auth.util.JwtUtil;
import com.ws.ldy.enums.base.BaseConstant;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 *  jwt --> token 信息认证
 *
 * @author 王松
 * @mail 1720696548@qq.com
 * @date 2020/7/5 0005 17:29
 */
@Slf4j
public class JWTValidFilter extends BasicAuthenticationFilter {

    // 异常处理类
    private HandlerExceptionResolver resolver;

    /**
     * SecurityConfig 配置中创建该类实例
     */
    public JWTValidFilter(AuthenticationManager authenticationManager, HandlerExceptionResolver resolver) {
        // 获取授权管理
        super(authenticationManager);
        // 获取异常处理类
        this.resolver = resolver;
    }


    /**
     * 拦截请求
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("请求方式:{} 请求URL:{} ", request.getMethod(), request.getServletPath());
        // 获取token, 没有token直接放行
        String token = request.getHeader(BaseConstant.Sys.TOKEN);
        if (StringUtils.isBlank(token) || "null".equals(token)) {
            super.doFilterInternal(request, response, chain);
            return;
        }
        // 有token进行权限验证
        List<SimpleGrantedAuthority> userAuthList = null;
        String username = null;
        try {
            //  权限列表
            userAuthList = JwtUtil.getUserAuth(token);
            //  获取账号
            username = JwtUtil.getUsername(token);
        } catch (SignatureException ex) {
            resolver.resolveException(request, response, null, new ErrorException(10000, "JWT签名与本地计算签名不匹配"));
            return;
        } catch (ExpiredJwtException ex) {
            resolver.resolveException(request, response, null, new ErrorException(10000, "登录过期"));
            return;
        } catch (Exception e) {
            resolver.resolveException(request, response, null, new ErrorException(10000, "JWT解析错误"));
            return;
        }
        //  添加账户的权限信息，和账号是否为空，然后保存到Security的Authentication授权管理器中
        if (StringUtils.isNotBlank(username) && userAuthList != null) {
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username, null, userAuthList));
        }
        // 执行成功，向下走
        super.doFilterInternal(request, response, chain);
    }
}