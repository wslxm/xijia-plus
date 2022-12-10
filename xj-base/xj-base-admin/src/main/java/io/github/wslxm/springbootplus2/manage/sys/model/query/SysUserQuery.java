package io.github.wslxm.springbootplus2.manage.sys.model.query;


import io.github.wslxm.springbootplus2.core.base.model.BaseQuery;


import lombok.Data;
import lombok.ToString;

/**
 * 用户
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/14 21:06
 */
@Data
@ToString(callSuper = true)
public class SysUserQuery extends BaseQuery {

    private static final long serialVersionUID = 4934650100711613453L;

    /**
     * id
     */
    private String id;
    /**
     * 账号/用户名
     */
    private String username;
    /**
     * 电话
     */
    private String phone;
    /**
     * 昵称
     */
    private String fullName;
    /**
     * 禁用（0-否，1-是）
     */
    private Integer disable;
    /**
     * 部门ids
     */
    private String depIds;
    /**
     * 职位（字典code）
     */
    private Integer position;
    /**
     * 是否只查询当前登录人创建的用户,默认false", notes = "需要多层级的结构权限,在角色列表查询，和用户分配时使用传递true
     */
    private Boolean isLoginUser;
    /**
     * 注册开始时间/结束时间，逗号分割
     */
    private String regTime;
}
