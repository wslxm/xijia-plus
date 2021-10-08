package com.github.wslxm.springbootplus2.manage.xj.model.query;

import com.github.wslxm.springbootplus2.core.base.model.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 黑名单
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-11-27 22:44:49
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "XjAdminBlacklistQuery 对象", description = "黑名单")
public class XjAdminBlacklistQuery extends BaseQuery {

    private static final long serialVersionUID = 0L;
    
    @ApiModelProperty(value = "1-白名单(* 表示允许除黑名单外的所有ip请求, 否则只允许白名单中的ip请求) 2-黑名单(禁止ip访问)" ,position = 0)
    private Integer type;

    @ApiModelProperty(value = "禁用(0-启用 1-禁用)" ,position = 3)
    private Integer disable;

}

