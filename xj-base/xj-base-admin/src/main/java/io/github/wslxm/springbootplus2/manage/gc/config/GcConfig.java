package io.github.wslxm.springbootplus2.manage.gc.config;


import io.github.wslxm.springbootplus2.manage.gc.config.model.GcFilePath;
import io.github.wslxm.springbootplus2.manage.gc.model.po.DbFieldPO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 所有配置的集合
 *
 * @author wangsong
 * @date 2022/3/4 16:35
 * @return
 */
@Slf4j
@Data
public class GcConfig {

	/**
	 * 表字段的相关数据(前端传递)
	 */
	private List<DbFieldPO> dbFields;
	/**
	 * 默认处理的模板参数
	 */
	private Map<String, String> defaultTemplateParam = new ConcurrentHashMap<>();
	/**
	 * 动态模板参数（各个生成的实现对象动态加入的参数）
	 */
	private Map<String, String> templateParam = new ConcurrentHashMap<>();
	/**
	 * 模板生成位置
	 */
	private Map<String, GcFilePath> templatePathMap = new HashMap<>();
	/**
	 * 模板生成后的访问链接（代码生成完成后存在的数据）
	 */
	private Map<String, String> visitPathMap = new HashMap<>();


	/**
	 * 添加文件生成 模板配置
	 *
	 * @param name
	 * @param templatePath
	 * @param path
	 * @return
	 */
	public GcConfig addTemplate(String name, String templatePath, String path) {
		GcFilePath gcFilePath = new GcFilePath();
		gcFilePath.setName(name);
		gcFilePath.setTemplatePath(templatePath);
		gcFilePath.setPath(path);
		templatePathMap.put(name, gcFilePath);
		return this;
	}

	/**
	 * 模板生成后的 访问链接
	 *
	 * @param name key名
	 * @param path 访问地址
	 * @return
	 */
	public void addVisitPath(String name, String path) {
		visitPathMap.put(name, path);
	}


	/**
	 * 添加默认模板参数
	 *
	 * @param key key名
	 * @param key 访问地址
	 * @return
	 */
	public void setDefaultTemplateParam(String key, String value) {
		defaultTemplateParam.put("{" + key + "}", value);
	}

	/**
	 * 添加模板参数
	 *
	 * @param key
	 */
	public String getDefaultTemplateParam(String key) {
		return templateParam.get("{" + key + "}");
	}

	/**
	 * 添加模板参数
	 *
	 * @param key
	 * @param value
	 */
	public void setTemplateParam(String key, String value) {
		templateParam.put("{" + key + "}", value);
	}


}
