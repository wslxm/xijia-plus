package io.github.wslxm.springbootplus2.open.file.strategy;

import io.github.wslxm.springbootplus2.open.file.strategy.impl.LocalFileStrategy;
import org.springframework.stereotype.Component;

/**
  * 增加文件管理渠道配置类
  * @author wangsong
  * @mail  1720696548@qq.com
  * @date  2022/10/16 0016 19:29
  * @version 1.0.0
  */
@Component
public class FileChannelConfig {

    public FileChannelConfig(FileContext fileContext, LocalFileStrategy qiniuOssFileStrategy) {
        fileContext.addChannel("QI-NIU", qiniuOssFileStrategy);
    }
}
