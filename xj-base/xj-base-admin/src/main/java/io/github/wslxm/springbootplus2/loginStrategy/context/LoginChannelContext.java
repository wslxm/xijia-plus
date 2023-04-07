package io.github.wslxm.springbootplus2.loginStrategy.context;

import io.github.wslxm.springbootplus2.constant.LoginTypeConst;
import io.github.wslxm.springbootplus2.loginStrategy.service.LoginStrategy;
import io.github.wslxm.springbootplus2.loginStrategy.service.impl.AccountPasswordLoginStrategy;
import io.github.wslxm.springbootplus2.loginStrategy.service.impl.PhonePasswordLoginStrategy;
import io.github.wslxm.springbootplus2.loginStrategy.service.impl.AccountOrPhonePasswordLoginStrategy;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 登录渠道-对外提供类 （策略模式对外提供者）
 *
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2022/10/15 0015 18:31
 * @version 1.0.0
 */
@Component
public class LoginChannelContext {

    /**
     * 渠道集
     */
    private Map<String, LoginStrategy> classMap = null;


    public LoginChannelContext(AccountPasswordLoginStrategy accountPasswordLoginStrategy,
                               PhonePasswordLoginStrategy phonePasswordLoginStrategy,
                               AccountOrPhonePasswordLoginStrategy accountOrPhonePasswordLoginStrategy
    ) {
        // 注册登录渠道
        classMap = new ConcurrentHashMap<>();
        classMap.put(LoginTypeConst.ACCOUNT_PASSWORD, accountPasswordLoginStrategy);
        classMap.put(LoginTypeConst.PHONE_PASSWORD, phonePasswordLoginStrategy);
        classMap.put(LoginTypeConst.ACCOUNT_OR_PHONE_PASSWORD, accountOrPhonePasswordLoginStrategy);
    }


    /**
     * 获取登录渠道
     * @param channelKey
     * @return
     */
    public LoginStrategy getChannel(String channelKey) {
        return classMap.get(channelKey);
    }

    /**
     * 增加渠道
     * @param channelKey
     * @return
     */
    public boolean addChannel(String channelKey, LoginStrategy fileStrategy) {
        classMap.put(channelKey, fileStrategy);
        return true;
    }
}
