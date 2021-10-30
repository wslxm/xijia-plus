package io.github.wslxm.springbootplus2.manage.gc.service;

import io.github.wslxm.springbootplus2.manage.gc.model.dto.XjGenerateDto;

import java.util.Map;

public interface XjGenerationSevice {


    /**
     * 生成预览（生成 服务端+layui+vue代码预览）
     * @param generateDto
     * @return
     */
    Map<String, String> preview(XjGenerateDto generateDto);

    /**
     * 生成代码(服务端代码，直接生成到对应目录,注意覆盖问题)
     * @param generateDto
     * @return
     */
    Map<String, String> generateCode(XjGenerateDto generateDto);

    /**
     * 生成 vue 代码(直接下载)
     * @param generateDto
     * @return
     */
    void generateCodeVue(XjGenerateDto generateDto);

    /**
     * 获取生成路径（服务端+layui）
     * @param generateDto
     * @return
     */
    Map<String, String> getPath(String tableName);

}
