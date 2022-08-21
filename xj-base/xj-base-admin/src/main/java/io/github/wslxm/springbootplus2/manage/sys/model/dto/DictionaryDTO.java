package io.github.wslxm.springbootplus2.manage.sys.model.dto;

import io.github.wslxm.springbootplus2.core.base.model.Convert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 *   字典表
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Sun Nov 24 11:23:12 CST 2019
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "DictionaryDTO", description = "字典表")
public class DictionaryDTO extends Convert {

    private static final long serialVersionUID = 0L;

    @ApiModelProperty(value = "字典类型")
    private String code;

    @ApiModelProperty(value = "字典名称")
    private String name;

    @ApiModelProperty(value = "父Id")
    private String pid;

    @ApiModelProperty(value = "描叙")
    private String desc;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "禁用（0-否，1-是）")
    private Integer disable;

    @ApiModelProperty(value = "扩展字段 1")
    private String ext1;

    @ApiModelProperty(value = "扩展字段 2")
    private String ext2;

    @ApiModelProperty(value = "扩展字段 3")
    private String ext3;

}

