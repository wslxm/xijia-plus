package com.ws.ldy.modules.yw.pets.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.modules.sys.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
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
@TableName("t_pets_invite_config")
@ApiModel(value = "PetsInviteConfig 对象", description = "邀请增加时长配置表")
public class PetsInviteConfig extends BaseEntity {

    private static final long serialVersionUID = -503862719250829316L;
    
    /** 
     * 类型(字典code 1-邀请用户)
     */
    @TableField(value = "type")
    private Integer type;

    /** 
     * 增加邀请人互助天数 
     */
    @TableField(value = "day_num")
    private Integer dayNum;

}

