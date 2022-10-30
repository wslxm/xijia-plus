package io.github.wslxm.springbootplus2.file.strategy.context;

import io.github.wslxm.springbootplus2.file.constant.FileChannel;
import io.github.wslxm.springbootplus2.file.strategy.service.FileStrategy;
import io.github.wslxm.springbootplus2.file.strategy.service.impl.AliYunOssFileStrategy;
import io.github.wslxm.springbootplus2.file.strategy.service.impl.LocalFileStrategy;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 文件渠道注册-对外提供类 （策略模式对外提供者）
 *
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2022/10/15 0015 18:31
 * @version 1.0.0
 */
@Component
public class FileChannelContext {

    /**
     * 渠道集
     */
    private Map<String, FileStrategy> classMap = null;


    public FileChannelContext(AliYunOssFileStrategy aliYunOssFileStrategy, LocalFileStrategy localFileStrategy) {
        // 注册渠道
        classMap = new ConcurrentHashMap<>();
        classMap.put(FileChannel.ALI_YUN_OSS, aliYunOssFileStrategy);
        classMap.put(FileChannel.LOCAL, localFileStrategy);
    }


    /**
     * 获取文件上传渠道
     * @param channelKey
     * @return
     */
    public FileStrategy getChannel(String channelKey) {
        return classMap.get(channelKey);
    }

    /**
     * 增加渠道
     * @param channelKey
     * @return
     */
    public boolean addChannel(String channelKey, FileStrategy fileStrategy) {
        classMap.put(channelKey, fileStrategy);
        return true;
    }
}
