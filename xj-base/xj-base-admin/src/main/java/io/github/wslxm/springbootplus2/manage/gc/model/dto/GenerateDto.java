package io.github.wslxm.springbootplus2.manage.gc.model.dto;

import io.github.wslxm.springbootplus2.core.base.model.Convert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *  @author wangsong
 */
@Data
@ApiModel(value = "GenerateDto", description = "代码生成")
public class GenerateDto extends Convert {

    private static final long serialVersionUID = -33297418791559528L;
    /**
     * data [{ search：true }] 表示要为该字段添加搜索功能
     */
    @ApiModelProperty(value = "数据表格完整数据")
    private String data;

    @ApiModelProperty(value = "表名称")
    private String tableName;

    @ApiModelProperty(value = "数据源Id")
    private String dataSourceId;

    @ApiModelProperty(value = "表注释")
    private String tableComment;
}
