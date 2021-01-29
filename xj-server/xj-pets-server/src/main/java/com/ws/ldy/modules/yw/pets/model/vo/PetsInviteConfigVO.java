package com.ws.ldy.modules.yw.pets.model.vo;

import com.ws.ldy.modules.sys.base.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;


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
@ApiModel(value = "PetsInviteConfigVO 对象", description = "邀请增加时长配置表")
public class PetsInviteConfigVO extends BaseVo {

    private static final long serialVersionUID = -503862719284383755L;
    
    @ApiModelProperty(notes = "类型(字典code 1-邀请用户)" ,position = 0)
    private Integer type;

    @ApiModelProperty(notes = "增加邀请人互助天数" ,position = 1)
    private Integer dayNum;

}

