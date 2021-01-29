package com.ws.ldy.modules.yw.pets.model.dto;

import com.ws.ldy.modules.sys.base.model.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

/**
 * 银行卡管理
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 18:49:29
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "PetsBankCardDTO 对象", description = "银行卡管理")
public class PetsBankCardDTO extends BaseDto {

    private static final long serialVersionUID = -503850663378292752L;

//    @ApiModelProperty(notes = "用户id" ,position = 0)
//    @Length(min=1, max=32,message = "用户id 必须>=0 和 <=32位")
//    private String userId;

    @ApiModelProperty(notes = "银行名称", position = 1)
    @Length(min = 1, max = 32, message = "银行名称 必须>=0 和 <=32位")
    private String bankName;

    @ApiModelProperty(notes = "银行卡号", position = 2)
    @Length(min = 1, max = 32, message = "银行卡号 必须>=0 和 <=32位")
    private String bankNo;

    @ApiModelProperty(notes = "电话", position = 3)
    @Length(min = 1, max = 32, message = "电话 必须>=0 和 <=32位")
    private String phone;

    @ApiModelProperty(notes = "姓名", position = 4)
    @Length(min = 1, max = 32, message = "姓名 必须>=0 和 <=32位")
    private String fullName;

//    @ApiModelProperty(notes = "是否-绑定( 0-否 1-是)" ,position = 5)
//    private Boolean isBinding;
//
//    @ApiModelProperty(notes = "是否-默认( 0-否 1-是)" ,position = 6)
//    private Boolean isDefault;

}

