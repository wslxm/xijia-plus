package com.ws.ldy.baseadmin.generate;

import lombok.Data;

/**
 * TODO  代码生成需要的字段 (公共字段，公共字段处理类，通过赋值和计算获得)
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/22 11:14
 */
@SuppressWarnings("all")
@Data
public class FieldCG {

    //传入获得
    public static String entryName;         // 项目实际名称
    public static String tableName;         // 数据库表的实际名称
    public static String pathTp;            // 代码模板路径(从项目名开始)
    public static String packName;          // 生成代码的包名/路径 （从java 目录开始，如当前: com.ws.ldy.baseadmin）
    //初始化数据处理获取
    public static String entryNameSmall;    //  项目名称-处理下划线(全转小写)
    public static String entryNameUp;       //  项目名称-处理下划线(驼峰模式全大写开头)
    public static String entryNameLast;     //  项目名称 (下化线分隔，除第一个，拼接后面的全部单词,全小写,如只存在一个，则使用第一个)
    public static String classNameUp;       //  java 类名称/文件名大写开头
    public static String classNameLower;    //  java 类名称/文件名小写开头
    public static String htmlNameLower;     //  html 文件名小写开头

    //每一个代码生成方法获得的数据
    public static String fieldEntitys = "";    // entity 实体类所有字段数据
    public static String primaryKeyType = "";  // id主键数据类型
    public static String layuiFields = "";     // 生成html主页，layui数据表格所有字段数据
    public static String addHtmls = "";        // 生成html 添加页，表单所有添加字段数据
    public static String updhtmls = "";        // 生成html 修改加页，表单所有添加字段数据
    public static String updId = "";           // 生成html 修改加页，修改赋值id字段（根据Id修改）
    public static String updBackfill = "";     // 生成html 修改加页，打开提交也回填数据赋值


    /**
     * TODO   数据处理,传入相关参数后主动调用（项目名称+生成的文件名，小写，驼峰模式全大写开头，下化线分隔，除第一个，小写开头，大写开头等）
     *
     * @return void
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/2/10 0010 23:58
     */
    public void dataProcessing() {
        putEntryNameSmall();  //  当前项目-模块名称 处理下划线(全转小写)
        putEntryNameUp();     //  项目名称-处理下划线(驼峰模式全大写开头)
        putEntryNameLast();   //  项目名称 (下化线分隔，除第一个，拼接后面的全部单词,全小写,如只存在一个，则使用第一个)
        putClassNameUp();     //  java 类名称/文件名小写开头
        putClassNameLower();  //  java 类名称/文件名大写开头
        putHtmlNameLower();   //  html 文件名小写开头
    }


    private void putEntryNameSmall() {
        this.entryNameSmall = this.entryName.replace("-", "").toLowerCase();
    }


    private void putEntryNameUp() {
        String[] entryNames = entryName.split("-");
        String fieldUp = "";
        for (int i = 0; i < entryNames.length; i++) {
            fieldUp += entryNames[i].substring(0, 1).toUpperCase() + entryNames[i].substring(1);
        }
        this.entryNameUp = fieldUp;
    }

    private void putEntryNameLast() {
        String[] entryNames = this.entryName.split("-");
        if (entryNames.length > 0) {
            String value = "";
            for (int i = 0; i < entryNames.length; i++) {
                if (i > 0) {
                    value += entryNames[i].toLowerCase();
                }
            }
            this.entryNameLast = value;
        } else {
            this.entryNameLast = entryNames[0].toLowerCase();
        }
    }


    private void putClassNameUp() {
        String[] tables = this.tableName.split("_");
        String tableNameVal = "";
        // t_admin_role_menu 划分过多处理
        for (int i = 2; i < tables.length; i++) {
            if (i == 2) {
                tableNameVal += tables[i].substring(0, 1).toUpperCase() + tables[i].substring(1);
            } else {
                tableNameVal += tables[i].substring(0, 1).toUpperCase() + tables[i].substring(1);
            }
        }
        tableNameVal += tables[1].substring(0, 1).toUpperCase() + tables[1].substring(1);
        this.classNameUp = tableNameVal;
    }


    private void putClassNameLower() {
        String[] tables = this.tableName.split("_");
        String tableNameVal = "";
        // t_admin_role_menu 划分过多处理
        for (int i = 2; i < tables.length; i++) {
            if (i == 2) {
                tableNameVal += tables[i].substring(0, 1).toLowerCase() + tables[i].substring(1);
            } else {
                tableNameVal += tables[i].substring(0, 1).toUpperCase() + tables[i].substring(1);
            }
        }
        tableNameVal += tables[1].substring(0, 1).toUpperCase() + tables[1].substring(1);
        this.classNameLower = tableNameVal;
    }


    private void putHtmlNameLower() {
        String[] tables = this.tableName.split("_");
        String value = "";
        if (tables.length > 2) {
            for (int i = 3; i < tables.length; i++) {
                value += tables[i].substring(0, 1).toUpperCase() + tables[i].substring(1);
            }
            value = tables[2].substring(0, 1).toLowerCase() + tables[2].substring(1) + value;
        } else {
            value = tables[1].substring(0, 1).toLowerCase() + tables[1].substring(1);
        }
        this.htmlNameLower = value;
    }
}
