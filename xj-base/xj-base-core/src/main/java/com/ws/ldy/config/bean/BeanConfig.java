package com.ws.ldy.config.bean;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.SimpleBeanDefinitionRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


/**
  * 把 SimpleBeanDefinitionRegistry 注册到 bean容器中
  * @author wangsong
  * @mail  1720696548@qq.com
  * @date  2021/7/28 0028 9:18
  * @version 1.0.0
  */
@Configuration
public class BeanConfig {

    @Bean
    @Primary
    public BeanDefinitionRegistry beanDefinitionRegistry() {
        return new SimpleBeanDefinitionRegistry();
    }
}
