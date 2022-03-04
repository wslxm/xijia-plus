package io.github.wslxm.springbootplus2.manage.gc.util;


import io.github.wslxm.springbootplus2.core.utils.LocalDateTimeUtil;
import io.github.wslxm.springbootplus2.core.utils.id.IdUtil;
import io.github.wslxm.springbootplus2.manage.gc.config.GenerateConfig;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 代码模板 参数替换类 (用于模板中获取参数)
 *
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2022/3/3 20:39
 */
@SuppressWarnings("all")
@Slf4j
public class TemplateParamsReplace {

	// 参数替换对象
	public static Map<String, String> PARAM_REPLAC = new ConcurrentHashMap<String, String>();



	/**
	 * ================================ 代码生成所有替换字段 ===========================================
	 * =================================注释信息
	 *  {author} ：      作者
	 *  {email} ：       邮箱 || 联系方式
	 *  {date} :         时间
	 *  =================================原始数据
	 *  {Demo} ：         类名/文件名大小（驼峰模式）
	 *  {demo} ：         类名/文件名小写开头（驼峰模式）
	 *  {htmlNameLower} ：html文件名称
	 *  {packName} ：     生成代码的包名/路径 （从java 目录开始，如当前: io.github.wslxm.baseadmin）
	 *  {tableName} ：    数据库表的实际名称
	 *  {entryName} ：    项目名实际名称
	 *  {entryNameUp} ：  项目名称-处理下划线(驼峰模式全大写开头)
	 *  {entryNameSmall}: 项目名称-处理下划线(全转小写)
	 *  {entryNameLast}:  项目名称 (下化线分隔，除第一个，拼接后面的全部单词,全小写,如只存在一个，则使用第一个)
	 *  =================================代码生成方法内获得的处理数据
	 *  {entitys} ：        entity 实体类所有字段数据
	 *  {primary-key-type} ： 主键id 数据类型
	 *  {layui-fields} ：   html主页layui数据表格所有字段数据
	 *  {add-htmls} ：      html添加页，表单所有添加字段数据
	 *  {upd-htmls} ：      html修改页，表单所有添加字段数据(无Id)
	 *  {upd-id} ：         html修改页，添加表单id赋值
	 *  {upd-backfill} ：   html修改页，打开提交回填数据赋值
	 */
	/**
	 * 代码生成所有文件数据替换工具类
	 *
	 * @param brBwPath
	 * @param DsField
	 * @return void
	 * @author ws
	 * @mail 1720696548@qq.com
	 * @date 2020/2/23 0023 8:12
	 */
	public static void replacBrBwWritee(Map<String, Object> brBwPath) {
		// 参数
		PARAM_REPLAC.put("{author}", GenerateConfig.AUTHOR);
		PARAM_REPLAC.put("{email}", GenerateConfig.EMAIL);
		PARAM_REPLAC.put("{describe}", GenerateConfig.DESCRIBE);
		PARAM_REPLAC.put("{date}", LocalDateTimeUtil.parse(LocalDateTimeUtil.now()));
		PARAM_REPLAC.put("{rootModule}", GenerateConfig.ROOT_MODULE); //模块根目录
		PARAM_REPLAC.put("{moduleName}", GenerateConfig.MODULE_NAME); //模块子目录
		// 原始数据
		PARAM_REPLAC.put("{tableName}", GenerateConfig.TABLE_NAME); // 表名
		PARAM_REPLAC.put("{tableNameUp}", GenerateConfig.TABLE_NAME_UP); //表名大写开头驼峰
		PARAM_REPLAC.put("{tableNameLower}", GenerateConfig.TABLE_NAME_LOWER); // 表名小写开头驼峰
		PARAM_REPLAC.put("{packPath}", GenerateConfig.PACK_PATH); // 包路径
		PARAM_REPLAC.put("{tableComment}", GenerateConfig.TABLE_COMMENT); // 数据表的注释
		// 后端代码
		PARAM_REPLAC.put("{entitys}", GenerateConfig.FIELD_ENTITYS);
		PARAM_REPLAC.put("{findPageMybatisPlus}", GenerateConfig.FIND_PAGE_MYBATIS_PLUS);
		PARAM_REPLAC.put("{resultMap}", GenerateConfig.RESULT_MAP);
		PARAM_REPLAC.put("{columnList}", GenerateConfig.COLUMN_LIST);
		PARAM_REPLAC.put("{xmlInsert}", GenerateConfig.XML_INSERT);
		PARAM_REPLAC.put("{xmlUpd}", GenerateConfig.XML_UPD);
		PARAM_REPLAC.put("{serialVersionUID}", IdUtil.snowflakeId()); //雪花id
		// 前端 layui
		PARAM_REPLAC.put("{layui-fields}", GenerateConfig.LAYUI_FIELDS);
		PARAM_REPLAC.put("{layui-search-pt-str}", GenerateConfig.LAYUI_SEARCH_PT_STR);
		PARAM_REPLAC.put("{layui-search-params-str}", GenerateConfig.LAYUI_SEARCH_PARAMS_STR);
		PARAM_REPLAC.put("{layui-search-js-str}", GenerateConfig.LAYUI_SEARCH_JS_STR);
		// html add/upd code
		PARAM_REPLAC.put("{add-upd-introduce}", GenerateConfig.ADD_UPD_INTRODUCE);
		PARAM_REPLAC.put("{add-upd-htmls}", GenerateConfig.ADD_UPD_HTMLS);
		PARAM_REPLAC.put("{add-upd-js}", GenerateConfig.ADD_UPD_JS);
		PARAM_REPLAC.put("{add-upd-submit-js}", GenerateConfig.ADD_UPD_SUBMIT_JS);
		// vue
		PARAM_REPLAC.put("{vue-info-columns}", GenerateConfig.VUE_INFO_COLUMNS);
		PARAM_REPLAC.put("{vue-add-columns}", GenerateConfig.VUE_ADD_COLUMNS);
		PARAM_REPLAC.put("{vue-add-columns-default}", GenerateConfig.VUE_ADD_COLUMNS_DEFAULT);
		PARAM_REPLAC.put("{vue-upd-columns}", GenerateConfig.VUE_UPD_COLUMNS);

		// 获取到getBrBwPath 方法拼装的数据
		BufferedReader br = (BufferedReader) brBwPath.get("br");
		BufferedWriter bw = (BufferedWriter) brBwPath.get("bw");
		try {
			String newLine = "";
			String line = null;
			while ((line = br.readLine()) != null) {
				// 内容替换
				newLine = line;
				for (String key : PARAM_REPLAC.keySet()) {
					newLine = newLine.replace(key, PARAM_REPLAC.get(key));
				}
				bw.write(newLine);
				bw.newLine();
				bw.flush();
			}
		} catch (IOException e) {
			log.debug(e.toString());
		}
		log.debug(brBwPath.get("name") + " --> " + brBwPath.get("path").toString());
	}
}
