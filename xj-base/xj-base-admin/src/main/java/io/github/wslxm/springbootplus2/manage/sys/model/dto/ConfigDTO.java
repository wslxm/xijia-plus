package io.github.wslxm.springbootplus2.manage.sys.model.dto;

import io.github.wslxm.springbootplus2.core.base.model.Convert;


import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

/**
 * 系统全局数据信息配置表
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 *
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-08-31 18:31:44
 */
@Data
@ToString(callSuper = true)
public class ConfigDTO extends Convert {

    private static final long serialVersionUID = 0L;

    /**
     * 配置code|搜索值(不能重复)"
     */
    @Length(min = 1, max = 32, message = "配置code|搜索值 必须小于32位")
    private String code;

    /**
     * 配置名称
     */
    @Length(min = 1, max = 32, message = "配置名称 必须小于32位")
    private String name;

    /**
     * 类型(0-文本 1-图片)
     */
    private Integer type;

    /**
     * 配置内容
     */
    private String content;

    /**
     * 排序
     */
    @Range(min = 0, max = 2147483647L, message = "排序 必须小于2147483647")
    private Integer sort;

    /**
     * 描述
     */
    private String desc;

    /**
     * 扩展字段 1
     */
    private String ext1;

    /**
     * 扩展字段 2
     */
    private String ext2;

    /**
     * 扩展字段 3
     */
    private String ext3;

}

