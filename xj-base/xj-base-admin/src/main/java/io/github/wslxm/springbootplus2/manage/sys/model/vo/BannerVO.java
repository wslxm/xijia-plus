package io.github.wslxm.springbootplus2.manage.sys.model.vo;

import io.github.wslxm.springbootplus2.core.base.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * banner表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-08-23 23:14:01
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "BannerVO 对象", description = "banner表")
public class BannerVO extends BaseVo {

    private static final long serialVersionUID = 0L;

    @ApiModelProperty(notes = "banner位置(字典code) " ,position = 0)
    private Integer position;

    @ApiModelProperty(notes = "banner标题" ,position = 0)
    private String name;

    @ApiModelProperty(notes = "banner描叙" ,position = 1)
    private String desc;

    @ApiModelProperty(notes = "banner图片" ,position = 2)
    private String imgUrl;

    @ApiModelProperty(notes = "banner排序" ,position = 3)
    private Integer sort;

    @ApiModelProperty(notes = "banner禁用(0-启用 1-禁用)" ,position = 4)
    private Integer disable;

    @ApiModelProperty(notes = "是否跳转(0-无  1-内部链接 2-外部链接)" ,position = 5)
    private Integer isSkip;

    @ApiModelProperty(notes = "跳转地址url(地址直接添加或字典表配置)" ,position = 6)
    private String skipUrl;

}

