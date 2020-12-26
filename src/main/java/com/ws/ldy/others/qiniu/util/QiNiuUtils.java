package com.ws.ldy.others.qiniu.util;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.qiniu.http.Response;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.ws.ldy.others.qiniu.config.QiNiuConfig;
import com.ws.ldy.others.qiniu.model.vo.TokenAndUrlVO;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import qiniu.happydns.util.Hex;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author bo5.wang@56qq.com
 * @version 1.0
 * @desc
 * @date 2018/4/19
 */
@Slf4j
public class QiNiuUtils {


    /**
     * 获取七牛云上传 token，用于前端直传使用
     * @author wangsong
     * @date 2020/10/16 0016 14:07
     * @return java.lang.String
     * @version 1.0.0
     */
    public static TokenAndUrlVO getUpToken() {
        Auth auth = Auth.create(QiNiuConfig.accessKey, QiNiuConfig.secretKey);
        String upToken = auth.uploadToken(QiNiuConfig.bucket);
        TokenAndUrlVO vo = new TokenAndUrlVO();
        vo.setToken(upToken);
        vo.setHosts(QiNiuConfig.hosts);
        vo.setPubHosts(QiNiuConfig.pubHosts);
        return vo;
    }


    /**
     * 七牛云文件上传
     * @author wangsong
     * @param filePath
     * @param targetFileName
     * @date 2020/12/25 0025 9:51
     * @return java.lang.String
     * @version 1.0.0
     */
    public static String upload(String filePath, String targetFileName) {
        if (targetFileName.startsWith("/")) {
            targetFileName = targetFileName.replaceFirst("/", "");
        }
        Auth auth = Auth.create(QiNiuConfig.accessKey, QiNiuConfig.secretKey);
        try {
            //七牛SDK上传时会进行url编码，所以这里为了防止targetFileName发生变化，先做一次url解码
            String uploadFileName = URLDecoder.decode(targetFileName, "UTF-8");
            String upToken = auth.uploadToken(QiNiuConfig.bucket, uploadFileName);
            Response response = QiNiuConfig.uploadManager.put(filePath, uploadFileName, upToken, null, null, true);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            log.info("七牛云oss文件上传成功 {}", JSONObject.toJSONString(putRet));
            return QiNiuConfig.hosts + targetFileName;
        } catch (Exception ex) {
            log.error("七牛云oss文件上传失败 {}", ex);
        }
        return null;
    }


