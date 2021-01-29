package com.ws.ldy.modules.yw.pets.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.modules.sys.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

/**
 * 医院表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 11:26:04
 */
@Data
@ToString(callSuper = true)
@TableName("t_pets_hospital")
@ApiModel(value = "PetsHospital 对象", description = "医院表")
public class PetsHospital extends BaseEntity {

    private static final long serialVersionUID = -503739073568051203L;
    
    /** 
     * 医院名称 
     */
    @TableField(value = "`name`")
    private String name;

    /** 
     * 电话 
     */
    @TableField(value = "phone")
    private String phone;

    /** 
     * 医院地址 
     */
    @TableField(value = "address")
    private String address;

    /** 
     * 1-禁用-黑名单 / 0-启用 
     */
    @TableField(value = "`disable`")
    private Integer disable;

}

