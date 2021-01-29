package com.ws.ldy.modules.yw.pets.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.modules.sys.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;
import java.math.BigDecimal;

/**
 * 风控管理表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 11:03:55
 */
@Data
@ToString(callSuper = true)
@TableName("t_pets_risk_manage")
@ApiModel(value = "PetsRiskManage 对象", description = "风控管理表")
public class PetsRiskManage extends BaseEntity {

    private static final long serialVersionUID = -503733498855690249L;
    
    /** 
     * 用户id 
     */
    @TableField(value = "user_id")
    private String userId;

    /** 
     * 微信昵称 
     */
    @TableField(value = "wx_name")
    private String wxName;

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
    @TableField(value = "id_card_front")
    private String idCardFront;

    /** 
     * 申报总次数 
     */
    @TableField(value = "declare_num")
    private Integer declareNum;

    /** 
     * 申报总金额 
     */
    @TableField(value = "declare_money")
    private BigDecimal declareMoney;

    /** 
     * 申报次数,近2月 
     */
    @TableField(value = "declare_mouth2_num")
    private Integer declareMouth2Num;

    /** 
     * 申报金额,近2月 
     */
    @TableField(value = "declare_mouth2_money")
    private BigDecimal declareMouth2Money;

    /** 
     * 备注 
     */
    @TableField(value = "remarks")
    private String remarks;

}