    /**
     * 七牛云字节文件上传
     * @author wangsong
     * @param uploadBytes
     * @param targetFileName
     * @date 2020/12/25 0025 9:52
     * @return java.lang.String
     * @version 1.0.0
     */
    public static String uploadBytes(byte[] uploadBytes, String targetFileName) {
        if (targetFileName.startsWith("/")) {
            targetFileName = targetFileName.replaceFirst("/", "");
        }
        Auth auth = Auth.create(QiNiuConfig.accessKey, QiNiuConfig.secretKey);
        String upToken = auth.uploadToken(QiNiuConfig.bucket);
        try {
            Response response = QiNiuConfig.uploadManager.put(uploadBytes, targetFileName, upToken, null, null, true);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            log.info("七牛云oss文件上传成功 {}", JSONObject.toJSONString(putRet));
            return QiNiuConfig.hosts + targetFileName;
        } catch (Exception ex) {
            log.error("七牛云oss文件上传失败 {}", ex);
        }
        return null;
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
    public static String uploadStream(InputStream uploadStream, String targetFileName) {
        if (targetFileName.startsWith("/")) {
            targetFileName = targetFileName.replaceFirst("/", "");
        }
        Auth auth = Auth.create(QiNiuConfig.accessKey, QiNiuConfig.secretKey);
        try {
            String upToken = auth.uploadToken(QiNiuConfig.bucket);
            Response response = QiNiuConfig.uploadManager.put(uploadStream, targetFileName, upToken, null, null);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            log.info("七牛云oss文件上传成功 {}", JSONObject.toJSONString(putRet));
            return QiNiuConfig.hosts + targetFileName;
        } catch (Exception ex) {
            log.error("七牛云oss文件上传失败 {}", ex);
        }
        return null;
    }

    /**
     * 开放式文件上传
     *
     * @param inputStream
     * @param targetFile
     * @return
     */
    public static String pubUpload(InputStream inputStream, String targetFile) {
        if (targetFile.startsWith("/")) {
            targetFile = targetFile.replaceFirst("/", "");
        }
        Auth auth = Auth.create(QiNiuConfig.accessKey, QiNiuConfig.secretKey);
        String upToken = auth.uploadToken(QiNiuConfig.pubBucket);
        try {
            Response response = QiNiuConfig.uploadManager.put(inputStream, targetFile, upToken, null, null);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            log.info("七牛云oss文件上传成功 {}", JSONObject.toJSONString(putRet));
            return QiNiuConfig.hosts + targetFile;
        } catch (Exception ex) {
            log.error("七牛云oss文件上传失败 {}", ex);
        }
        return null;
    }


    /**
     * 有效时间的链接
     *
     * @param publicUrl       上传时返回的链接地址
     * @param expireInSeconds 该链接存活时间
     * @return
     */
    public static String privateFileUrl(String publicUrl, Integer expireInSeconds) {
        try {
            publicUrl = urlEncode(publicUrl);
            return getAntiLeechAccessUrlBasedOnTimestamp(publicUrl, QiNiuConfig.priKey, expireInSeconds);
        } catch (Exception e) {
            log.info("qiniu create internal-url error:", e);
            throw new RuntimeException("七牛生成防盗链错误", e);
        }
    }

    /**
     * url编码
     *
     * @param urlStr 原始url
     * @return
     * @throws UnsupportedEncodingException
     * @throws MalformedURLException
     */
    public static String urlEncode(String urlStr) throws MalformedURLException, URISyntaxException {
        if (urlStr.contains("%")) {
            return urlStr;
        }
        URL url = new URL(urlStr);
        URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
        return uri.toASCIIString();
    }


    private static String getAntiLeechAccessUrlBasedOnTimestamp(String url, String encryptKey, int durationInSeconds)
            throws MalformedURLException, UnsupportedEncodingException, NoSuchAlgorithmException {
        URL urlObj = new URL(url);
        String path = urlObj.getPath();
        long timestampNow = System.currentTimeMillis() / 1000 + durationInSeconds;
        String expireHex = Long.toHexString(timestampNow);
        String toSignStr = String.format("%s%s%s", encryptKey, path, expireHex);
        String signedStr = md5ToLower(toSignStr);
        String signedUrl = null;
        if (urlObj.getQuery() != null) {
            signedUrl = String.format("%s&sign=%s&t=%s", url, signedStr, expireHex);
        } else {
            signedUrl = String.format("%s?sign=%s&t=%s", url, signedStr, expireHex);
        }
        return signedUrl;
    }

    private static String md5ToLower(String src) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        digest.update(src.getBytes("utf-8"));
        byte[] md5Bytes = digest.digest();
        return Hex.encodeHexString(md5Bytes);
    }

    public static String getPrivateFileUrl(String url, Integer expireInSeconds) {

        Auth auth = Auth.create(QiNiuConfig.accessKey, QiNiuConfig.secretKey);
        String finalUrl = auth.privateDownloadUrl(url, expireInSeconds);

        return finalUrl;
    }


    /**
     * 下载图片，返回流( url下载)
     *
     * @param url
     * @return
     */
    public static InputStream download(String url) {
        Auth auth = Auth.create(QiNiuConfig.accessKey, QiNiuConfig.secretKey);
        //获取下载地址
        String downloadUrl = auth.privateDownloadUrl(url);
        //图片下载
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(downloadUrl).build();
        okhttp3.Response response = null;
        InputStream in = null;
        try {
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                ResponseBody responseBody = response.body();
                in = responseBody.byteStream();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return in;
    }


    /**
     * 下载图片，返回图片字节( url下载)
     *
     * @param url
     * @return
     */
    public static byte[] downLoadBytes(String url) {

        Auth auth = Auth.create(QiNiuConfig.accessKey, QiNiuConfig.secretKey);
        //获取下载地址
        String downloadUrl = auth.privateDownloadUrl(url);
        //图片下载
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(downloadUrl).build();
        okhttp3.Response response;
        byte[] res = new byte[0];
        try {
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                ResponseBody responseBody = response.body();
                res = responseBody.bytes();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}


