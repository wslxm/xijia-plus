package io.github.wslxm.springbootplus2.starter.websocket.config.init;

import io.github.wslxm.springbootplus2.starter.redis.util.RedisUtil;
import io.github.wslxm.springbootplus2.starter.websocket.server.WebsocketServer;
import io.github.wslxm.springbootplus2.starter.websocket.util.WebsocketSpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * 项目完全启动成功后的执行的一些处理操作
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/7/23 0023 9:04
 * @version 1.0.1
 */
@Component
@Slf4j
public class WebsocketRunnerImpl implements CommandLineRunner {


    @Override
    public void run(String... args) {
        // 服务重启让所有用户掉线
        RedisUtil redisUtil = WebsocketSpringContextUtil.getBean(RedisUtil.class);
        redisUtil.delete(WebsocketServer.WEBSOCKET_CACHE_KEY);
    }
}