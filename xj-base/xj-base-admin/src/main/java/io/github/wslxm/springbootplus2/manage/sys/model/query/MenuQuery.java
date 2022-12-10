package io.github.wslxm.springbootplus2.manage.sys.model.query;

import io.github.wslxm.springbootplus2.core.base.model.Convert;
import lombok.Data;
import lombok.ToString;

/**
 * 菜单
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/14 20:49
 */
@Data
@ToString(callSuper = true)
public class MenuQuery extends Convert {


    /**
     * 父id--非必传,没有获取所有,传递了只查询指定层级的下一级数据
     */
    private String pid;
    /**
     * 父id--非必传,查询是否获取所有层级数据 true-是(所有下级-默认) false-否(下一级)
     */
    private Boolean isNextAll;
    /**
     * 角色id--非必传, 不传返回的isChecked = false, 传了根据权限标记true/false
     */
    private String roleId;
    /**
     * 禁用--非必传,（0-否，1-是)"
     */
    private Integer disable;
    /**
     * 查询级别(变根父级时查询列表)--非必传,查询参数当前选中数据的级别-1 (1-目录 2-菜单 3-页面)
     */
    private Integer root;
    /**
     * 是否只返回当前登录用户存在的菜单--非必传,  true是 false-否(默认))
     */
    private Boolean isLoginUser;
}
