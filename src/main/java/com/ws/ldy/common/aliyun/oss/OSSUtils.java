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

package com.ws.ldy.common.aliyun.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
import com.ws.ldy.common.result.ResultEnum;
import com.ws.ldy.config.error.ErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Slf4j
@Service
//@Order(999)
public class OSSUtils {


    /**
     * endpoint是访问OSS的域名。如果您已经在OSS的控制台上 创建了Bucket，请在控制台上查看域名。
     * 上传使用外网 endpoint
     * 下载使用内网 endpoint（只有在同地区下的阿里云服务器才可以访问）
     * <p>
     * // 上传
     * 外网流入流量免费 （上传免费）
     * 内网流入流量免费 （上传免费）
     * // 下载
     * 外网流出流量免费（按量计费, 闲是2.5毛 1GB，忙是 5毛1GB）
     * 内网流出流量免费（下载访问免费，同地域阿里云服务器可使用内网）
     */
    // 阿里云下oss 接口访问地址
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    // 阿里云下oss 的 accessKeyId和accessKeySecret(访问密钥，您可以在控制台上创建和查看)
    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;

    //阿里云oss下bucketName
    @Value("${aliyun.oss.bucketName}")
    private String bucketName;


    /**
     * TODO 上传文件到oss/file 目录下
     * <p>
     * 链接地址：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/upload_object.html?spm=5176.docoss/user_guide/upload_object
     * </P>
     *
     * @param yourObjectName 表示上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
     * @param inputStream    文件流
     * @return
     */
    public boolean upload(String yourObjectName, InputStream inputStream) {
        // 创建ossClient
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 创建PutObjectRequest对象。
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, yourObjectName, inputStream);
        // 设置 ContentType类型,防止图片等资源无法使用url直接访问
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(getContentType(yourObjectName.substring(yourObjectName.lastIndexOf("."))));
        putObjectRequest.setMetadata(metadata);
        // 开始上传
        ossClient.putObject(putObjectRequest);
        // 上传成功关闭OSSClient
        //  ossClient.shutdown();
        log.info("上传-" + yourObjectName + " 成功");
        // 关闭OSSClient。
        ossClient.shutdown();
        return true;
    }


    /**
     * TODO 从OSS 下载文件
     * <p>
     * 下载文件。详细请参看“SDK手册 > Java-SDK > 下载文件”。
     * 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/download_object.html?spm=5176.docoss/sdk/java-sdk/manage_object
     * </P>
     *
     * @param yourObjectName 表示上文件在OSS时包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
     * @return
     */
    public InputStream download(String yourObjectName) {
        // 创建ossClient
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        OSSObject ossObject = null;
        try {
            ossObject = ossClient.getObject(bucketName, yourObjectName);
        } catch (OSSException e) {
            e.printStackTrace();
            throw new ErrorException(ResultEnum.ADMIN_OSS_NO_PATH);
        }
        // 关闭OSSClient。
        ossClient.shutdown();
        //返回InputStream
        return ossObject.getObjectContent();
    }


    /**
     * TODO 获取OSS 文件列表
     */
    public List<OSSObjectSummary> getObjectListing() {
        // 创建ossClient
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ObjectListing objectListing = ossClient.listObjects(bucketName);
        List<OSSObjectSummary> objectSummary = objectListing.getObjectSummaries();
//        System.out.println("您有以下Object：");
//        for (OSSObjectSummary object : objectSummary) {
//            System.out.println("\t" + object.getKey());
//        }
        // 关闭OSSClient。
        ossClient.shutdown();
        return objectSummary;
    }

    /**
     * TODO 删除, 删除文件夹 --> 如: file/
     */
    public boolean deleteObject(String firstKey) {
        // 创建ossClient
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.deleteObject(bucketName, firstKey);
        System.out.println("删除Object：" + firstKey + "成功。");
        // 关闭OSSClient。
        ossClient.shutdown();
        return true;
    }


    /**
     * TODO 判断获取文件保存内存
     *
     * @param FilenameExtension
     * @return
     */
    public static String getContentType(String FilenameExtension) {
        if (FilenameExtension.equalsIgnoreCase(".bmp")) {
            return "image/bmp";
        }
        if (FilenameExtension.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }
        if (FilenameExtension.equalsIgnoreCase(".jpeg") ||
                FilenameExtension.equalsIgnoreCase(".jpg") ||
                FilenameExtension.equalsIgnoreCase(".png")) {
            return "image/jpg";
        }
        if (FilenameExtension.equalsIgnoreCase(".html")) {
            return "text/html";
        }
        if (FilenameExtension.equalsIgnoreCase(".txt")) {
            return "text/plain";
        }
        if (FilenameExtension.equalsIgnoreCase(".vsd")) {
            return "application/vnd.visio";
        }
        if (FilenameExtension.equalsIgnoreCase(".pptx") ||
                FilenameExtension.equalsIgnoreCase(".ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (FilenameExtension.equalsIgnoreCase(".docx") ||
                FilenameExtension.equalsIgnoreCase(".doc")) {
            return "application/msword";
        }
        if (FilenameExtension.equalsIgnoreCase(".xml")) {
            return "text/xml";
        }
        if (FilenameExtension.equalsIgnoreCase(".mp4")) {
            return "video/mp4";
        }
        if (FilenameExtension.equalsIgnoreCase(".mp3")) {
            return "audio/mp3";
        }
        return "image/jpg";
    }
}
