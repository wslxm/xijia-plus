package com.ws.ldy.modules.sys.gc.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "XjTableVO", description = "数据库表")
public class XjTableVO {

    @ApiModelProperty(value = "表名")
    private String name;

    @ApiModelProperty(value = "表描叙")
    private String comment;

}
