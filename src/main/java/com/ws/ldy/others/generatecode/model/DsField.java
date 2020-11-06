package com.ws.ldy.others.generatecode.model;

import com.google.common.base.CaseFormat;
import com.ws.ldy.others.generatecode.config.GenerateConfig;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 *  代码生成需要的字段 (公共字段，公共字段处理类，通过赋值和计算获得)
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/22 11:14
 */
@SuppressWarnings("all")
@Data
public class DsField {

    /**
     * 代码模板路径(从项目名开始,除该字段，其余所有字段为模块页面替换值)
     */
    public static String PATH_TP;
    /**
     * FieldCG 构造函数传入获得( new FieldCG(a,b,b) 时)
     */
    public static String TABLE_NAME;         // 数据库表的实际名称
    public static String TABLE_COMMENT;      // 数据表的注释
    public static String PACK_PATH;          // 生成代码的包名/路径 （从java 目录开始，如当前: com.ws.ldy.baseadmin）
    /**
     * FieldCG 构造函数初始化类时计算获得( new FieldCG(a,b,b) 时)
     */
    public static String TABLE_NAME_UP;       //  表名驼峰大写开头 --> java 文件名+类名
    public static String TABLE_NAME_LOWER;    //  表名驼峰小写开头 --> (  html 文件名 ||  java 对象属性名-User user = new User() 的user)

    /**
     * 每一个代码生成方法获得的数据 (在每一个生成代码方法中获得，初始化该类时为空)
     */
    public static String FIELD_ENTITYS = "";           // entity 实体类所有字段数据
    public static String FIND_PAGE_PARAM = "";         // controller， findPage方法参数列表
    public static String FIND_PAGE_MYBATIS_PLUS = "";  // controller， findPage方法查询参数,拼接到mybatisPlus方法中参数列表
    public static String SWAGGER_REMARK = "";          // controller， findPage方法查询参数，的swagger注释

    public static String RESULT_MAP = "";   // mapper xml 字段映射
    public static String COLUMN_LIST = "";  // mapper xml 通用字段返回

    //
//        {resultMap}
//        {columnList}

    // public static String PRIMARY_KEY_TYPE = "";      // id主键数据类型
    //html主页参数
    public static String LAYUI_FIELDS = "";             // 生成html主页，layui数据表格所有字段数据
    public static String LAYUI_SEARCH_PT_STR = "";      // 生成html主页，搜索条件拼接
    public static String LAYUI_SEARCH_PARAMS_STR = "";   // 生成html主页，搜索条件url参数
    //
    public static String ADD_HTMLS = "";         // 生成html 添加页，表单所有添加字段数据
    public static String UPD_HTMLS = "";         // 生成html 修改加页，表单所有添加字段数据
    public static String UPD_ID = "";            // 生成html 修改加页，修改赋值id字段（根据Id修改）
    public static String UPD_BACKFILL = "";      // 生成html 修改加页，打开提交也回填数据赋值


    /**
     * 1、tableName;         // 数据库表的实际名称
     * 2、tableComment;      // 数据库表的注释
     * 3、packName;          // 生成代码的包名/路径 （从java 目录开始，如当前: com.ws.ldy.baseadmin）
     * 4、pathTp;            // 代码模板路径
     */
    public DsField(String tableName, String tableComment, String packPath, String pathTp) {
        PATH_TP = pathTp;
        TABLE_NAME = tableName;
        TABLE_COMMENT = tableComment;
        PACK_PATH = packPath;
        // 如果需要，去除表前缀
        if(StringUtils.isNotBlank(GenerateConfig.TABLE_PREFIX)){
            // 获取前缀
            String prefix = tableName.substring(0, GenerateConfig.TABLE_PREFIX.length() );
            String newTableName = tableName;
            // 去除表前缀(如果为?_  去除 ?_)
            if (GenerateConfig.TABLE_PREFIX.equals(prefix)) {
                //去掉数据库表的前缀，如 t_
                newTableName = tableName.substring(GenerateConfig.TABLE_PREFIX.length());
            }
            TABLE_NAME_UP = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, newTableName);    // test_data --> TestData
            TABLE_NAME_LOWER = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, newTableName); // test_data --> testData
        }else{
            TABLE_NAME_UP = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName);    // test_data --> TestData
            TABLE_NAME_LOWER = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableName); // test_data --> testData
        }

    }


    // TEST
    public static void main(String[] args) {
        System.out.println(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, "t_test-data"));            // testData
        System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "t_test_data"));        // testData
        System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "t_test_data_test"));   // TestData
        System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "t_test_data"));        // TestData
        System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "testdata"));           // testdata
        System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "TestData"));           // test_data
        System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, "testData"));               // test-data
    }
}
