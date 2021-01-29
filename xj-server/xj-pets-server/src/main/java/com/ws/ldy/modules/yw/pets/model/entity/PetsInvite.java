package com.ws.ldy.modules.yw.pets.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.modules.sys.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
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
@TableName("t_pets_invite")
@ApiModel(value = "PetsInvite 对象", description = "邀请表")
public class PetsInvite extends BaseEntity {

    private static final long serialVersionUID = -503730911821565976L;
    
    /** 
     * 邀请人id 
     */
    @TableField(value = "user_id")
    private String userId;

    /** 
     * 被邀请人id 
     */
    @TableField(value = "in_user_id")
    private String inUserId;

    /** 
     * 增加邀请人会员天数 
     */
    @TableField(value = "day_num")
    private Integer dayNum;

    /** 
     * 邀请人昵称 
     */
    @TableField(value = "`name`")
    private String name;

    /** 
     * 被邀请人昵称 
     */
    @TableField(value = "in_name")
    private String inName;

}

