//package io.github.wslxm.springbootplus2.redis.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.listener.RedisMessageListenerContainer;
//
///***
// * redis key过期监听器注入bean
// * @author wangsong
// * @mail 1720696548@qq.com
// * @date 2021/3/2 0002 17:04
// * @version 1.0.1
// */
//@Configuration
//public class RedisListenerConfig {
//
//    @Bean
//    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
//
//        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        return container;
//    }
//}
