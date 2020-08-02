package com.ws.ldy.modules.dev.model.dto;

import com.ws.ldy.others.base.model.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *   更新内容
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-06-27 12:23:10
 */
@Data
@ApiModel(value = "DevRenewDTO 对象", description = "更新内容")
public class DevRenewDTO extends BaseDto {

    private static final long serialVersionUID = 0L;
    
    @ApiModelProperty(notes = "更新名称")
    private String name;

    @ApiModelProperty(notes = "更新内容(富文本)")
    private String content;

    @ApiModelProperty(notes = "更新类型(1-管理端 2-用户端 3-app端)")
    private Integer type;

}

