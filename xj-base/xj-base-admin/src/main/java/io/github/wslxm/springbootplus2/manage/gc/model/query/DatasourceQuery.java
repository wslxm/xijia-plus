package io.github.wslxm.springbootplus2.manage.gc.model.query;

import io.github.wslxm.springbootplus2.core.base.model.BaseQuery;


import lombok.Data;
import lombok.ToString;

/**
 * 代码生成数据源维护表
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 *
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-11-04 20:11:08
 */
@Data
@ToString(callSuper = true)
public class DatasourceQuery extends BaseQuery {

    private static final long serialVersionUID = 0L;

    /**
     * 数据库标题"
     */
    private String dbTitle;

    /**
     * 数据库名"
     */
    private String dbName;

}

