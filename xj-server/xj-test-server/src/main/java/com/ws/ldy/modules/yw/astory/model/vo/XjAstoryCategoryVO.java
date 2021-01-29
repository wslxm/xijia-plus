package com.ws.ldy.modules.yw.astory.model.vo;

import com.ws.ldy.modules.sys.base.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 段子分类表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-20 22:04:03
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "XjAstoryCategoryVO 对象", description = "段子分类表")
public class XjAstoryCategoryVO extends BaseVo {

    private static final long serialVersionUID = 0L;
    
    @ApiModelProperty(notes = "类别id" ,position = 0)
    private Integer typeid;

    @ApiModelProperty(notes = "类别名称" ,position = 1)
    private String type;

}

