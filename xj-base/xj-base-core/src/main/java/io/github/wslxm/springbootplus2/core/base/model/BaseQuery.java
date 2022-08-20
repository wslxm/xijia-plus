package io.github.wslxm.springbootplus2.core.base.model;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 通用查询字段
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2021/8/25 0025 11:53
 * @version 1.0.1
 */
@Data
public class BaseQuery extends Convert {

    @ApiModelProperty(value = "页数(小于等于0查询所有,不传默认1)", required = false, position = -3, example = "1")
    private Long current = 1L;

    @ApiModelProperty(value = "每页数量(不传默认10)", required = false, position = -2, example = "10")
    private Long size = 10L;

}
