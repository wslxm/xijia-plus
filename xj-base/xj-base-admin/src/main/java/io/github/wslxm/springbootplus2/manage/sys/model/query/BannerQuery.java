package io.github.wslxm.springbootplus2.manage.sys.model.query;

import io.github.wslxm.springbootplus2.core.base.model.BaseQuery;


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
public class BannerQuery extends BaseQuery {

    private static final long serialVersionUID = 0L;

    /**
     * banner标题
     */
    private String name;

    /**
     * 位置(字典code)
     */
    private Integer position;

}

