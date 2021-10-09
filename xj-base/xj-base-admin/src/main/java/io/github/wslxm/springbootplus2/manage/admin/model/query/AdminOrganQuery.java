package io.github.wslxm.springbootplus2.manage.admin.model.query;

import io.github.wslxm.springbootplus2.core.base.model.Convert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 基础表--组织机构
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 * @author  ws
 * @email  1720696548@qq.com
 * @date  2021-09-30 16:10:57
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "AdminOrganQuery 对象", description = "基础表--组织机构")
public class AdminOrganQuery extends Convert {

    private static final long serialVersionUID = -603467428491563017L;

    @ApiModelProperty(value = "父id--非必传,没有获取所有",position = 2)
    private String pid;

    @ApiModelProperty(value = "是否禁用--非必传,（0-否，1-是)",position = 4)
    private Integer disable;

    @ApiModelProperty(value = "是否返回Tree数据,--非必传,  true=-返回tree false-返回list(默认)",position = 5)
    private Boolean isTree = false;

}

