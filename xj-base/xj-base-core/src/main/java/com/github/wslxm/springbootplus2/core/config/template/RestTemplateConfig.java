package com.github.wslxm.springbootplus2.core.config.template;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * restTemplate注入springboot容器
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/18 20:43
 */
@Configuration
public class RestTemplateConfig {


    //    @Bean
    //    public RestTemplate restTemplate() {
    //        return new RestTemplate();
    //    }


    /**
     * RestTemplate =  rest,http风格的,api调用工具
     * <P>
     * 使用：
     * @Autowired private RestTemplate restTemplate;
     * String result = restTemplate.getForObject(memberUrl, String.class);
     * </P>
     *

     */
    @Bean
    //@LoadBalanced
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> list = restTemplate.getMessageConverters();
        for (HttpMessageConverter<?> httpMessageConverter : list) {
            if (httpMessageConverter instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter) httpMessageConverter).setDefaultCharset(Charset.forName(StandardCharsets.UTF_8.toString()));
                break;
            }
        }
        return new RestTemplate();
    }
}
