package io.github.wslxm.springbootplus2.manage.sys.model.vo;

import io.github.wslxm.springbootplus2.core.base.model.BaseVo;


import lombok.Data;
import lombok.ToString;

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
public class BannerVO extends BaseVo {

    private static final long serialVersionUID = 0L;

    /**
     * banner位置(字典code) "
     */
    private Integer position;

    /**
     * banner标题"
     */
    private String name;

    /**
     * banner描叙"
     */
    private String desc;

    /**
     * banner图片"
     */
    private String imgUrl;

    /**
     * banner排序"
     */
    private Integer sort;

    /**
     * banner禁用(0-启用 1-禁用)"
     */
    private Integer disable;

    /**
     * 是否跳转(0-无  1-内部链接 2-外部链接)"
     */
    private Integer isSkip;

    /**
     * 跳转地址url(地址直接添加或字典表配置)"
     */
    private String skipUrl;

}

