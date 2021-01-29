package com.ws.ldy.modules.yw.pets.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.modules.sys.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

/**
 * 银行卡管理
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 18:49:29
 */
@Data
@ToString(callSuper = true)
@TableName("t_pets_bank_card")
@ApiModel(value = "PetsBankCard 对象", description = "银行卡管理")
public class PetsBankCard extends BaseEntity {

    private static final long serialVersionUID = -503850663315378186L;
    
    /** 
     * 用户id 
     */
    @TableField(value = "user_id")
    private String userId;

    /** 
     * 银行名称 
     */
    @TableField(value = "bank_name")
    private String bankName;

    /** 
     * 银行卡号 
     */
    @TableField(value = "bank_no")
    private String bankNo;

    /** 
     * 电话 
     */
    @TableField(value = "phone")
    private String phone;

    /** 
     * 姓名 
     */
    @TableField(value = "full_name")
    private String fullName;

    /** 
     * 是否-绑定( 0-否 1-是) 
     */
    @TableField(value = "is_binding")
    private Boolean isBinding;

    /** 
     * 是否-默认( 0-否 1-是) 
     */
    @TableField(value = "is_default")
    private Boolean isDefault;

}

