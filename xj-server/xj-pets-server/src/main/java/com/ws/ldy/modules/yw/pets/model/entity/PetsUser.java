package com.ws.ldy.modules.yw.pets.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.modules.sys.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

/**
 * 用户表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 11:03:46
 */
@Data
@ToString(callSuper = true)
@TableName("t_pets_user")
@ApiModel(value = "PetsUser 对象", description = "用户表")
public class PetsUser extends BaseEntity {

    private static final long serialVersionUID = -503733462457520139L;

    /**
     * 姓名 
     */
    @TableField(value = "full_name")
    private String fullName;

    /**
     * 电话 
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 身份证号 
     */
    @TableField(value = "id_card")
    private String idCard;

    /**
     * 身份证前 
     */
    @TableField(value = "id_card_front_pic")
    private String idCardFrontPic;

    /**
     * 身份证后 
     */
    @TableField(value = "id_card_after_pic")
    private String idCardAfterPic;

    /**
     * 省 
     */
    @TableField(value = "province")
    private String province;

    /**
     * 市 
     */
    @TableField(value = "city")
    private String city;

    /**
     * 区 
     */
    @TableField(value = "area")
    private String area;

    /**
     * 用户微信openId 
     */
    @TableField(value = "wx_open_id")
    private String wxOpenId;

    /**
     * 用户头像 (默认使用微信头像) 
     */
    @TableField(value = "wx_head_pic")
    private String wxHeadPic;

    /**
     * 用户昵称( 默认微信昵称) 
     */
    @TableField(value = "wx_name")
    private String wxName;

    /**
     * 用户性别(默认微信性别)
     */
    @TableField(value = "wx_gender")
    private Integer wxGender;

    /**
     * 1-禁用-黑名单 / 0-启用 
     */
    @TableField(value = "`disable`")
    private Integer disable;

}

