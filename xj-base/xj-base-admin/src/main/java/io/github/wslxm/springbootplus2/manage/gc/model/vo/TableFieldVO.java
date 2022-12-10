package io.github.wslxm.springbootplus2.manage.gc.model.vo;


import lombok.Data;

/**
 * @author wangsong
 */
@Data
public class TableFieldVO {

    /**
     * 字段名
     */
    private String name;

    /**
     * 字段类型
     */
    private String type;

    /**
     * 字段描叙
     */
    private String desc;

    /**
     * 字段详情
     */
    private String typeDetail;

    /**
     * 是否可用为空
     */
    private String isNull;

    /**
     * 空串代码判断替换
     */
    /**
     * 默认数据
     */
    private String defaultVal;

    /**
     * 代码判断
     */
    /**
     * 是否未通用字段
     */
    Boolean isChecked;
}
