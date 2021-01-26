package com.ws.ldy.modules.third.aliyun.sms.task;

import com.ws.ldy.modules.third.aliyun.sms.smsConstant.SmsCode;
import com.ws.ldy.modules.third.aliyun.sms.util.AliSmsUtil;
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
    private AliSmsUtil aliSmsUtil;

    /**
     * 清理cacheMap中未使用的过期幂等token，每小时1次
     */
    @Scheduled(cron = "0 0 0/1 * * ?")
    private void clearIdempotent() {
        //iterator 遍历清理
        Map<String, SmsCode> cacheMap = aliSmsUtil.getSmsCache();
        for (Iterator<SmsCode> iterator = cacheMap.values().iterator(); iterator.hasNext(); ) {
            SmsCode smsCode = iterator.next();
            if (smsCode.getTime() - System.currentTimeMillis() < 0) {
                iterator.remove();
            }
        }
        log.info("清理过期验证码共 {} 个", cacheMap.size() - aliSmsUtil.getSmsCache().size());
    }
}
