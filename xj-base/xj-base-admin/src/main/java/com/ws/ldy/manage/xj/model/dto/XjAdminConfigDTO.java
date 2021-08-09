package com.ws.ldy.manage.xj.model.dto;

import com.ws.ldy.core.base.model.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

/**
 * 系统全局数据信息配置表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-08-31 18:31:44
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "XjAdminConfigDTO 对象", description = "系统全局数据信息配置表")
public class XjAdminConfigDTO extends BaseDto {

    private static final long serialVersionUID = 0L;
    
    @ApiModelProperty(notes = "配置code|搜索值(不能重复)" ,position = 0)
    @Length(min=1, max=32,message = "配置code|搜索值 必须小于32位")
    private String code;

    @ApiModelProperty(notes = "配置名称" ,position = 1)
    @Length(min=1, max=32,message = "配置名称 必须小于32位")
    private String name;

    @ApiModelProperty(notes = "类型(0-文本 1-图片)" ,position = 2)
    private Integer type;

    @ApiModelProperty(notes = "配置内容" ,position = 2)
    private String content;

    @ApiModelProperty(notes = "排序" ,position = 3)
    @Range(min=0, max=2147483647L,message = "排序 必须小于2147483647")
    private Integer sort;

}

