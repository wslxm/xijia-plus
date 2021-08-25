package com.ws.ldy.manage.xj.model.query;

import com.ws.ldy.core.base.model.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

/**
 * 系统全局数据信息配置表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-08-31 18:31:44
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "XjAdminConfigQuery 对象", description = "系统全局数据信息配置表")
public class XjAdminConfigQuery extends BaseQuery {

    private static final long serialVersionUID = 0L;
    
    @ApiModelProperty(value = "配置code|搜索值(不能重复)" ,position = 0)
    private String code;

    @ApiModelProperty(value = "配置名称" ,position = 1)
    private String name;


}

