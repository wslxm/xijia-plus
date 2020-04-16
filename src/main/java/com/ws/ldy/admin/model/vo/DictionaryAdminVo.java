package com.ws.ldy.admin.model.vo;

import com.ws.ldy.base.model.vo.BaseAdminVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * TODO  代码生成器自动生成，请自定义描叙
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Sun Nov 24 11:23:12 CST 2019
 */
@Data
@ApiModel(value = "DictionaryAdminVo", description = "字典表")
public class DictionaryAdminVo extends BaseAdminVo {

    private static final long serialVersionUID = 0L;

    @ApiModelProperty(value = "字典类型")
    private String type;
    @ApiModelProperty(value = "搜索值")
    private String key;
    @ApiModelProperty(value = "选择值")
    private String value;
    @ApiModelProperty(value = "描叙")
    private String desc;
    @ApiModelProperty(value = "树菜单列表需要name字段显示")
    private String name;
}

