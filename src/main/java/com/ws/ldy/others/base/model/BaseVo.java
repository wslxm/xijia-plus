package com.ws.ldy.others.base.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *  通用Vo,获取反序列类生成UUID
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/10/31 21:12
 * spring
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BaseVo extends Convert {

    @ApiModelProperty(notes = "ID")
    private Integer id;
}
