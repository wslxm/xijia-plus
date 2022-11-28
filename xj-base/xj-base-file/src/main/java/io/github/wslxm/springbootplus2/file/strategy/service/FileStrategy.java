package io.github.wslxm.springbootplus2.file.strategy.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * 文件策略类 （目前支持 上传/删除）
 * <P>
 *     计划支持
 *     - 本地存储
 *     - 阿里云oss
 * </P>
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2022/10/15 0015 18:11 
 * @version 1.0.0
 */
public interface FileStrategy {


    /**
     * 文件上传
     * @param inputStream 上传的文件流
     * @param filePath 文件存储路径 (子路径)
     * @param fileName 文件名(已处理防重后的文件名)
     * @return
     */
    public String upload(InputStream inputStream, String filePath, String fileName);


    /**
     * 指定文件 或 指定文件夹以及下级所有数据 删除
     * <P>
     *     本地文件已支持删除文件或文件夹
     *     阿里云oss目前只支持删除文件
     * </P>
     * @param filePath 文件/文件夹 路径跟目录到文件的完整路径地址
     * @return
     */
    public Boolean del(String filePath);

}
