package com.ws.ldy.modules.caipu.model.vo;

import com.ws.ldy.others.base.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 字典表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-10-04 21:44:55
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "CpCategoryVO 对象", description = "字典表")
public class CpCategoryVO extends BaseVo {

    private static final long serialVersionUID = 0L;

    @ApiModelProperty(notes = "字典类型", position = 0)
    private String code;

    @ApiModelProperty(notes = "字典名称", position = 1)
    private String name;

    @ApiModelProperty(notes = "父Id", position = 2)
    private String pid;

    @ApiModelProperty(notes = "描叙", position = 3)
    private String desc;

    @ApiModelProperty(notes = "排序", position = 4)
    private Integer sort;

    @ApiModelProperty(notes = "禁用(0-否 1-是)", position = 5)
    private Integer disable;

    private List<CpCategoryVO> cpCategoryVOList;

}

