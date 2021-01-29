package com.ws.ldy.modules.yw.pets.model.dto;

import com.ws.ldy.modules.sys.base.model.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

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
@ApiModel(value = "PetsRiskManageDTO 对象", description = "风控管理表")
public class PetsRiskManageDTO extends BaseDto {

    private static final long serialVersionUID = -503733498918604813L;
    
    @ApiModelProperty(notes = "用户id" ,position = 0)
    @Length(min=1, max=32,message = "用户id 必须>=0 和 <=32位")
    private String userId;

    @ApiModelProperty(notes = "微信昵称" ,position = 1)
    @Length(min=1, max=32,message = "微信昵称 必须>=0 和 <=32位")
    private String wxName;

    @ApiModelProperty(notes = "姓名" ,position = 2)
    @Length(min=1, max=32,message = "姓名 必须>=0 和 <=32位")
    private String fullName;

    @ApiModelProperty(notes = "电话" ,position = 3)
    @Length(min=1, max=32,message = "电话 必须>=0 和 <=32位")
    private String phone;

    @ApiModelProperty(notes = "身份证号" ,position = 4)
    @Length(min=1, max=32,message = "身份证号 必须>=0 和 <=32位")
    private String idCardFront;

    @ApiModelProperty(notes = "申报总次数" ,position = 5)
    @Range(min=0, max=99L,message = "申报总次数 必须>=0 和 <=99")
    private Integer declareNum;

    @ApiModelProperty(notes = "申报总金额" ,position = 6)
    @DecimalMin(value = "0",message = "申报总金额 必须>=0")
    @DecimalMax(value = "9999999999",message = "申报总金额 必须<=9999999999")
    private BigDecimal declareMoney;

    @ApiModelProperty(notes = "申报次数,近2月" ,position = 7)
    private Integer declareMouth2Num;

    @ApiModelProperty(notes = "申报金额,近2月" ,position = 8)
    private String declareMouth2Money;

    @ApiModelProperty(notes = "备注" ,position = 9)
    @Length(min=1, max=256,message = "备注 必须>=0 和 <=256位")
    private String remarks;

}

