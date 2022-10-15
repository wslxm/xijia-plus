package io.github.wslxm.springbootplus2.manage.file.strategy;

import io.github.wslxm.springbootplus2.manage.file.constant.FileChannel;
import io.github.wslxm.springbootplus2.manage.file.strategy.impl.AliYunOssFileStrategy;
import io.github.wslxm.springbootplus2.manage.file.strategy.impl.LocalFileStrategy;
import org.springframework.beans.factory.annotation.Autowired;
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
public class FileContext {

    /**
     * 渠道集
     */
    private Map<String, FileStrategy> classMap = null;

    @Autowired
    private AliYunOssFileStrategy aliYunOssFileStrategy;
    @Autowired
    private LocalFileStrategy localFileStrategy;

    /**
     * 注册渠道
     */
    public void init() {
        if (classMap == null) {
            classMap = new ConcurrentHashMap<>();
            classMap.put(FileChannel.ALI_YUN_OSS, aliYunOssFileStrategy);
            classMap.put(FileChannel.LOCAL, localFileStrategy);
        }
    }

    /**
     * 获取文件上传渠道
     * @param channelKey
     * @return
     */
    public FileStrategy getChannel(String channelKey) {
        this.init();
        return classMap.get(channelKey);
    }

}
