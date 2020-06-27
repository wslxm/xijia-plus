package com.ws.ldy.dev.baseModel;

import com.ws.ldy.base.convert.Convert;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * TODO  通用Vo ,获取反序列类生成UUID
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/10/31 21:12
 * spring
 */
@Data  //set,get
@NoArgsConstructor  //无参构造
public class BaseDevVo extends Convert {

    @ApiModelProperty(notes = "ID")
    private Integer id;

    @ApiModelProperty(notes = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(notes = "最后更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(notes = "逻辑删除（0：正常  1：删除）")
    private Integer deleted;
}
