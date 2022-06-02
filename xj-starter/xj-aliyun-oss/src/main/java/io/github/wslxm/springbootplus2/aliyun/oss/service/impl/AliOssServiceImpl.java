package io.github.wslxm.springbootplus2.aliyun.oss.service.impl;

import com.aliyun.oss.model.OSSObjectSummary;
import io.github.wslxm.springbootplus2.aliyun.oss.config.error.AliYunOssErrorException;
import io.github.wslxm.springbootplus2.aliyun.oss.service.AliOssService;
import io.github.wslxm.springbootplus2.aliyun.oss.util.FileDownloadUtil;
import io.github.wslxm.springbootplus2.aliyun.oss.util.FileUploadUtil;
import io.github.wslxm.springbootplus2.aliyun.oss.util.OSSUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 阿里云OSS 文件上传下载
 * <p>
 * consumes = "multipart/*", headers = "content-type=multipart/form-data"
 * </P>
 *
 * @author wangsong
 * @version 1.0.0
 * @email 1720696548@qq.com
 * @date 2018/10/20 21:32
 */
@Service
@Slf4j
public class AliOssServiceImpl implements AliOssService {


	@Autowired
	private HttpServletResponse response;

	@Autowired
	private OSSUtil ossUtil;


	@Override
	public Object upload(MultipartFile file,
	                     String filePath,
	                     Integer resType,
	                     Boolean isReduce) {

		// 1、验证文件格式
		// 2、验证保存路径
		// 3、获取处理后的文件名, file.getOriginalFilename()=原文件名
		String fileName = FileUploadUtil.getPath(filePath, file.getOriginalFilename());
		try {
			// 对上传的图片进行压缩
			InputStream inputStream = FileUploadUtil.imgReduce(filePath, isReduce, file.getInputStream());
			// 上传到OSS,返回访问地址
			if (resType == null || resType == 1) {
				return ossUtil.upload(filePath, fileName, inputStream);
			} else {
				String path = ossUtil.upload(filePath, fileName, inputStream);
				Map<String, String> res = new HashMap<>(2, 1);
				res.put("name", file.getOriginalFilename());
				res.put("url", path);
				return res;
			}
		} catch (Exception e) {
			throw new AliYunOssErrorException("上传过程中出现错误");
		}
	}


	/**
	 * oss-文件列表
	 */

	@Override
	public List<OSSObjectSummary> fileList() {
		return ossUtil.getObjectListing();
	}


	/**
	 * oss-文件删除
	 */
	@Override
	public Boolean del(@RequestParam String filePath) {
		// 去除域名 ,获得oss存储路径
		return ossUtil.deleteObject(filePath);
	}

	/**
	 * 网络文件下载
	 */
	@Override
	public void downloadNet(@RequestParam String filePath) {
		// 获取文件名称
		String fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
		// 文件下载
		FileDownloadUtil.download(filePath, fileName, response);
	}

	/**
	 * 网络文件打包下载
	 */
	@Override
	public void downloadNet(@RequestParam String filePaths, @RequestParam String zipName) {
		// 文件打包下载
		FileDownloadUtil.downloadZip(Arrays.asList(filePaths.split(",")), zipName, response);
	}
}

