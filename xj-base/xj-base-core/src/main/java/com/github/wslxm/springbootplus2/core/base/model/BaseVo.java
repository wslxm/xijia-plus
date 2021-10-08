package com.github.wslxm.springbootplus2.core.base.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

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

    @ApiModelProperty(notes = "ID", position = 0)
    private String id;

    @ApiModelProperty(value = "创建时间", position = 0)
    private LocalDateTime createTime;
}
