package com.ws.ldy.common.generate;

/**
 * TODO  代码生成常量类
 *
 * @author ws
 * @mail 1720696548@qq.com
 * @date 2020/2/11 0011 0:08
 * @return
 */
@SuppressWarnings("ALL")
public class GenerateConfig {

    /**
     * author：    代码生成的注释信息：作者
     * wx_qq：     代码生成的qq,微信联系方式
     * moduleName: 模块名称
     * packName:   包名/包路径（生成属性/接口名称,引入依赖使用）
     */
    public final static String AUTHOR = "wangsong";
    public final static String EMAIL = "1720696548@qq.com";
    public final static String DESCRIBE = " ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 ";
    public final static String PACK_PATH = "com.ws.ldy";
    public final static String MODULE_NAME = "xijia";
    // 代码生成路径 --> 父工程绝对路径
    //public final static String FATHER_PATH = "F:/workspace/code/git2020-1/spring-boot-plus2/";
    public final static String FATHER_PATH = ""; //不填为当前自动为当前项目目录下


    /**
     * =================================================================================
     * =================== 预览代码生成路径=====================
     * =================================================================================
     */
   // html代码生成路径
    public final static String BASE_PATH_HTML_YL = "File/code/src/main/resources/templates/" + MODULE_NAME + "/";
    public final static String BASE_PATH_HTML_TXT_YL = "File/code/src/main/resources/templates/" + MODULE_NAME + "/txt/";
    // java代码生成路径拼接 --> 父工程绝对路径 + 包路径 + 模块名
    public final static String BASE_PATH_JAVA_YL = "File/code/src/main/java/" + PACK_PATH.replace(".", "/") + "/";

    /**
     * =================================================================================
     * =================== 生成代码位置基本不用改变，除目录结构发生变化======================
     * =================================================================================
     */
    public final static String BASE_PATH_HTML = "src/main/resources/templates/" + MODULE_NAME + "/";
    public final static String BASE_PATH_HTML_TXT = "src/main/resources/templates/" + MODULE_NAME + "/txt/";
    // java代码生成路径拼接 --> 父工程绝对路径 + 包路径 + 模块名
    public final static String BASE_PATH_JAVA = "src/main/java/" + PACK_PATH.replace(".", "/") + "/" + MODULE_NAME + "/";

    // 各代码具体路径
    public final static String PATH_ENTITY = "model/entity/";
    public final static String PATH_VO = "model/vo/";
    public final static String PATH_DTO = "model/dto/";
    public final static String PATH_CONTROLLER = "controller/";
    public final static String PATH_SERVICE = "service/";
    public final static String PATH_SERVICE_IMPL = "service/impl/";
    public final static String PATH_DAO = "mapper/";

    /**
     * 此处内容一般都是不会修改的，特殊情况除外
     */
    public final static String PATH_TEMPLATE = "/template";   // 代码模版读取位置（目录 resources/static/下）
    public static String SUFFIX_JAVA = ".java";               // 实际 java 后缀名
    public static String SUFFIX_HTML = ".html";               // 实际 html 后缀名
    public static String SUFFIX_TXT = ".txt";                 // 预览 html 后缀名(html页面展示为渲染后的页面)
    public static String SUFFIX = ".html";                    // 预览 html 后缀名(html页面展示为渲染后的页面)

    /**
     * mysql 关键字配置,如存在下方定义的关键字字段，实体类会进行自动处理
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
            "unLock"
    };
}
