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
        if (uploadManager == null) {
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

}


