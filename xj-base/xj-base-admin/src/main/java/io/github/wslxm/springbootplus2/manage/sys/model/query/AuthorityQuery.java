package io.github.wslxm.springbootplus2.manage.sys.model.query;

import io.github.wslxm.springbootplus2.core.base.model.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;


/**
 * 权限接口表
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 *
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-08-22 13:25:02
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "AuthorityQuery 对象", description = "权限接口表")
public class AuthorityQuery extends BaseQuery {

    private static final long serialVersionUID = 0L;

    @ApiModelProperty(notes = "查询参数--父Id(方法与类/层级关系展示)", position = 1)
    private String pid;

    @ApiModelProperty(notes = "查询参数--访问端(字典code, 如 0-管理端 1-用户端)", position = 2)
    private Integer type;

    @ApiModelProperty(notes = "查询参数--查询状态(字典code, 如 0-无权限 1-需登录 2-需登录+授权)", position = 2)
    private Integer state;

    @ApiModelProperty(notes = "查询参数--禁用状态(字典code, 如 0-启用 1-禁用)", position = 2)
    private Integer disable;

//    @ApiModelProperty(notes = "权限参数--角色Id,查询指定角色拥有的权限 (isChecked=true 角色没有权限：isChecked=false)", position = 3)
//    private String roleId;

    @ApiModelProperty(notes = "控制参数--是否只查询当前用户登录存在的权限(默认false)", position = 4)
    private Boolean isLoginUser;

    @ApiModelProperty(notes = "控制参数--是否返回Tree结构数据(true=Tree结构数据  false=返回list结构数据(默认))", position = 5)
    private Boolean isTree;

    @ApiModelProperty(notes = "指定字段正序查询 asc (如asc和desc 同时存在,则优先使用 asc)", position = -1)
    private String asc;

    @ApiModelProperty(notes = "指定字段倒序查询 desc ", position = -1)
    private String desc;

}
