package com.ws.ldy.modules.business.caipu.model.dto;


import com.ws.ldy.modules.sys.base.model.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

/**
 * 字典表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-10-04 21:44:55
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "CpCategoryDTO 对象", description = "字典表")
public class CpCategoryDTO extends BaseDto {

    private static final long serialVersionUID = 0L;
    
    @ApiModelProperty(notes = "字典类型" ,position = 0)
    @Length(min=1, max=32,message = "字典类型 必须小于32位")
    private String code;

    @ApiModelProperty(notes = "字典名称" ,position = 1)
    @Length(min=1, max=32,message = "字典名称 必须小于32位")
    private String name;

    @ApiModelProperty(notes = "父Id" ,position = 2)
    @Length(min=1, max=32,message = "父Id 必须小于32位")
    private String pid;

    @ApiModelProperty(notes = "描叙" ,position = 3)
    @Length(min=1, max=128,message = "描叙 必须小于128位")
    private String desc;

    @ApiModelProperty(notes = "排序" ,position = 4)
    @Range(min=0, max=2147483647L,message = "排序 必须小于2147483647")
    private Integer sort;

    @ApiModelProperty(notes = "禁用(0-否 1-是)" ,position = 5)
    @Range(min=0, max=9L,message = "禁用 必须小于9")
    private Integer disable;

}

