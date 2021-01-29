package com.ws.ldy.modules.yw.pets.model.dto;

import com.ws.ldy.modules.sys.base.model.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

/**
 * 医院表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 11:26:04
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "PetsHospitalDTO 对象", description = "医院表")
public class PetsHospitalDTO extends BaseDto {

    private static final long serialVersionUID = -503739073626771462L;
    
    @ApiModelProperty(notes = "医院名称" ,position = 0)
    @Length(min=1, max=128,message = "医院名称 必须>=0 和 <=128位")
    private String name;

    @ApiModelProperty(notes = "电话" ,position = 1)
    @Length(min=1, max=32,message = "电话 必须>=0 和 <=32位")
    private String phone;

    @ApiModelProperty(notes = "医院地址" ,position = 2)
    @Length(min=1, max=512,message = "医院地址 必须>=0 和 <=512位")
    private String address;

//    @ApiModelProperty(notes = "1-禁用-黑名单 / 0-启用" ,position = 3)
//    @Range(min=0, max=9L,message = "1-禁用-黑名单 / 0-启用 必须>=0 和 <=9")
//    private Integer disable;

}

