package io.github.wslxm.springbootplus2.core.config.cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 跨域配置
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2021/8/16 0016 17:43 
 * @version 1.0.1
 */
@Configuration
public class CorsConfig {

    /**
     * 跨域处理
     * @author wangsong
     * @mail 1720696548@qq.com
     * @date 2020/10/29 0029 19:16 
     * @version 1.0.1
     */
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true); /*是否允许请求带有验证信息*/
        corsConfiguration.addAllowedOrigin("*");     /*允许访问的客户端域名*/
        corsConfiguration.addAllowedHeader("*");     /*允许服务端访问的客户端请求头*/
        corsConfiguration.addAllowedMethod("*");     /*允许访问的方法名,GET POST等*/
        corsConfiguration.addExposedHeader("token"); /*暴露哪些头部信息 不能用*因为跨域访问默认不能获取全部头部信息*/
        corsConfiguration.addExposedHeader("TOKEN");
        corsConfiguration.addExposedHeader("Authorization");
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
