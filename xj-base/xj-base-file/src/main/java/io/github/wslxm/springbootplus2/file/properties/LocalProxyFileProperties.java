package io.github.wslxm.springbootplus2.file.properties;

import lombok.Data;

/**
 * 文件代理配置
 @author wangsong
 @date 2023/04/06
 */
@Data
public class LocalProxyFileProperties {

    /**
     * 文件操作 代理地址 (上传/删除等)
     */
    private String url;
}
