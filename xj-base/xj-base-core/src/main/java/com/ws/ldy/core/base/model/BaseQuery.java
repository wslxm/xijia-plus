package com.ws.ldy.core.base.model;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 通用查询字段
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2021/8/25 0025 11:53
 * @version 1.0.0
 */
@Data
public class BaseQuery extends Convert {

    @ApiModelProperty(value = "页数(小于1查询所有)", required = false, position = -1, example = "1")
    private Long current = 1L;

    @ApiModelProperty(value = "每页数量", required = false, position = -1, example = "20")
    private Long size = 20L;

    @ApiModelProperty(value = "是否导出Excel", required = false, position = -1, example = "false")
    private Boolean isExport = false;
}
