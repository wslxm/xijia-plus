package com.github.wslxm.springbootplus2.manage.admin.model.query;

import com.github.wslxm.springbootplus2.core.base.model.Convert;
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
@ApiModel(value = "AdminDictionaryQuery", description = "字典表")
public class AdminDictionaryQuery extends Convert {

    private static final long serialVersionUID = 0L;

    @ApiModelProperty(value = "父级code, 传递了只查询指定code下数据，不传查询所有")
    private String code;

    @ApiModelProperty(value = "是否查询禁用数据 true-查询*默认  false-不查询")
    private Boolean isDisable;

    @ApiModelProperty(value = "是否需要最后一级数据 true-需要*默认   false-不需要")
    private Boolean isBottomLayer;

    @ApiModelProperty(value = "isTree 是否返回树结构数据  tree-是*默认  false-否(返回过滤后的 list列表)")
    private Boolean isTree;

}
