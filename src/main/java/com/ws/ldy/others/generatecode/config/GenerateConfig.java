package com.ws.ldy.others.generatecode.config;


import java.util.Arrays;
import java.util.List;

/**
 *  代码生成常量类
 *
 * @author ws
 * @mail 1720696548@qq.com
 * @date 2020/2/11 0011 0:08
 * @return
 */
@SuppressWarnings({"ALL", "AlibabaAvoidCommentBehindStatement"})
public class GenerateConfig {

    /**
     * author：    代码生成的注释信息：作者
     * email：     代码生成的邮箱联系方式
     * moduleName: 模块名称
     * packName:   包名/包路径（生成属性/接口名称,引入依赖使用）
     */
    public final static String AUTHOR = "wangsong";
    public final static String EMAIL = "1720696548@qq.com";
    public final static String DESCRIBE = " ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 ";
    public final static String PACK_PATH = "com.ws.ldy";    // 包根路径（所有代码,启动类的路径）
    public final static String PACK_PATH_ZP = "modules";    // 包根的下一级路径（所有模块的包路径）
    public final static String MODULE_NAME = "admin";       // 模块名/路径（当前模块的包路径）

    /**
     * 生成路径
     * 相对路径： value=""  当前项目目录下(建议-默认为相对路径)
     * 绝对路径:  value="F:/workspace/code/git2020-1/spring-boot-plus2/"
     */
    public final static String FATHER_PATH = "";

    /**
     * 前缀配置（非默认数据源允许被篡改）
     * 表前缀：TABLE_PREFIX  （绝对生成的文件名称是否去除前缀信息）
     * 字段前缀：FIELD_PREFIX
     * _DEFAULT = 默认配置
     */
    public static String TABLE_PREFIX = GenerateConfig.TABLE_PREFIX_DEFAULT;
    public static String FIELD_PREFIX = GenerateConfig.FIELD_PREFIX_DEFAULT;
    public static String TABLE_PREFIX_DEFAULT = "t_";
    public static String FIELD_PREFIX_DEFAULT = "";

    /**
     * entity / vo / dto 是否使用swagger 注释
     * entitySwagger : 实体类是否使用swagger注释, true=是  false=否
     */
    public static boolean entitySwagger = false;

    /**
     * 生成代码位置基本不用改变，除目录结构发生变化
     */
    // 预览的 html + java代码生成路径拼接 --> 父工程绝对路径 + 包路径 + 包的下一级路径+ 模块名
    public final static String BASE_PATH_HTML_TXT_YL = "File/code/src/main/resources/templates/" + PACK_PATH_ZP + "/" + MODULE_NAME + "/txt/";
    public final static String BASE_PATH_JAVA_YL = "File/code/src/main/java/" + PACK_PATH.replace(".", "/") + "/" + PACK_PATH_ZP + "/" + MODULE_NAME + "/";
    public final static String BASE_PATH_XML_YL = "File/code/src/main/resources/mapper/" + PACK_PATH_ZP + "/" + MODULE_NAME + "/";

    // 生成的 html + java代码生成路径拼接 --> 父工程绝对路径 + 包路径 + 包的下一级路径+ 模块名
    public final static String BASE_PATH_HTML = "src/main/resources/templates/" + PACK_PATH_ZP + "/" + MODULE_NAME + "/";
    public final static String BASE_PATH_JAVA = "src/main/java/" + PACK_PATH.replace(".", "/") + "/" + PACK_PATH_ZP + "/" + MODULE_NAME.replace(".", "/") + "/";
    public final static String BASE_PATH_XML = "src/main/resources/mapper/" + PACK_PATH_ZP + "/" + MODULE_NAME + "/";

    /**
     * 生成的各代码具体的生成路径
     */
    public final static String PATH_ENTITY = "model/entity/";
    public final static String PATH_VO = "model/vo/";
    public final static String PATH_DTO = "model/dto/";
    public final static String PATH_CONTROLLER = "controller/";
    public final static String PATH_SERVICE = "service/";
    public final static String PATH_SERVICE_IMPL = "service/impl/";
    public final static String PATH_MAPPER = "mapper/";
    public final static String PATH_MAPPER_XML = "mapperXml/";

    /**
     * 此处内容一般都是不会修改的，特殊情况除外
     */
    public final static String PATH_TEMPLATE = "/template";   // 代码模版读取位置（目录 resources/static/下）
    public static String SUFFIX_JAVA = ".java";             // 实际 java 后缀名
    public static String SUFFIX_XML = ".xml";               // 实际 xml 后缀名
    public static String SUFFIX_HTML = ".html";             // 实际 html 后缀名
    //
    public static String SUFFIX_TXT = ".txt";               // 预览 html 后缀名
    //
    public static String SUFFIX_JAVA_PT = ".txt";           // 最后生成html 后缀名
    public static String SUFFIX_HTML_PT = ".txt";           // 最后生成的html 后缀名
    public static String SUFFIX_XML_PT = ".txt";            // 最后生成的html 后缀名


    /**
     * mysql 数据表通用字段
     */
    public static final List<String> BASE_FIELDS = Arrays.asList(
            ("id" +
                    ",create_user" +
                    ",update_user" +
                    ",create_time" +
                    ",update_time" +
                    ",deleted" +
                    ",version")
                    .split(","));

    /**
     * mysql 关键字配置,如存在下方定义的关键字字段,实体类会进行自动处理
     */
    public static final String[] KEYWORD_ARRAY = {
            "time",
            "desc",
            "name",
            "key",
            "value",
            "mysql",
            "info",
            "form",
            "sort",
            "icon",
            "unlock",
            "unLock",
            "comment",
            "disable",
            "force",
    };
}
