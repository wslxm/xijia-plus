package com.ws.ldy.adminconsole.controller.vo;

import lombok.Data;

/**
 * TODO  代码生成需要的字段
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/22 11:14
 */
@SuppressWarnings("DuplicatedCode")
@Data
public class FieldCG {

    /**
     * 项目实际名称
     */
    private String entryName;
    /**
     * 数据库表的实际名称
     */
    private String tableName;
    /**
     * 生成 html 代码的路径(从项目名开始)
     */
    private String pathHtml;
    /**
     * 生成 java 代码的路径(从项目名开始)
     */
    private String pathJava;
    /**
     * 项目 Factory代码的路径(从项目名开始)，daoFactory，serviceFactory
     */
    private String pathFactoryTp;
    /**
     * 代码模板路径(从项目名开始)
     */
    private String pathTp;
    /**
     * 父项目的硬盘路径(如当前到： spring-boot-plus2)
     */
    private String pathFather;
    /**
     * 启动后的服务器跟路径
     */
    private String pathDeploy;
    /**
     * 启动后的服务器id+端口
     */
    private String basePath;
    /**
     * 生成代码的包名 （从java 目录开始，如当前: com.ws.ldy.adminconsole）
     */
    private String packName;


    /**
     * 数据处理
     */
    public void dataProcessing() {
        putClassNameLower();
        putClassNameUp();
        putEntryNameLast();
        putEntryNameSmall();
        putEntryNameUp();
        putHtmlNameLower();
    }


    /**
     * 项目名称 处理下划线(全转小写)
     */
    private String entryNameSmall;
    /**
     * 项目名称 处理下划线(驼峰模式全大写开头)
     */
    private String entryNameUp;
    /**
     * 项目名称 (下化线分隔，除第一个，拼接后面的全部单词,全小写,如只存在一个，则使用第一个)
     */
    private String entryNameLast;
    /**
     * java类名称/文件名大写开头
     */
    private String classNameUp;
    /**
     * java类名称/文件名小写开头
     */
    private String classNameLower;
    /**
     * html文件名小写开头
     */
    private String htmlNameLower;

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
