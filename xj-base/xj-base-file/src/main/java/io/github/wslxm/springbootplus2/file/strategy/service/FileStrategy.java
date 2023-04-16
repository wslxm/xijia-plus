package io.github.wslxm.springbootplus2.file.strategy.service;

import java.io.InputStream;

/**
 * 文件策略类 （目前支持 上传/删除）
 * <p>
 * 计划支持
 * - 本地存储
 * - 阿里云oss
 * </P>
 *
 * @author wangsong
 * @version 1.0.0
 * @mail 1720696548@qq.com
 * @date 2022/10/15 0015 18:11
 */
public interface FileStrategy {

    /**
     * 文件上传
     *
     * @param inputStream 上传的文件流
     * @param filePath    文件存储路径 (子路径)
     * @param fileName    文件名(已处理防重后的文件名)
     * @return
     */
    String upload(InputStream inputStream, String filePath, String fileName);


    /**
     * 删除文件
     * <p>
     * filePath 支持传递:
     * 1、可访问的完整路径地址 如：http://xijia-sz.oss-cn-shenzhen.aliyuncs.com/oss/file/image/20230117-3085-14.jpeg
     * 2、存储路径地址 如：oss/file/image/20230117-3085-14.jpeg
     * <p>
     *
     * @param filePath 路径地址
     * @return
     */
    boolean del(String filePath);


    /**
     * 删除指定目录的所有文件
     *
     * @param filePath 路径: 如 oss/file/image/test
     * @return
     */
    boolean delFolder(String filePath);

}
