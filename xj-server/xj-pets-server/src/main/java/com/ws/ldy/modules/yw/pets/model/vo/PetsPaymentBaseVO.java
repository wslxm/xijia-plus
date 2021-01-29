package com.ws.ldy.modules.yw.pets.model.vo;

import com.ws.ldy.modules.sys.base.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "PetsPaymentBaseVO 对象", description = "缴费基数配置表")
public class PetsPaymentBaseVO extends BaseVo {

    private static final long serialVersionUID = -503731090792517646L;
    
    @ApiModelProperty(notes = "报销次数" ,position = 0)
    private Integer declareNum;

    @ApiModelProperty(notes = "基数比率( 金额 * 比率 )" ,position = 1)
    private Double ratio;

    @ApiModelProperty(notes = "禁用( 字典code)" ,position = 2)
    private Integer disable;

}

