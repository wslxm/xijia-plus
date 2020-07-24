package com.ws.ldy.modules.dev.model.dto;

import com.ws.ldy.others.base.model.dev.BaseDevDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * TODO  开发规范
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-06-27 12:22:53
 */
@Data
@ApiModel(value = "DevNormDTO 对象", description = "开发规范")
public class DevNormDTO extends BaseDevDto {

    private static final long serialVersionUID = 0L;
    
    @ApiModelProperty(notes = "规范名称")
    private String name;

    @ApiModelProperty(notes = "规范内容(富文本)")
    private String content;

    @ApiModelProperty(notes = "排序")
    private Integer sort;

}

