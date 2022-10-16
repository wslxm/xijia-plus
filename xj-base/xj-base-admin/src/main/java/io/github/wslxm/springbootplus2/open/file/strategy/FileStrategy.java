package io.github.wslxm.springbootplus2.open.file.strategy;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传策略类
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
     * @param file     上传的文件
     * @param filePath 文件存储路径 (子路径)
     * @param fileName 文件名(已处理防重后的文件名)
     * @return
     */
    public String upload(MultipartFile file, String filePath, String fileName);


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
