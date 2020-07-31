package com.ws.ldy.modules.dev.model.dto;

import com.ws.ldy.others.base.model.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 开发规范
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-07-31 15:14:20
 */
@Data
@ApiModel(value = "DevNormDTO 对象", description = "开发规范")
public class DevNormDTO extends BaseDto {

    private static final long serialVersionUID = 0L;
    
    @ApiModelProperty(notes = "规范名称")
    private String name;

    @ApiModelProperty(notes = "规范内容(md-富文本)")
    private String content;

    @ApiModelProperty(notes = "排序")
    private Integer sort;

}

