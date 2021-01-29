package com.ws.ldy.modules.yw.pets.model.vo;

import com.ws.ldy.modules.sys.base.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;


/**
 * 邀请表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 10:53:38
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "PetsInviteVO 对象", description = "邀请表")
public class PetsInviteVO extends BaseVo {

    private static final long serialVersionUID = -503730911846731804L;
    
    @ApiModelProperty(notes = "邀请人id" ,position = 0)
    private String userId;

    @ApiModelProperty(notes = "被邀请人id" ,position = 1)
    private String inUserId;

    @ApiModelProperty(notes = "增加邀请人会员天数" ,position = 2)
    private Integer dayNum;

    @ApiModelProperty(notes = "邀请人昵称" ,position = 3)
    private String name;

    @ApiModelProperty(notes = "被邀请人昵称" ,position = 4)
    private String inName;

}

