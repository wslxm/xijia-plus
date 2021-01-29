package com.ws.ldy.modules.yw.pets.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.modules.sys.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;
import java.math.BigDecimal;

/**
 * 月费表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 10:53:43
 */
@Data
@ToString(callSuper = true)
@TableName("t_pets_month_fee")
@ApiModel(value = "PetsMonthFee 对象", description = "月费表")
public class PetsMonthFee extends BaseEntity {

    private static final long serialVersionUID = -503730931450908693L;
    
    /** 
     * 月数( 1/3/6/12) 
     */
    @TableField(value = "month_num")
    private Integer monthNum;

    /** 
     * 月费-对应月数( 30/90/180/360) 
     */
    @TableField(value = "month_fee")
    private BigDecimal monthFee;

    /** 
     * 描叙 
     */
    @TableField(value = "month_desc")
    private String monthDesc;

}

