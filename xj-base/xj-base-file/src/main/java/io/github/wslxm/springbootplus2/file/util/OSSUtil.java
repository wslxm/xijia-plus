/**
 * 示例说明
 * <p>
 * HelloOSS是OSS Java SDK的示例程序，您可以修改endpoint、accessKeyId、accessKeySecret、bucketName后直接运行。
 * 运行方法请参考README。
 * <p>
 * 本示例中的并不包括OSS Java SDK的所有功能，详细功能及使用方法，请参看“SDK手册 > Java-SDK”，
 * 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/preface.html?spm=5176.docoss/sdk/java-sdk/。
 * <p>
 * 调用OSS Java SDK的方法时，抛出异常表示有错误发生；没有抛出异常表示成功执行。
 * 当错误发生时，OSS Java SDK的方法会抛出异常，异常中包括错误码、错误信息，详细请参看“SDK手册 > Java-SDK > 异常处理”，
 * 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/exception.html?spm=5176.docoss/api-reference/error-response。
 * <p>
 * OSS控制台可以直观的看到您调用OSS Java SDK的结果，OSS控制台地址是：https://oss.console.aliyun.com/index#/。
 * OSS控制台使用方法请参看文档中心的“控制台用户指南”， 指南的来链接地址是：https://help.aliyun.com/document_detail/oss/getting-started/get-started.html?spm=5176.docoss/user_guide。
 * <p>
 * OSS的文档中心地址是：https://help.aliyun.com/document_detail/oss/user_guide/overview.html。
 * OSS Java SDK的文档地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/install.html?spm=5176.docoss/sdk/java-sdk。
 */
/**
 * 示例说明
 * <p>
 * HelloOSS是OSS Java SDK的示例程序，您可以修改endpoint、accessKeyId、accessKeySecret、bucketName后直接运行。
 * 运行方法请参考README。
 * <p>
 * 本示例中的并不包括OSS Java SDK的所有功能，详细功能及使用方法，请参看“SDK手册 > Java-SDK”，
 * 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/preface.html?spm=5176.docoss/sdk/java-sdk/。
 * <p>
 * 调用OSS Java SDK的方法时，抛出异常表示有错误发生；没有抛出异常表示成功执行。
 * 当错误发生时，OSS Java SDK的方法会抛出异常，异常中包括错误码、错误信息，详细请参看“SDK手册 > Java-SDK > 异常处理”，
 * 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/exception.html?spm=5176.docoss/api-reference/error-response。
 * <p>
 * OSS控制台可以直观的看到您调用OSS Java SDK的结果，OSS控制台地址是：https://oss.console.aliyun.com/index#/。
 * OSS控制台使用方法请参看文档中心的“控制台用户指南”， 指南的来链接地址是：https://help.aliyun.com/document_detail/oss/getting-started/get-started.html?spm=5176.docoss/user_guide。
 * <p>
 * OSS的文档中心地址是：https://help.aliyun.com/document_detail/oss/user_guide/overview.html。
 * OSS Java SDK的文档地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/install.html?spm=5176.docoss/sdk/java-sdk。
 */

package io.github.wslxm.springbootplus2.file.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import io.github.wslxm.springbootplus2.file.config.AliYunOssProperties;
import io.github.wslxm.springbootplus2.file.config.FileProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;

/**
 *  阿里云 OSS
 *  endpoint是访问OSS的域名。如果您已经在OSS的控制台上 创建了Bucket，请在控制台上查看域名。
 *  上传使用外网 endpoint
 *  下载使用内网 endpoint（只有在同地区下的阿里云服务器才可以访问）
 *  <p>
 *  // 上传
 *  外网流入流量免费 （上传免费）
 *  内网流入流量免费 （上传免费）
 *  // 下载
 *  外网流出流量免费（按量计费, 闲是2.5毛 1GB，忙是 5毛1GB）
 *  内网流出流量免费（下载访问免费，同地域阿里云服务器可使用内网）
 *
 * @author wangsong
 * @date 2020/12/11 0011 16:05
 * @return
 * @version 1.0.1
 */
@Slf4j
@Service
public class OSSUtil {

    /**
     * 文件一级目录, 文件保存到oss的路径,
     */
    //private static final String FILE_PATH = "oss/file/";

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private FileProperties fileProperties;

    /**
     * 文件客户端对象
     */
    private OSS ossClient = null;


    /**
     * 初始化对象
     */
    private void init() {
        // 创建ossClient
        if (ossClient == null) {
            AliYunOssProperties aliYunOssProperties = fileProperties.getAliyunOss();
            ossClient = new OSSClientBuilder().build(aliYunOssProperties.getEndpoint(), aliYunOssProperties.getAccessKeyId(), aliYunOssProperties.getAccessKeySecret());
        }
    }

