package com.ws.ldy.config.mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * restTemplate注入springboot容器
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/18 20:43
 */
@Configuration
public class RestTemplateConfig {

    /**
     * RestTemplate =  rest,http风格的,api调用工具
     * 使用
     *
     * @Autowired private RestTemplate restTemplate;
     * String result = restTemplate.getForObject(memberUrl, String.class);
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
