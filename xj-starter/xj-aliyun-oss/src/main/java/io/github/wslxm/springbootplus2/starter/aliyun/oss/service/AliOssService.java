package io.github.wslxm.springbootplus2.starter.aliyun.oss.service;

import com.aliyun.oss.model.OSSObjectSummary;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface AliOssService {


	/**
	 * 文件生成
	 *
	 * @param file
	 * @param filePath
	 * @param resType
	 * @param isReduce
	 * @return
	 */
	public Object upload(@RequestParam(required = true) MultipartFile file,
	                     @RequestParam(required = true) String filePath,
	                     @RequestParam(required = false) Integer resType,
	                     @RequestParam(required = false) Boolean isReduce);

	/**
	 * oss-文件列表
	 */
	public List<OSSObjectSummary> fileList();

	/**
	 * oss-文件删除
	 */
	public Boolean del(String filePath);

	/**
	 * 网络文件下载
	 */
	public void downloadNet(String filePath);

	/**
	 * 网络文件打包下载
	 */
	public void downloadNet(String filePaths, String zipName);
}

