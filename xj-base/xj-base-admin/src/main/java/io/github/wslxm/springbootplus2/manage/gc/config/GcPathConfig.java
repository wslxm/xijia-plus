package io.github.wslxm.springbootplus2.manage.gc.config;


import lombok.extern.slf4j.Slf4j;


/**
 * 配置 文件 和 目录 的模板和生成位置
 *
 * @author wangsong
 * @date 2022/3/4 16:35
 * @return
 */
@SuppressWarnings({"all"})
@Slf4j
public class GcPathConfig {

	/**
	 * 代码模板 路径
	 */
	// java代码
	public static String T_BASE_TEMPLATE = "/template";
	public static String T_ENTITY = T_BASE_TEMPLATE + "/model/Demo.tp";
	public static String T_VO = T_BASE_TEMPLATE + "/model/DemoVO.tp";
	public static String T_DTO = T_BASE_TEMPLATE + "/model/DemoDTO.tp";
	public static String T_QUERY = T_BASE_TEMPLATE + "/model/DemoQuery.tp";
	public static String T_CONTROLLER = T_BASE_TEMPLATE + "/DemoController.tp";
	public static String T_SERVICE = T_BASE_TEMPLATE + "/DemoService.tp";
	public static String T_SERVICEIMPL = T_BASE_TEMPLATE + "/DemoServiceImpl.tp";
	public static String T_MAPPER = T_BASE_TEMPLATE + "/DemoMapper.tp";
	// xml 及 vue代码
	public static String T_MAPPER_XML = T_BASE_TEMPLATE + "/xml/DemoMapper.tp";
	public static String T_VUE = T_BASE_TEMPLATE + "/vue/DemoVue.tp";
	public static String T_VUE_ADD = T_BASE_TEMPLATE + "/vue/DemoVueAdd.tp";
	public static String T_VUE_UPD = T_BASE_TEMPLATE + "/vue/DemoVueUpd.tp";

	/**
	 * 代码生成 路径
	 * <P>
	 *  {projectName}
	 *  {packPath}
	 *  {rootModule}
	 *  {moduleName}
	 *  {tableNameUp}
	 * </P>
	 */
	// java 文件默认生成到 指定包录 + 模块路径 + 下方指定的路径下
	private static String BASE_PATH_JAVA = "{projectName}/src/main/java/{packFilePath}/{rootModule}/{moduleName}";
	private static String BASE_PATH_XML = "{projectName}/src/main/resources";
	private static String BASE_PATH_VUE = "{projectName}/src/main/resources";
	//
	public static String P_ENTITY = BASE_PATH_JAVA + "/model/entity/{tableNameUp}.java";
	public static String P_VO = BASE_PATH_JAVA + "/model/vo/{tableNameUp}VO.java";
	public static String P_DTO = BASE_PATH_JAVA + "/model/dto/{tableNameUp}DTO.java";
	public static String P_QUERY = BASE_PATH_JAVA + "/model/query/{tableNameUp}Query.java";
	public static String P_CONTROLLER = BASE_PATH_JAVA + "/controller/{tableNameUp}Controller.java";
	public static String P_SERVICE = BASE_PATH_JAVA + "/service/{tableNameUp}Service.java";
	public static String P_SERVICE_IMPL = BASE_PATH_JAVA + "/service/impl/{tableNameUp}serviceImpl.java";
	public static String P_MAPPER = BASE_PATH_JAVA + "/mapper/{tableNameUp}mapper.java";
	// 以下默认生成到 resources 目录
	public static String P_MAPPER_XML = BASE_PATH_XML + "/mapper/{moduleName}/{tableNameUp}.xml";
	// vue
	public static String P_VUE = BASE_PATH_VUE + "/templates/vue/{rootModule}/{moduleName}/{tableNameUp}.vue";
	public static String P_VUE_ADD = BASE_PATH_VUE + "/templates/vue/{rootModule}/{moduleName}/{tableNameUp}Add.vue";
	public static String P_VUE_UPD = BASE_PATH_VUE + "/templates/vue/{rootModule}/{moduleName}/{tableNameUp}Upd.vue";


	/**
	 *  预览文件配置
	 *   - 预览文件生成路径
	 *   - 预览文件生成后缀格式
	 */
	public static String PREVIEW_FILE_PATH = "File/";
	public static String PREVIEW_SUFFIX = ".txt";
}
