package com.ws.ldy.modules.yw.pets.model.dto;

import com.ws.ldy.modules.sys.base.model.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

/**
 * 邀请增加时长配置表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 19:37:23
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "PetsInviteConfigDTO 对象", description = "邀请增加时长配置表")
public class PetsInviteConfigDTO extends BaseDto {

    private static final long serialVersionUID = -503862719309549571L;
    
    @ApiModelProperty(notes = "类型(字典code 1-邀请用户)" ,position = 0)
    @Range(min=0, max=9L,message = "类型 必须>=0 和 <=9")
    private Integer type;

    @ApiModelProperty(notes = "增加邀请人互助天数" ,position = 1)
    @Range(min=0, max=1215752191L,message = "增加邀请人互助天数 必须>=0 和 <=1215752191")
    private Integer dayNum;

}

