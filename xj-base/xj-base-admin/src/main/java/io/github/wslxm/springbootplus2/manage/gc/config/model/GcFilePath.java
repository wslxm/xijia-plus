package io.github.wslxm.springbootplus2.manage.gc.config.model;

import lombok.Data;

/**
 * 代码生成位置/ 模板位置存储对象
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2022/8/22 0022 20:48
 * @version 1.0.0
 */
@Data
public class GcFilePath {
    /**
     * 模板名称
     */
    private String name;
    /**
     * 模板位置
     */
    private String templatePath;
    /**
     * 生成文件位置
     */
    private String path;
}