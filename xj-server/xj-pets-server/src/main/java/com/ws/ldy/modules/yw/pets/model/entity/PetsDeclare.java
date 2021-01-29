package com.ws.ldy.modules.yw.pets.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.modules.sys.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 申报信息表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-31 17:41:21
 */
@Data
@ToString(callSuper = true)
@TableName("t_pets_declare")
@ApiModel(value = "PetsDeclare 对象", description = "申报信息表")
public class PetsDeclare extends BaseEntity {

    private static final long serialVersionUID = -504558292161400837L;

    /**
     * 申报人id
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 宠物id
     */
    @TableField(value = "pet_id")
    private String petId;

    /**
     * 治疗医院id
     */
    @TableField(value = "hospital_id")
    private String hospitalId;

    /**
     * 实发金额
     */
    @TableField(value = "paid_in_amount")
    private BigDecimal paidInAmount;

    /**
     * 申报金额
     */
    @TableField(value = "declare_money")
    private BigDecimal declareMoney;

    /**
     * 报销方式=支付渠道(字典code)
     */
    @TableField(value = "pay_channel")
    private Integer payChannel;

    /**
     * 申报状态(字典code 0-待审核*默认 1-已驳回 2-待付款 3-已完成)
     */
    @TableField(value = "state")
    private Integer state;

    /**
     * 宠物年龄 (申请时)
     */
    @TableField(value = "pet_age")
    private Double petAge;

    /**
     * 报销次数 (申请时)
     */
    @TableField(value = "declare_num")
    private Integer declareNum;

    /**
     * 累积缴费金额 (申请时)
     */
    @TableField(value = "pay_money")
    private BigDecimal payMoney;

    /**
     * 保单加入时间（申请时）
     */
    @TableField(value = "apply_time")
    private LocalDateTime applyTime;

    /**
     * 剩余天数（申请时）
     */
    @TableField(value = "expiration_day_num")
    private Integer expirationDayNum;

    /**
     * 帮助次数( 申请时)
     */
    @TableField(value = "help_num")
    private Integer helpNum;

    /**
     * 加入天数（申请时）
     */
    @TableField(value = "join_day_num")
    private Integer joinDayNum;

    /**
     * 生病记录图
     */
    @TableField(value = "sick_record_pic")
    private String sickRecordPic;

    /**
     * 费用明细图
     */
    @TableField(value = "expense_details_pic")
    private String expenseDetailsPic;

    /**
     * 费用发票图
     */
    @TableField(value = "expense_invoice_pic")
    private String expenseInvoicePic;

    /**
     * 病因
     */
    @TableField(value = "pathogeny")
    private String pathogeny;

    /**
     * 审核失败原因备注( 多次审核 | 线分隔, 保留之前审核记录)
     */
    @TableField(value = "remarks")
    private String remarks;


    /**
     * 审核失败类型（字典code）
     */
    @TableField(value = "error_type")
    private Integer errorType;

    /**
     * 审核通过时间
     */
    @TableField(value = "pass_time")
    private LocalDateTime passTime;

    /**
     * 支付时间
     */
    @TableField(value = "pay_time")
    private LocalDateTime payTime;

    /**
     * 支付订单号
     */
    @TableField(value = "order_no")
    private String orderNo;

}
