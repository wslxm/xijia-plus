package io.github.wslxm.springbootplus2.manage.sys.model.query;

import io.github.wslxm.springbootplus2.core.base.model.BaseQuery;


import lombok.Data;
import lombok.ToString;

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
public class ConfigQuery extends BaseQuery {

    private static final long serialVersionUID = 0L;

    /**
     * 配置code|搜索值(不能重复)
     */
    private String code;

    /**
     * 配置名称
     */
    private String name;

    /**
     * 配置类型(字典code)
     */
    private Integer type;


}

