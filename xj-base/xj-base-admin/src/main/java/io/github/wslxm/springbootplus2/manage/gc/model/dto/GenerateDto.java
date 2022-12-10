package io.github.wslxm.springbootplus2.manage.gc.model.dto;

import io.github.wslxm.springbootplus2.core.base.model.Convert;


import lombok.Data;

/**
 * @author wangsong
 */
@Data
public class GenerateDto extends Convert {

    private static final long serialVersionUID = -33297418791559528L;
    /**
     * data [{ search：true }] 表示要为该字段添加搜索功能
     */
    /**
     * 数据表格完整数据
     */
    private String data;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 数据源Id
     */
    private String dataSourceId;

    /**
     * 表注释
     */
    private String tableComment;
}
