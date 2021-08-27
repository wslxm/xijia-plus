package com.ws.ldy.manage.admin.model.query;

import com.ws.ldy.core.base.model.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 *   角色
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/14 20:48
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "AdminRoleQuery", description = "角色")
public class AdminRoleQuery extends BaseQuery {


    private static final long serialVersionUID = 5684918982089765949L;

    @ApiModelProperty(value = "角色名")
    private String name;

    @ApiModelProperty(value = "终端")
    private Integer terminal;

}

