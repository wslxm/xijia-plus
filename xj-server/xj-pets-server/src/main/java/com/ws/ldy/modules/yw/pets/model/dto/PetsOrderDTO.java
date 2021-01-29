package com.ws.ldy.modules.yw.pets.model.dto;

import com.ws.ldy.modules.sys.base.model.Convert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 支付订单/缴费管理表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:54:07
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "PetsOrderDTO 对象", description = "支付订单/缴费管理表")
public class PetsOrderDTO extends Convert {

    private static final long serialVersionUID = -503731032437166100L;

    @ApiModelProperty(notes = "宠物id( 如果是新添加宠物：不填， 如果是续费：必传)", position = 1)
    @Length(min = 1, max = 32, message = "宠物id 必须>=0 和 <=32位")
    @NotBlank
    private String petId;

    @ApiModelProperty(notes = "月费表Id", position = 1)
    @Length(min = 1, max = 32, message = "宠物id 必须>=0 和 <=32位")
    private String monthFeeId;

    @ApiModelProperty(notes = "本次支付是否开通自动续费", position = 11)
    private Boolean isAutoRenew;
}

