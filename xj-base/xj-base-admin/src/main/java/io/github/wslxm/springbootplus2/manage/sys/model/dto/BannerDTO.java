package io.github.wslxm.springbootplus2.manage.sys.model.dto;

import io.github.wslxm.springbootplus2.core.base.model.Convert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

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
@ApiModel(value = "BannerDTO 对象", description = "banner表")
public class BannerDTO extends Convert {

    private static final long serialVersionUID = 0L;

    @ApiModelProperty(notes = "banner位置(字典code) " ,position = 0)
    @Range(min=0, max=99,message = "banner位置  必须小于99")
    private Integer position;

    @ApiModelProperty(notes = "banner标题" ,position = 0)
    @Length(min=1, max=128,message = "banner标题 必须小于128位")
    private String name;

    @ApiModelProperty(notes = "banner描叙" ,position = 1)
    private String desc;

    @ApiModelProperty(notes = "banner图片" ,position = 2)
    @Length(min=1, max=256,message = "banner图片 必须小于256位")
    private String imgUrl;

    @ApiModelProperty(notes = "banner排序" ,position = 3)
    @Range(min=0, max=2147483647L,message = "banner排序 必须小于2147483647")
    private Integer sort;

    @ApiModelProperty(notes = "banner禁用(0-启用 1-禁用)" ,position = 4)
    @Range(min=0, max=9L,message = "banner禁用 必须小于9")
    private Integer disable;

    @ApiModelProperty(notes = "是否跳转(0-无  1-内部链接 2-外部链接)" ,position = 5)
    @Range(min=0, max=9L,message = "是否跳转 必须小于9")
    private Integer isSkip;

    @ApiModelProperty(notes = "跳转地址url(地址直接添加或字典表配置)" ,position = 6)
    private String skipUrl;

}

