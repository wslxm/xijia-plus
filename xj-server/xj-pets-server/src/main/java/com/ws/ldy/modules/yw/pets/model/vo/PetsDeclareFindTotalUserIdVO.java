package com.ws.ldy.modules.yw.pets.model.vo;

import com.ws.ldy.modules.sys.base.model.Convert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;


/**
 * 申报信息分页查询 额外返回参数
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-31 17:41:21
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "PetsDeclareFindTotalUserIdVO 对象", description = "查询指定用户的申报数据 统计")
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class PetsDeclareFindTotalUserIdVO extends Convert {


    private static final long serialVersionUID = -5667640369720260611L;
    @ApiModelProperty(notes = "申报总金额(用户输入)", position = 1)
    private BigDecimal declareMoneyTotal;

    @ApiModelProperty(notes = "申报总次数", position = 1)
    private Integer  declareNum;

}
