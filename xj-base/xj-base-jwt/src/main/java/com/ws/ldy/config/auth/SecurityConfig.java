//package com.ws.ldy.config.auth;
//
//import com.ws.ldy.modules.admin.service.AdminAuthorityService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.web.cors.CorsUtils;
//import org.springframework.web.servlet.HandlerExceptionResolver;
//
///**
// * Security配置文件，项目启动时就加载了
// *
// * @date 2020/7/5 0005 20:44
// * @return
// */
//@Configuration
//@Slf4j
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    /**
//     * 异常处理类,在 filter无法使用全局异常,在 .addFilter(new JWTValidFilter 中传递该对象过去，便于返回异常信息
//     */
//    @Autowired
//    @Qualifier("handlerExceptionResolver")
//    private HandlerExceptionResolver resolver;
//
//
//    @Autowired
//    private AdminAuthorityService adminAuthorityService;
//
//    // 认证，不需要使用它的认证方法，使用自己的 Login接口authenticationProvider
//
//    /**
//     *  授权
//     *
//     * @param http
//     * @throws Exception
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        // 缓存httpSecurity，让权限数据被修改后可动态刷新
//        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry eiur = http.authorizeRequests();
//
//        // 加载权限到SecurityConfig中，SecurityConfig权限管理太繁琐,自己重写
//        // eiur.antMatchers(auth.getUrl()).hasAnyAuthority(auth.getUrl());
//
//        // 加载权限SecurityConfig中的接口必须配置放行, 不然没登录是无法访问的
//        // eiur.antMatchers(uri).permitAll();
//
//        // 设置登录/ 授权过滤器
//        eiur.and().addFilter(new JWTValidFilter(authenticationManager(), resolver,adminAuthorityService));
//        // 剔除 session
//        eiur.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        // 开启跨域访问
//        http.cors(); //.disable();
//        // 开启模拟请求，比如API POST测试工具的测试，不开启时，API POST为报403错误
//        http.csrf().disable();
//        // iframe 跳转错误处理 Refused to display 'url' in a frame because it set 'X-Frame-Options' to 'deny'
//        http.headers().frameOptions().disable();
//        // 当出现跨域的OPTIONS请求时，发现被拦截，加入下面设置可实现对OPTIONS请求的放行。
//        http.authorizeRequests().requestMatchers(CorsUtils::isPreFlightRequest).permitAll();
//    }
//
//
//    /**
//     * There is no PasswordEncoder mapped for the id "null"
//     * 原因:升级为Security5.0以上密码支持多中加密方式，恢复以前模式
//     *
//     * @return
//     */
////    @Bean
////    public static NoOpPasswordEncoder passwordEncoder() {
////        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
////    }
//}