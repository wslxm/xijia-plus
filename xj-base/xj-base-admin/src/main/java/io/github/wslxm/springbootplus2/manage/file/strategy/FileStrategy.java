package io.github.wslxm.springbootplus2.manage.file.strategy;

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
     * 文件列表获取
     * @return
     */
    public Object fileList();


    /**
     * 文件或文件夹 删除
     * @param filePath 文件/文件夹 路径
     * @return
     */
    public Object del(String filePath);

}
