package com.ws.ldy.modules.yw.pets.model.vo;

import com.ws.ldy.modules.sys.base.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "PetsRiskManageVO 对象", description = "风控管理表")
public class PetsRiskManageVO extends BaseVo {

    private static final long serialVersionUID = -503733498880856083L;
    
    @ApiModelProperty(notes = "用户id" ,position = 0)
    private String userId;

    @ApiModelProperty(notes = "微信昵称" ,position = 1)
    private String wxName;

    @ApiModelProperty(notes = "姓名" ,position = 2)
    private String fullName;

    @ApiModelProperty(notes = "电话" ,position = 3)
    private String phone;

    @ApiModelProperty(notes = "身份证号" ,position = 4)
    private String idCardFront;

    @ApiModelProperty(notes = "申报总次数" ,position = 5)
    private Integer declareNum;

    @ApiModelProperty(notes = "申报总金额" ,position = 6)
    private BigDecimal declareMoney;

    @ApiModelProperty(notes = "申报次数,近2月" ,position = 7)
    private Integer declareMouth2Num;

    @ApiModelProperty(notes = "申报金额,近2月" ,position = 8)
    private String declareMouth2Money;

    @ApiModelProperty(notes = "备注" ,position = 9)
    private String remarks;

}

