package io.github.wslxm.springbootplus2.manage.gc.config.model;

import lombok.Data;

@Data
public class GcFilePath {
	/**
	 * 模板名称
	 */
	private String name;
	/**
	 * 模板位置
	 */
	private String templatePath;
	/**
	 * 生成文件位置
	 */
	private String path;
}