package com.ws.ldy.modules.yw.pets.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.modules.sys.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 宠物表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 10:54:33
 */
@Data
@ToString(callSuper = true)
@TableName("t_pets_pet_info")
@ApiModel(value = "PetsPetInfo 对象", description = "宠物表")
public class PetsPetInfo extends BaseEntity {

    private static final long serialVersionUID = -503731141237411844L;
    
    /** 
     * 宠物主人账号id 
     */
    @TableField(value = "user_id")
    private String userId;

    /** 
     * 宠物名称 
     */
    @TableField(value = "nickname")
    private String nickname;
    /**
     * 宠物年龄
     */
    @TableField(value = "age")
    private Double age;
    /** 
     * 宠物性别(字典 code) 
     */
    @TableField(value = "sex")
    private Integer sex;

    /** 
     * 宠物类别(宠物字典 code) 
     */
    @TableField(value = "type")
    private String type;

    /** 
     * 宠物品种(宠物字典 code) 
     */
    @TableField(value = "breed")
    private String breed;

    /** 
     * 宠物头像 
     */
    @TableField(value = "head_pic")
    private String headPic;

    /** 
     * 宠物图片(索引 0- 正面 1-左侧 2-右侧 3-主人合照) 
     */
    @TableField(value = "photo_pic")
    private String photoPic;

    /** 
     * 加入时间(首次缴费时间) 
     */
    @TableField(value = "join_time")
    private LocalDateTime joinTime;

    /** 
     * 过期时间 (T+1) 
     */
    @TableField(value = "expiration_time")
    private LocalDateTime expirationTime;

    /** 
     * 保单申请时间(过期后再续费将更新，用于计算连续天数) 
     */
    @TableField(value = "apply_time")
    private LocalDateTime applyTime;

    /** 
     * 累积获赠时长(时间单位 分) 
     */
    @TableField(value = "cumulative_time")
    private Integer cumulativeTime;

    /** 
     * 是否-默认( 如设置为默认,所有赠送时长都将分发给默认绑定宠物) 
     */
    @TableField(value = "is_default")
    private Boolean isDefault;

    /** 
     * 是否-自动续约 
     */
    @TableField(value = "is_auto_renew")
    private Boolean isAutoRenew;

    /**
     * 帮助次数
     */
    @TableField(value = "help_num")
    private Integer helpNum;

    /**
     * 获帮助次数
     */
    @TableField(value = "in_help_num")
    private Integer inHelpNum;
}

