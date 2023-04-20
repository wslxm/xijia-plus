package io.github.wslxm.springbootplus2.file.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 文件名规则常量
 *
 * @author wangsong
 * @date 2023/04/20
 */
@Getter
@AllArgsConstructor
public enum FilenameRuleEnum {
    //
    NO("no", "无规则,使用原文件名称"),
    TIME("time", "使用时间 【yyyyMMdd-SSS 加 1位随机数】 + 原文件名称 (默认)"),
    UUID("uuid", "使用 32位uuid + 加原文件名称"),
    SNOWFLAKE("snowflake", "使用 18位雪花生成 + 加原文件名称"),

    ;
    private String value;
    private String desc;
}
