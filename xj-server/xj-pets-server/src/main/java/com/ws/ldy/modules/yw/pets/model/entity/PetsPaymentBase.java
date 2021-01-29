package com.ws.ldy.modules.yw.pets.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.modules.sys.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

/**
 * 缴费基数配置表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 10:54:21
 */
@Data
@ToString(callSuper = true)
@TableName("t_pets_payment_base")
@ApiModel(value = "PetsPaymentBase 对象", description = "缴费基数配置表")
public class PetsPaymentBase extends BaseEntity {

    private static final long serialVersionUID = -503731090767351819L;
    
    /** 
     * 报销次数 
     */
    @TableField(value = "declare_num")
    private Integer declareNum;

    /** 
     * 基数比率( 金额 * 比率 ) 
     */
    @TableField(value = "ratio")
    private Double ratio;

    /** 
     * 禁用( 字典code) 
     */
    @TableField(value = "`disable`")
    private Integer disable;

}

