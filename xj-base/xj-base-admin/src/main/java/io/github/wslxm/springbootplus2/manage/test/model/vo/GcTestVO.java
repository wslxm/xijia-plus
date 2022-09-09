package io.github.wslxm.springbootplus2.manage.test.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.github.wslxm.springbootplus2.core.base.model.BaseVo;

/**
 * 代码生成测试表 VO
 *
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>

 * @author ws
 * @email 1720696548@qq.com
 * @date 2022-09-09 16:18:55
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "GcTestVO 对象", description = "代码生成测试表")
public class GcTestVO extends BaseVo {

    private static final long serialVersionUID = -728130862469746688L;
    
    @ApiModelProperty(value = "图标 " ,position = 0)
    private String icon;

    @ApiModelProperty(value = "颜色选择器" ,position = 1)
    private String color;

}

