package io.github.wslxm.springbootplus2.common.idempotent.task;

import io.github.wslxm.springbootplus2.common.idempotent.util.XJIdempotentUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;

/**
 * 清理过期幂等token
 * @author wangsong
 * @date 2020/12/29 0029 13:25
 * @return
 * @version 1.0.0
 */
@Component
@Configuration
@EnableScheduling
@Slf4j
public class ClearIdempotentTask {


    /**
     * 清理cacheMap中未使用的过期幂等token，每小时1次
     */
    @Scheduled(cron = "0 0 0/1 * * ?")
    private void clearIdempotent() {
        //iterator 遍历清理
        Map<String, Long> cacheMap = XJIdempotentUtils.getCacheMap();
        for (Iterator<Long> iterator = cacheMap.values().iterator(); iterator.hasNext(); ) {
            Long expiredTime = iterator.next();
            if (expiredTime - System.currentTimeMillis() < 0) {
                iterator.remove();
            }
        }
        Map<String, Long> cacheMapTwo = XJIdempotentUtils.getCacheMap();
        log.info("清理过期幂等token共 {} 个", cacheMap.size() - cacheMapTwo.size());
    }
}
