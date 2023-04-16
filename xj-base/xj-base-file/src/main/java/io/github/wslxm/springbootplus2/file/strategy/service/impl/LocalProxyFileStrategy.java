package io.github.wslxm.springbootplus2.file.strategy.service.impl;

import cn.hutool.core.io.resource.InputStreamResource;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import io.github.wslxm.springbootplus2.core.config.error.ErrorException;
import io.github.wslxm.springbootplus2.core.result.Result;
import io.github.wslxm.springbootplus2.core.result.ResultType;
import io.github.wslxm.springbootplus2.core.utils.validated.ValidUtil;
import io.github.wslxm.springbootplus2.file.properties.FileProperties;
import io.github.wslxm.springbootplus2.file.strategy.service.FileStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 代理 到 当前服务单独部署的统一文件服务中
 *
 * @author wangsong
 * @version 1.0.0
 * @mail 1720696548@qq.com
 * @date 2022/10/15 0015 18:19
 */
@Service
@Slf4j
public class LocalProxyFileStrategy implements FileStrategy {

    /**
     * 上传接口地址
     */
    private final static String UPLOAD_URL = "/api/open/file/upload";
    /**
     * 删除文件 接口地址
     */
    private final static String DEL_URL = "/api/open/file/del";
    /**
     * 删除目录 接口地址
     */
    private final static String DEL_FOLDER = "/api/open/file/delFolder";

    @Autowired
    private FileProperties fileProperties;

    @Override
    public String upload(InputStream inputStream, String filePath, String fileName) {
        InputStreamResource inputStreamResource = new InputStreamResource(inputStream, fileName);
        Map<String, Object> params = new HashMap<>();
        params.put("filePath", filePath);
        params.put("resType", 1);
        params.put("file", inputStreamResource);
        // 链式构建请求
        String proxyUrl = this.getProxyUrl();
        Result result = null;
        try {
            String resultStr = HttpRequest.post(proxyUrl + UPLOAD_URL)
                    .header("Content-type", "multipart/form-data")
                    .form(params).timeout(59000).execute().body();
            result = JSON.parseObject(resultStr, Result.class);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ErrorException("上传错误");
        }
        if (!result.getCode().equals(Result.success().getCode())) {
            throw new ErrorException(result.getCode(), result.getMsg());
        }
        return result.getData() + "";
    }

    @Override
    public boolean del(String filePath) {
        Map<String, Object> params = new HashMap<>();
        params.put("filePath", filePath);
        // 链式构建请求
        String resultStr = HttpRequest.delete(this.getProxyUrl() + DEL_URL).form(params)
                .execute().body();
        Result result = null;
        try {
            result = JSON.parseObject(resultStr, Result.class);
        } catch (Exception e) {
            throw new ErrorException(ResultType.SYS_ERROR_CODE_500.getValue(), "删除文件错误");
        }
        if (!result.getCode().equals(Result.success().getCode())) {
            throw new ErrorException(result.getCode(), result.getMsg());
        }
        return Boolean.parseBoolean(result.getData() + "");
    }

    @Override
    public boolean delFolder(String filePath) {
        Map<String, Object> params = new HashMap<>();
        params.put("filePath", filePath);
        String resultStr = HttpRequest.delete(this.getProxyUrl() + DEL_FOLDER).form(params)
                .execute().body();
        Result result = null;
        try {
            JSON.parseObject(resultStr, Result.class);
        } catch (Exception e) {
            throw new ErrorException(ResultType.SYS_ERROR_CODE_500.getValue(), "删除目录错误");
        }
        if (!result.getCode().equals(Result.success().getCode())) {
            throw new ErrorException(result.getCode(), result.getMsg());
        }
        return Boolean.parseBoolean(result.getData() + "");
    }


    /**
     * 获取文件代理路径
     */
    private String getProxyUrl() {
        String proxyUrl = fileProperties.getLocalProxy().getUrl();
        ValidUtil.isTrue(StringUtils.isEmpty(proxyUrl), "没有配置代理路径");
        return proxyUrl;
    }
}
