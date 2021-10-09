package io.github.wslxm.springbootplus2.basepay.manage.model.dto;

import io.github.wslxm.springbootplus2.core.base.model.BaseDto;
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
 * 账单/流水/支付流水表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:55:32
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "PetsWalletFlowDTO 对象", description = "账单/流水/支付流水表")
public class PayWalletFlowDTO extends BaseDto {

    private static final long serialVersionUID = -503731388638433298L;

    @ApiModelProperty(notes = "账号id=用户id/ 平台为0*默认", position = 0)
    @Length(min = 1, max = 32, message = "账号id=用户id/ 平台为0*默认 必须>=0 和 <=32位")
    private String userId;

    @ApiModelProperty(notes = "订单号", position = 1)
    @Length(min = 1, max = 32, message = "订单号 必须>=0 和 <=32位")
    private String orderNo;

    @ApiModelProperty(notes = "交易后总金额( 元)", position = 4)
    @DecimalMin(value = "0", message = "交易后总金额 必须>=0")
    @DecimalMax(value = "9999999999", message = "交易后总金额 必须<=9999999999")
    private BigDecimal moneyAfter;

    @ApiModelProperty(notes = "金额( 不论支出收入都是正数)", position = 5)
    @DecimalMin(value = "0", message = "金额 必须>=0")
    @DecimalMax(value = "9999999999", message = "金额 必须<=9999999999")
    private BigDecimal money;

    @ApiModelProperty(notes = "流水类型(1-收入 2-支出)", position = 6)
    @Range(min = 0, max = 9L, message = "流水类型 必须>=0 和 <=9")
    private Integer walletType;

    @ApiModelProperty(notes = "业务类型( 字段code)", position = 7)
    @Range(min = 0, max = 9L, message = "业务类型 必须>=0 和 <=9")
    private Integer businessType;

    @ApiModelProperty(notes = "业务描叙", position = 8)
    private String businessDesc;

}

