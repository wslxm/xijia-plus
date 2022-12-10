package io.github.wslxm.springbootplus2.manage.sys.model.dto;

import io.github.wslxm.springbootplus2.core.base.model.Convert;


import lombok.Data;
import lombok.ToString;

/**
 * 字典表
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Sun Nov 24 11:23:12 CST 2019
 */
@Data
@ToString(callSuper = true)
public class DictionaryDTO extends Convert {

    private static final long serialVersionUID = 0L;

    /**
     * 字典类型
     */
    private String code;

    /**
     * 字典名称
     */
    private String name;

    /**
     * 父Id
     */
    private String pid;

    /**
     * 描叙
     */
    private String desc;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 禁用（0-否，1-是）
     */
    private Integer disable;

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

