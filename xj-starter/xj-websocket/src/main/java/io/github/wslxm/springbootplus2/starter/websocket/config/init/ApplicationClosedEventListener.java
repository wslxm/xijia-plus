package io.github.wslxm.springbootplus2.starter.websocket.config.init;

import io.github.wslxm.springbootplus2.starter.redis.util.RedisUtil;
import io.github.wslxm.springbootplus2.starter.websocket.constant.WebsocketConst;
import io.github.wslxm.springbootplus2.starter.websocket.util.WebsocketSpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

/**
 *
 * 项目完全停止时执行的一些处理操作
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/7/23 0023 9:04
 * @version 1.0.1
 */
@Component
@Slf4j
public class ApplicationClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    private static Logger logger = LoggerFactory.getLogger(ApplicationClosedEventListener.class);

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        // 服务重启清空 reids 在线用户缓存
        RedisUtil redisUtil = WebsocketSpringContextUtil.getBean(RedisUtil.class);
        redisUtil.delete(WebsocketConst.WEBSOCKET_CACHE_KEY);
        logger.info("程序已停止");
    }
}
