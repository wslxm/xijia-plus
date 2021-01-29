package com.ws.ldy.modules.yw.pets.model.vo;

import com.ws.ldy.modules.sys.base.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;


/**
 * 互助资金抽成配置表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 10:52:46
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "PetsCapitalConfigVO 对象", description = "互助资金抽成配置表")
public class PetsCapitalConfigVO extends BaseVo {

    private static final long serialVersionUID = -503730692409135125L;
    
    @ApiModelProperty(notes = "平台抽成比率( 如：0.18 = 18 % )" ,position = 0)
    private Double platformPercentage;

}

