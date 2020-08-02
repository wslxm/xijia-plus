package com.ws.ldy.modules.admin.model.vo;

import com.ws.ldy.others.base.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


/**
 *   字典表
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Sun Nov 24 11:23:12 CST 2019
 */
@Data
@ApiModel(value = "DictionaryAdminVo", description = "字典表")
public class DictionaryAdminVo extends BaseVo {

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

    @ApiModelProperty(value = "子级")
    List<DictionaryAdminVo> dictList;
}

