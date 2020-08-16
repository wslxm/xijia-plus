package com.ws.ldy.config.auth.springSecurity.config;

import com.ws.ldy.config.auth.jwt.filter.JWTLoginFilter;
import com.ws.ldy.config.auth.jwt.filter.JWTValidFilter;
import com.ws.ldy.config.auth.springSecurity.service.impl.XiJiaUserDetailsServiceImpl;
import com.ws.ldy.config.auth.util.MD5Util;
import com.ws.ldy.modules.admin.model.entity.AdminAuthority;
import com.ws.ldy.modules.admin.service.AdminAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.List;

/**
 *    Security配置文件，项目启动时就加载了
 *
 * @date 2020/7/5 0005 20:44
 * @return
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 登录-认证方法->loadUserByUsername
    @Autowired
    private XiJiaUserDetailsServiceImpl xiJiaUserDetailsService;


    // 异常处理类,在 filter无法使用全局异常,在 .addFilter(new JWTValidFilter 中传递该对象过去，便于返回异常信息
    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    // 当前系统权限表
    @Autowired
    private AdminAuthorityService adminAuthorityService;

    /**
     * 认证
     *
     * @return
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        //对默认的UserDetailsService进行覆盖
        authenticationProvider.setUserDetailsService(xiJiaUserDetailsService);
        authenticationProvider.setPasswordEncoder(new PasswordEncoder() {

            // 对密码MD5
            @Override
            public String encode(CharSequence rawPassword) {
                return MD5Util.encode((String) rawPassword);
            }

            // 判断密码是否正确, rawPassword 用户输入的密码,  encodedPassword 数据库DB的密码,当 XiJiaUserDetailsServiceImpl的loadUserByUsername方法执行完后执行
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return MD5Util.encode((String) rawPassword).equals(encodedPassword);
            }
        });
        return authenticationProvider;
    }


    /**
     *  授权
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 启动项目时刷新权限接口
        adminAuthorityService.refreshAuthority();
        // 只拦截需要拦截的所有接口, 拦截数据库权限表中的所有接口
        List<AdminAuthority> authoritys = adminAuthorityService.list(null);
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry eiur = http.authorizeRequests();
        authoritys.forEach((auth) -> {
            eiur.antMatchers(auth.getUrl()).hasAnyAuthority(auth.getUrl());
        });
        // 配置token验证及登录认证,过滤器
        eiur
                // 登录接口不需要权限控制,可删除该行配置，目前该接口不在权限列表中
                .antMatchers("/auth/login", "POST").permitAll()
                // 设置JWT过滤器
                .and()
                .addFilter(new JWTValidFilter(authenticationManager(), resolver))
                .addFilter(new JWTLoginFilter(authenticationManager(), resolver)).csrf().disable()
                // 剔除session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 开启跨域访问
        http.cors(); //.disable();
        // 开启模拟请求，比如API POST测试工具的测试，不开启时，API POST为报403错误
        http.csrf().disable();
        // iframe 跳转错误处理 Refused to display 'url' in a frame because it set 'X-Frame-Options' to 'deny'
        http.headers().frameOptions().disable();
        // 当出现跨域的OPTIONS请求时，发现被拦截，加入下面设置可实现对OPTIONS请求的放行。
        http.authorizeRequests().
                requestMatchers(CorsUtils::isPreFlightRequest).
                permitAll();
    }


    /**
     * There is no PasswordEncoder mapped for the id "null"
     * 原因:升级为Security5.0以上密码支持多中加密方式，恢复以前模式
     *
     * @return
     */
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}