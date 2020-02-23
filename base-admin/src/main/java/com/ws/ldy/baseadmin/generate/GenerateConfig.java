package com.ws.ldy.baseadmin.generate;

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
     * entryName:  模块/项目名称
     * packName:   包名/包路径（生成属性/接口名称,引入依赖使用）
     */
    public final static String author = "wangsong";
    public final static String wx_qq = "1720696548";
    public final static String entryName = "xi-jia-server";
    public final static String packName = "com.ws.ldy.xijiaserver";
    //代码生成路径 --> 父工程绝对路径
    public final static String fatherPath = "F:/workspace/code/git2020-1/spring-boot-plus2/";

    /**
     * =================================================================================
     * =================== 生成代码位置基本不用改变，除目录结构发生变化======================
     * =================================================================================
     */
    //java代码生成路径拼接 --> 父工程绝对路径 + 包路径 + 模块名
    public final static String java_path = fatherPath + entryName + "/src/main/java/" + packName.replace(".", "/")  + "/";
    public final static String path_entity = java_path + "entity/";
    public final static String path_controller = java_path + "controller/";
    public final static String path_service = java_path + "service/";
    public final static String path_service_impl = java_path + "service/impl/";
    public final static String path_dao = java_path + "dao/";
    //html 实际代码生成路径
    public final static String html_path = fatherPath + entryName + "/src/main/resources/templates/"+ FieldCG.entryNameSmall+"/";
    public final static String path_html_main = html_path;
    public final static String path_html_add = html_path;
    public final static String path_html_upd = html_path;



    /**
     * =================================================================================
     * =================== 此处内容一般都是不会修改的，特殊情况除外======================
     * =================================================================================
     *
     * PATH_TEMPLATE：       代码模版读取位置（目录 resources/static/下）,
     * PATH_PREVIEW：        预览代码生成路径(全生成到一个目录,最好不要动这个)
     * SUFFIX_JAVA_PREVIEW:  预览 java 后缀名（预览指定把该值赋值给 SUFFIX_JAVA）
     * SUFFIX_HTML_PREVIEW:  预览 html 后缀名（预览自动把该值赋值给 SUFFIX_HTML）
     * SUFFIX_JAVA:          实际 html 后缀名（最后读取）
     * SUFFIX_HTML:          实际 html 后缀名（最后读取）
     */
    public final static String PATH_TEMPLATE = "/template";
    public final static String PATH_PREVIEW = "File/src/txt/";
    public final static String SUFFIX_JAVA_PREVIEW = ".txt";
    public final static String SUFFIX_HTML_PREVIEW = ".txt";
    public final static String SUFFIX_JAVA_CODE = ".java";
    public final static String SUFFIX_HTML_CODE = ".html";
    public static String SUFFIX_JAVA = "";
    public static String SUFFIX_HTML = "";

    /**
     * mysql 关键字配置,如存在下方定义的关键字字段，实体类会进行自动处理
     */
    public static final String[] KEYWORD_ARRAY = {"time","desc", "name", "key", "value", "mysql", "info", "form", "sort", "icon", "unlock", "unLock"};

}
