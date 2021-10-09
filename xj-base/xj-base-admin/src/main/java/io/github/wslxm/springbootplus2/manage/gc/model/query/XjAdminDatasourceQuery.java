package io.github.wslxm.springbootplus2.manage.gc.model.query;

import io.github.wslxm.springbootplus2.core.base.model.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 代码生成数据源维护表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-11-04 20:11:08
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "XjAdminDatasourceDTO 对象", description = "代码生成数据源维护表")
public class XjAdminDatasourceQuery extends BaseQuery {

    private static final long serialVersionUID = 0L;
    
    @ApiModelProperty(notes = "数据库标题" ,position = 0)
    private String dbTitle;

    @ApiModelProperty(notes = "数据库名" ,position = 1)
    private String dbName;

}