    /**
     * 上传文件到oss/file 目录下
     * <p>
     * 链接地址：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/upload_object.html?spm=5176.docoss/user_guide/upload_object
     * </P>
     *
     * @param filePath  文件保存到的二级目录
     * @param fileName  文件名称
     * @param inputStream  文件流
     * @return
     */
    public String upload(String uploadPath,String filePath, String fileName, InputStream inputStream) {
        this.init();

        // 表示上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        String yourObjectName = uploadPath + filePath + fileName;
        // 创建PutObjectRequest对象。
        AliYunOssProperties aliYunOssProperties = fileProperties.getAliyunOss();
        PutObjectRequest putObjectRequest = new PutObjectRequest(aliYunOssProperties.getBucketName(), yourObjectName, inputStream);
        // 设置 ContentType 类型 ,防止图片等资源无法使用url直接访问，没有对应格式时,不处理，使用文件对应的默认格式
        ObjectMetadata metadata = new ObjectMetadata();
        String suffixName = yourObjectName.substring(yourObjectName.lastIndexOf("."));
        String contentType = this.getContentType(suffixName);
        if (contentType != null) {
            metadata.setContentType(contentType);
        }
        putObjectRequest.setMetadata(metadata);
        // 开始上传
        ossClient.putObject(putObjectRequest);
        log.info("上传-" + yourObjectName + " 成功");
        // 关闭OSSClient。
        // ossClient.shutdown();
        // 访问地址= 当前服务器域名 + oss存储路径
        return aliYunOssProperties.getBucket() + "/" + yourObjectName;
    }


    /**
     * 获取OSS 文件列表
     */
    public List<OSSObjectSummary> getObjectListing() {
        this.init();
        AliYunOssProperties aliYunOssProperties = fileProperties.getAliyunOss();
        ObjectListing objectListing = ossClient.listObjects(aliYunOssProperties.getBucketName());
        List<OSSObjectSummary> objectSummary = objectListing.getObjectSummaries();
        // 关闭OSSClient。
        // ossClient.shutdown();
        return objectSummary;
    }


    /**
     * 删除 或 删除文件夹 --> 如: file/
     */
    public boolean deleteObject(String firstKey) {
        this.init();
        // 创建PutObjectRequest对象。
        AliYunOssProperties aliYunOssProperties = fileProperties.getAliyunOss();
        ossClient.deleteObject(aliYunOssProperties.getBucketName(), firstKey);
        log.info("删除Object：" + firstKey + "成功。");
        // 关闭OSSClient。
        // ossClient.shutdown();
        return true;
    }


    /**
     *  1、获取保存后的文件类型
     *  <P>  阿里云,处理文件格式，不处理可能导致文件无法直接访问 </P>
     *  --- 1、如果后缀不在以下范围内，返回null,保存上传文件的格式
     *  --- 2、图片统一服务端统一保存为： image/jpg
     *
     * @param filenameExtension
     * @return
     */
    private String getContentType(String filenameExtension) {
        if (filenameExtension.equalsIgnoreCase(".bmp")) {
            return "image/bmp";
        }
        if (filenameExtension.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }
        if (filenameExtension.equalsIgnoreCase(".jpeg") ||
                filenameExtension.equalsIgnoreCase(".jpg") ||
                filenameExtension.equalsIgnoreCase(".png")) {
            return "image/jpg";
        }
        if (filenameExtension.equalsIgnoreCase(".html")) {
            return "text/html";
        }
        if (filenameExtension.equalsIgnoreCase(".txt")) {
            return "text/plain";
        }
        if (filenameExtension.equalsIgnoreCase(".vsd")) {
            return "application/vnd.visio";
        }
        if (filenameExtension.equalsIgnoreCase(".pptx") ||
                filenameExtension.equalsIgnoreCase(".ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (filenameExtension.equalsIgnoreCase(".docx") ||
                filenameExtension.equalsIgnoreCase(".doc")) {
            return "application/msword";
        }
        if (filenameExtension.equalsIgnoreCase(".xml")) {
            return "text/xml";
        }
        if (filenameExtension.equalsIgnoreCase(".mp4")) {
            return "video/mp4";
        }
        if (filenameExtension.equalsIgnoreCase(".mp3")) {
            return "audio/mp3";
        }
        if (filenameExtension.equalsIgnoreCase(".pdf")) {
            return " application/pdf";
        }
        return null;
    }
}
