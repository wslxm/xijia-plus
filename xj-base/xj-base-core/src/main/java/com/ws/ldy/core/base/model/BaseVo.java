package com.ws.ldy.core.base.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *  通用Vo,获取反序列类生成UUID
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/10/31 21:12
 * spring
 */
@Data
public class BaseVo extends Convert {

    @ApiModelProperty(notes = "ID")
    private String id;
}
