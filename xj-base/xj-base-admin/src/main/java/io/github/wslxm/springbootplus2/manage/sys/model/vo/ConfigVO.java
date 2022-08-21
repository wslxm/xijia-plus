package io.github.wslxm.springbootplus2.manage.sys.model.vo;

import io.github.wslxm.springbootplus2.core.base.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 系统全局数据信息配置表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-08-31 18:31:44
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "ConfigVO 对象", description = "系统全局数据信息配置表")
public class ConfigVO extends BaseVo {

    private static final long serialVersionUID = 0L;
    
    @ApiModelProperty(notes = "配置code|搜索值(不能重复)" ,position = 0)
    private String code;

    @ApiModelProperty(notes = "配置名称" ,position = 1)
    private String name;

    @ApiModelProperty(notes = "类型(0-文本 1-图片)" ,position = 2)
    private Integer type;

    @ApiModelProperty(notes = "配置内容" ,position = 2)
    private String content;

    @ApiModelProperty(notes = "排序" ,position = 3)
    private Integer sort;

    @ApiModelProperty(notes = "描述", position = 3)
    private String desc;

    @ApiModelProperty(value = "扩展字段 1", position = 4)
    private String ext1;

    @ApiModelProperty(value = "扩展字段 2", position = 5)
    private String ext2;

    @ApiModelProperty(value = "扩展字段 3", position = 6)
    private String ext3;

}

