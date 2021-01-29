package com.ws.ldy.modules.yw.pets.model.dto;

import com.ws.ldy.modules.sys.base.model.Convert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

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
@ApiModel(value = "PetsInviteDTO 对象", description = "邀请表")
public class PetsInviteDTO extends Convert {

    private static final long serialVersionUID = -503730911871897610L;
    
    @ApiModelProperty(notes = "邀请人id" ,position = 0)
    @Length(min=1, max=32,message = "邀请人id 必须>=0 和 <=32位")
    private String userId;

//    @ApiModelProperty(notes = "被邀请人id" ,position = 1)
//    @Length(min=1, max=32,message = "被邀请人id 必须>=0 和 <=32位")
//    private String inUserId;

//    @ApiModelProperty(notes = "增加邀请人会员天数" ,position = 2)
//    @Range(min=0, max=1215752191L,message = "增加邀请人会员天数 必须>=0 和 <=1215752191")
//    private Integer dayNum;

    @ApiModelProperty(notes = "邀请人昵称" ,position = 3)
    @Length(min=1, max=32,message = "邀请人昵称 必须>=0 和 <=32位")
    private String name;

//    @ApiModelProperty(notes = "被邀请人昵称" ,position = 4)
//    @Length(min=1, max=32,message = "被邀请人昵称 必须>=0 和 <=32位")
//    private String inName;

}

