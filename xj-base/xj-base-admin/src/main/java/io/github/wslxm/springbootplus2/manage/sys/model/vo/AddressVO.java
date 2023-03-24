package io.github.wslxm.springbootplus2.manage.sys.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.github.wslxm.springbootplus2.core.base.model.BaseVo;
import io.github.wslxm.springbootplus2.core.utils.tree.BaseTree;

/**
 *  VO
 *
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>

 * @author ws
 * @email 1720696548@qq.com
 * @date 2023-03-24 10:04:20
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "AddressVO 对象", description = "")
public class AddressVO extends BaseTree<AddressVO,String>  {

    private static final long serialVersionUID = -1639085709816733696L;
    
    @ApiModelProperty(value = "父级挂接id" ,position = 0)
    private String pid;

    @ApiModelProperty(value = "区划名称" ,position = 1)
    private String name;

    @ApiModelProperty(value = "区划编码" ,position = 2)
    private String code;

    @ApiModelProperty(value = "备注" ,position = 3)
    private String remark;

    @ApiModelProperty(value = "级次id 0:省/自治区/直辖市 1:市级 2:县级" ,position = 4)
    private Integer level;

}

