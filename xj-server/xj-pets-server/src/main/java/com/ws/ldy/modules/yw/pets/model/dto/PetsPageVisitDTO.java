package com.ws.ldy.modules.yw.pets.model.dto;

import com.ws.ldy.modules.sys.base.model.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

/**
 * 页面访问记录表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 10:54:16
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "PetsPageVisitDTO 对象", description = "页面访问记录表")
public class PetsPageVisitDTO extends BaseDto {

    private static final long serialVersionUID = -503731071599382535L;
    
    @ApiModelProperty(notes = "页面名称" ,position = 0)
    @Length(min=1, max=64,message = "页面名称 必须>=0 和 <=64位")
    private String pageName;

    @ApiModelProperty(notes = "页面url地址" ,position = 1)
    @Length(min=1, max=512,message = "页面url地址 必须>=0 和 <=512位")
    private String pageUrl;

//    @ApiModelProperty(notes = "访问用户id" ,position = 2)
//    @Length(min=1, max=32,message = "访问用户id 必须>=0 和 <=32位")
//    private String userId;
//
//    @ApiModelProperty(notes = "访问用户ip" ,position = 3)
//    @Length(min=1, max=32,message = "访问用户ip 必须>=0 和 <=32位")
//    private String ip;

}

