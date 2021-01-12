package com.ws.ldy.modules.third.qiniu.util;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.ws.ldy.modules.third.qiniu.config.QiNiuOssProperties;
import com.ws.ldy.modules.third.qiniu.model.vo.TokenAndUrlVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * @author bo5.wang@56qq.com
 * @version 1.0
 * @desc
 * @date 2018/4/19
 */
@Slf4j
@Component
public class QiNiuUtils {

    @Autowired
    private QiNiuOssProperties qiNiuOssProperties;

    /**
     * 上传注册器
     */
    private static UploadManager uploadManager = null;


    /**
     * 初始化上传管理器
     */
    public void init() {
        //上传管理器
        if (uploadManager != null) {
            // 华东 z0 华北 z1 华南 z2 北美 na0 东南亚 as0   文档：https://developer.qiniu.com/kodo/1671/region-endpoint-fq
            Configuration cfg = null;
            String area = qiNiuOssProperties.getArea();
            if (area.equals("z0")) {
                cfg = new Configuration(Zone.zone0());
            } else if (area.equals("z1")) {
                cfg = new Configuration(Zone.zone1());
            } else if (area.equals("z2")) {
                cfg = new Configuration(Zone.zone2());
            } else if (area.equals("na0")) {
                cfg = new Configuration(Zone.zoneNa0());
            } else if (area.equals("as0")) {
                cfg = new Configuration(Zone.zoneAs0());
            }
            uploadManager = new UploadManager(cfg);
        }
    }


    /**
     * 获取七牛云上传 token，用于前端直传使用
     * @author wangsong
     * @date 2020/10/16 0016 14:07
     * @return java.lang.String
     * @version 1.0.0
     */
    public TokenAndUrlVO getUpToken() {
        Auth auth = Auth.create(qiNiuOssProperties.getAccessKey(), qiNiuOssProperties.getSecretKey());
        String upToken = auth.uploadToken(qiNiuOssProperties.getBucket());
        TokenAndUrlVO vo = new TokenAndUrlVO();
        vo.setToken(upToken);
        vo.setHosts(qiNiuOssProperties.getHosts());
        return vo;
    }


    /**
     * 七牛云流文件上传
     * @author wangsong
     * @param uploadStream
     * @param targetFileName
     * @date 2020/12/25 0025 9:54
     * @return java.lang.String
     * @version 1.0.0
     */
    public String uploadStream(InputStream uploadStream, String targetFileName) {
        this.init();
        if (targetFileName.startsWith("/")) {
            targetFileName = targetFileName.replaceFirst("/", "");
        }
        Auth auth = Auth.create(qiNiuOssProperties.getAccessKey(), qiNiuOssProperties.getSecretKey());
        try {
            String upToken = auth.uploadToken(qiNiuOssProperties.getBucket());
            Response response = uploadManager.put(uploadStream, targetFileName, upToken, null, null);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            log.info("七牛云oss文件上传成功 {}", JSONObject.toJSONString(putRet));
            return qiNiuOssProperties.getHosts() + targetFileName;
        } catch (Exception ex) {
            log.error("七牛云oss文件上传失败 {}", ex);
        }
        return null;
    }

//
//    /**
//     * 设置文件有效期
//     *
//     * @param publicUrl       上传时返回的链接地址
//     * @param expireInSeconds 该链接存活时间
//     * @return
//     */
//    public String privateFileUrl(String publicUrl, Integer expireInSeconds) {
//        try {
//            publicUrl = urlEncode(publicUrl);
//            return getAntiLeechAccessUrlBasedOnTimestamp(publicUrl, qiNiuOssProperties.getPriKey(), expireInSeconds);
//        } catch (Exception e) {
//            log.info("qiniu create internal-url error:", e);
//            throw new RuntimeException("七牛生成防盗链错误", e);
//        }
//    }

//    /**
//     * url编码
//     *
//     * @param urlStr 原始url
//     * @return
//     * @throws UnsupportedEncodingException
//     * @throws MalformedURLException
//     */
//    public static String urlEncode(String urlStr) throws MalformedURLException, URISyntaxException {
//        if (urlStr.contains("%")) {
//            return urlStr;
//        }
//        URL url = new URL(urlStr);
//        URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
//        return uri.toASCIIString();
//    }


//    private String getAntiLeechAccessUrlBasedOnTimestamp(String url, String encryptKey, int durationInSeconds)
//            throws MalformedURLException, UnsupportedEncodingException, NoSuchAlgorithmException {
//        URL urlObj = new URL(url);
//        String path = urlObj.getPath();
//        long timestampNow = System.currentTimeMillis() / 1000 + durationInSeconds;
//        String expireHex = Long.toHexString(timestampNow);
//        String toSignStr = String.format("%s%s%s", encryptKey, path, expireHex);
//        String signedStr = md5ToLower(toSignStr);
//        String signedUrl = null;
//        if (urlObj.getQuery() != null) {
//            signedUrl = String.format("%s&sign=%s&t=%s", url, signedStr, expireHex);
//        } else {
//            signedUrl = String.format("%s?sign=%s&t=%s", url, signedStr, expireHex);
//        }
//        return signedUrl;
//    }

//    private String md5ToLower(String src) throws UnsupportedEncodingException, NoSuchAlgorithmException {
//        MessageDigest digest = MessageDigest.getInstance("MD5");
//        digest.update(src.getBytes("utf-8"));
//        byte[] md5Bytes = digest.digest();
//        return Hex.encodeHexString(md5Bytes);
//    }

//    public String getPrivateFileUrl(String url, Integer expireInSeconds) {
//
//        Auth auth = Auth.create(qiNiuOssProperties.getAccessKey(), qiNiuOssProperties.getSecretKey());
//        String finalUrl = auth.privateDownloadUrl(url, expireInSeconds);
//
//        return finalUrl;
//    }


//    /**
//     * 下载图片，返回流( url下载)
//     *
//     * @param url
//     * @return
//     */
//    public InputStream download(String url) {
//        Auth auth = Auth.create(qiNiuOssProperties.getAccessKey(), qiNiuOssProperties.getSecretKey());
//        //获取下载地址
//        String downloadUrl = auth.privateDownloadUrl(url);
//        //图片下载
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder().url(downloadUrl).build();
//        okhttp3.Response response = null;
//        InputStream in = null;
//        try {
//            response = client.newCall(request).execute();
//            if (response.isSuccessful()) {
//                ResponseBody responseBody = response.body();
//                in = responseBody.byteStream();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return in;
//    }

//
//    /**
//     * 下载图片，返回图片字节( url下载)
//     *
//     * @param url
//     * @return
//     */
//    public byte[] downLoadBytes(String url) {
//
//        Auth auth = Auth.create(qiNiuOssProperties.getAccessKey(), qiNiuOssProperties.getSecretKey());
//        //获取下载地址
//        String downloadUrl = auth.privateDownloadUrl(url);
//        //图片下载
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder().url(downloadUrl).build();
//        okhttp3.Response response;
//        byte[] res = new byte[0];
//        try {
//            response = client.newCall(request).execute();
//            if (response.isSuccessful()) {
//                ResponseBody responseBody = response.body();
//                res = responseBody.bytes();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return res;
//    }
}


