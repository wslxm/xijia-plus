package io.github.wslxm.springbootplus2.manage.sys.model.dto;

import io.github.wslxm.springbootplus2.core.base.model.Convert;


import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

/**
 * banner表
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 *
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-08-23 23:14:01
 */
@Data
@ToString(callSuper = true)
public class BannerDTO extends Convert {

    private static final long serialVersionUID = 0L;

    /**
     * banner位置(字典code) "
     */
    @Range(min = 0, max = 99, message = "banner位置  必须小于99")
    private Integer position;

    /**
     * banner标题"
     */
    @Length(min = 1, max = 128, message = "banner标题 必须小于128位")
    private String name;

    /**
     * banner描叙"
     */
    private String desc;

    /**
     * banner图片"
     */
    @Length(min = 1, max = 256, message = "banner图片 必须小于256位")
    private String imgUrl;

    /**
     * banner排序"
     */
    @Range(min = 0, max = 2147483647L, message = "banner排序 必须小于2147483647")
    private Integer sort;

    /**
     * banner禁用(0-启用 1-禁用)"
     */
    @Range(min = 0, max = 9L, message = "banner禁用 必须小于9")
    private Integer disable;

    /**
     * 是否跳转(0-无  1-内部链接 2-外部链接)"
     */
    @Range(min = 0, max = 9L, message = "是否跳转 必须小于9")
    private Integer isSkip;

    /**
     * 跳转地址url(地址直接添加或字典表配置)"
     */
    private String skipUrl;

}

