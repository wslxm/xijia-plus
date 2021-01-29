package com.ws.ldy.modules.yw.pets.model.vo;

import com.ws.ldy.modules.sys.base.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "PetsDeclareVO 对象", description = "申报信息表")
public class PetsDeclareVO extends BaseVo {

    private static final long serialVersionUID = -504558292190760969L;

    @ApiModelProperty(notes = "申报人id", position = 0)
    private String userId;

    @ApiModelProperty(notes = "宠物id", position = 1)
    private String petId;

    @ApiModelProperty(notes = "申请时间", position = 1)
    private LocalDateTime createTime;

    @ApiModelProperty(notes = "治疗医院id", position = 2)
    private String hospitalId;

    @ApiModelProperty(notes = "实发金额", position = 3)
    private BigDecimal paidInAmount;

    @ApiModelProperty(notes = "申报金额", position = 4)
    private BigDecimal declareMoney;

    @ApiModelProperty(notes = "报销方式=支付渠道(字典code)", position = 5)
    private Integer payChannel;

    @ApiModelProperty(notes = "申报状态(字典code 0-待审核*默认 1-已驳回 2-待付款 3-已完成)", position = 6)
    private Integer state;

    @ApiModelProperty(notes = "宠物年龄 (申请时)", position = 7)
    private Double petAge;

    @ApiModelProperty(notes = "报销次数 (申请时)", position = 8)
    private Integer declareNum;

    @ApiModelProperty(notes = "累积缴费金额 (申请时)", position = 9)
    private BigDecimal payMoney;

    @ApiModelProperty(notes = "保单加入时间（申请时）", position = 10)
    private LocalDateTime applyTime;

    @ApiModelProperty(notes = "剩余天数（申请时）", position = 11)
    private Integer expirationDayNum;

    @ApiModelProperty(notes = "帮助次数( 申请时)", position = 12)
    private Integer helpNum;

    @ApiModelProperty(notes = "加入天数（申请时）", position = 13)
    private Integer joinDayNum;

    @ApiModelProperty(notes = "生病记录图", position = 14)
    private String sickRecordPic;

    @ApiModelProperty(notes = "费用明细图", position = 15)
    private String expenseDetailsPic;

    @ApiModelProperty(notes = "费用发票图", position = 16)
    private String expenseInvoicePic;

    @ApiModelProperty(notes = "病因", position = 17)
    private String pathogeny;

    @ApiModelProperty(notes = "审核备注( 多次审核 | 线分隔, 保留之前审核记录)", position = 18)
    private String remarks;

    @ApiModelProperty(notes = "审核失败类型（字典code）", position = 19)
    private Integer errorType;

    @ApiModelProperty(notes = "审核通过时间", position = 20)
    private LocalDateTime passTime;

    @ApiModelProperty(notes = "支付时间", position = 29)
    private LocalDateTime payTime;

    @ApiModelProperty(notes = "支付订单号", position = 29)
    private String orderNo;

    @ApiModelProperty(notes = "宠物信息", position = 21)
    private PetsUserVO petsUserVO;

    @ApiModelProperty(notes = "宠物信息", position = 21)
    private PetsPetInfoVO petInfoVO;

    @ApiModelProperty(notes = "医院信息", position = 22)
    private PetsHospitalVO hospitalVO;
}
