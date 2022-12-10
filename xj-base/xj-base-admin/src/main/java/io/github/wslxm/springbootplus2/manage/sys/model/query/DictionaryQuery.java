package io.github.wslxm.springbootplus2.manage.sys.model.query;

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
public class DictionaryQuery extends Convert {

    private static final long serialVersionUID = 0L;

    /**
     * 父级code,没有获取所有, 传递了只查询指定code下数据
     */
    private String code;

    /**
     * 父级code 查询是否获取所有层级数据 true-是(所有下级-默认) false-否(下一级)
     */
    private Boolean isNextAll;

    /**
     * 是否查询禁用数据 true-查询*默认  false-不查询
     */
    private Boolean isDisable;

    /**
     * 是否需要最后一级数据 true-需要*默认   false-不需要
     */
    private Boolean isBottomLayer;

    /**
     * isTree 是否返回树结构数据  tree-是*默认  false-否(返回过滤后的 list列表)
     */
    private Boolean isTree;

}
