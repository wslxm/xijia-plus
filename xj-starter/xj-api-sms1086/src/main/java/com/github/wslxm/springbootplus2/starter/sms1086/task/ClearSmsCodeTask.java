package com.github.wslxm.springbootplus2.starter.sms1086.task;

import com.github.wslxm.springbootplus2.starter.sms1086.model.SmsCode;
import com.github.wslxm.springbootplus2.starter.sms1086.util.Sms1086Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ClearSmsCodeTask {


    @Autowired
    private Sms1086Util sms1086Util;

    /**
     * 清理cacheMap中未使用的过期幂等token，每小时1次
     * <P>
     *     30秒1次    【 0/30 * * * * ?】
     *     一小时一次  【 0 0 0/1 * * ?】
     * </P>
     */
    @Scheduled(cron = "0 0 0/1 * * ?")
    private void clearIdempotent() {
        //iterator 遍历清理
        Map<String, SmsCode> cacheMap = sms1086Util.getSmsCache();
        for (Iterator<SmsCode> iterator = cacheMap.values().iterator(); iterator.hasNext(); ) {
            SmsCode smsCode = iterator.next();
            if (smsCode.getTime() - System.currentTimeMillis() < 0) {
                iterator.remove();
            }
        }
        log.info("清理过期验证码共 {} 个", cacheMap.size() - sms1086Util.getSmsCache().size());
    }
}
