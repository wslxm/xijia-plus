package io.github.wslxm.springbootplus2.manage.sys.model.query;

import io.github.wslxm.springbootplus2.core.base.model.BaseQuery;
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
@ApiModel(value = "BannerQuery 对象", description = "banner表")
public class BannerQuery extends BaseQuery {

    private static final long serialVersionUID = 0L;

    @ApiModelProperty(value = "banner标题" ,position = 0)
    private String name;

    @ApiModelProperty(value = "位置(字典code)" ,position = 0)
    private Integer position;

}

