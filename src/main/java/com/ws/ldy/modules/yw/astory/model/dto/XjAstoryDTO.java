package com.ws.ldy.modules.yw.astory.model.dto;

import com.ws.ldy.modules.sys.base.model.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 段子表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-20 22:04:11
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "XjAstoryDTO 对象", description = "段子表")
public class XjAstoryDTO extends BaseDto {

    private static final long serialVersionUID = 0L;
    
    @ApiModelProperty(notes = "标题" ,position = 0)
    private String title;

    @ApiModelProperty(notes = "段子内容" ,position = 1)
    private String content;

    @ApiModelProperty(notes = "类别id" ,position = 2)
    private Integer typeid;

    @ApiModelProperty(notes = "-" ,position = 3)
    private Integer hits;

    @ApiModelProperty(notes = "段子生产日期" ,position = 4)
    private LocalDateTime dateandtime;

}

