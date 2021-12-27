package io.github.wslxm.springbootplus2.manage.gc.service;

import io.github.wslxm.springbootplus2.manage.gc.model.dto.XjGenerateDto;

import java.util.Map;

/**
 *  @author wangsong
 */
public interface XjGenerationSevice {


    /**
     * 生成预览（生成 服务端+layui+vue代码预览）
     *
     * @param generateDto generateDto
     * @return java.util.Map<java.lang.String, java.lang.String>
     * @version 1.0.0
     */
    Map<String, String> preview(XjGenerateDto generateDto);


    /**
     * 生成代码(服务端代码，直接生成到对应目录,注意覆盖问题)
     *
     * @param generateDto generateDto
     * @return java.util.Map<java.lang.String, java.lang.String>
     * @version 1.0.0
     */
    Map<String, String> generateCode(XjGenerateDto generateDto);


    /**
     * 生成 vue 代码(直接下载)
     *
     * @param generateDto generateDto
     * @return void
     * @version 1.0.0
     */
    void generateCodeVue(XjGenerateDto generateDto);


    /**
     * 获取生成路径（服务端+layui）
     *
     * @param tableName tableName
     * @return java.util.Map<java.lang.String, java.lang.String>
     * @version 1.0.0
     */
    Map<String, String> getPath(String tableName);

}
