package io.github.wslxm.springbootplus2.manage.admin.model.query;

import io.github.wslxm.springbootplus2.core.base.model.Convert;
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
public class AdminMenuQuery extends Convert {

    @ApiModelProperty(value = "终端--非必传,(不传查所有)", position = 1)
    private Integer terminal;

    @ApiModelProperty(value = "父id--非必传,没有获取所有", position = 2)
    private String pid;

    @ApiModelProperty(value = "角色id--非必传, 不传返回的isChecked = false, 传了根据权限标记true/false", position = 3)
    private String roleId;

    @ApiModelProperty(value = "禁用--非必传,（0-否，1-是)", position = 4)
    private Integer disable;

    @ApiModelProperty(value = "是否返回Tree数据,--非必传,  true=-返回tree false-返回list(默认)", position = 5)
    private Boolean isTree ;

    @ApiModelProperty(value = "是否需要最后一级的数据--非必传,  true需要(默认) false-不需要", position = 6)
    private Boolean isBottomLayer;

    @ApiModelProperty(value = "查询级别(变根父级时查询列表)--非必传,查询参数当前选中数据的级别-1 (1-目录 2-菜单 3-页面)", position = 6)
    private Integer root;

    @ApiModelProperty(value = "是否只返回当前用户存在的菜单--非必传,  true是 false-否(默认))", position = 7)
    private Boolean isLoginUser ;
}
