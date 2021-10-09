package io.github.wslxm.springbootplus2.manage.xj.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.wslxm.springbootplus2.core.base.model.BaseEntity;
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
@TableName("t_xj_admin_banner")
@ApiModel(value = "XjAdminBanner 对象", description = "banner表")
public class XjAdminBanner extends BaseEntity {

    private static final long serialVersionUID = 0L;

    @ApiModelProperty(notes = "banner位置(字典code) " ,position = 0)
    @TableField(value = "position")
    private Integer position;

    @ApiModelProperty(notes = "banner标题" ,position = 0)
    @TableField(value = "`name`")
    private String name;

    @ApiModelProperty(notes = "banner描叙" ,position = 1)
    @TableField(value = "`desc`")
    private String desc;

    @ApiModelProperty(notes = "banner图片" ,position = 2)
    @TableField(value = "img_url")
    private String imgUrl;

    @ApiModelProperty(notes = "banner排序" ,position = 3)
    @TableField(value = "`sort`")
    private Integer sort;

    @ApiModelProperty(notes = "banner禁用(0-启用 1-禁用)" ,position = 4)
    @TableField(value = "`disable`")
    private Integer disable;

    @ApiModelProperty(notes = "是否跳转(0-无  1-内部链接 2-外部链接)" ,position = 5)
    @TableField(value = "is_skip")
    private Integer isSkip;

    @ApiModelProperty(notes = "跳转地址url(地址直接添加或字典表配置)" ,position = 6)
    @TableField(value = "skip_url")
    private String skipUrl;

}

