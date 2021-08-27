package com.ws.ldy.manage.admin.model.query;

import com.ws.ldy.core.base.model.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 *   菜单
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/14 20:49
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "AdminMenuQuery", description = "菜单")
public class AdminMenuQuery extends BaseQuery {

    @ApiModelProperty(value = "true=需要最后一级的数据  false=不需要最后一级 (默认true)")
    private Boolean isBottomLayer;

    @ApiModelProperty(value = "终端(不传查所有)")
    private Integer terminal;
}
