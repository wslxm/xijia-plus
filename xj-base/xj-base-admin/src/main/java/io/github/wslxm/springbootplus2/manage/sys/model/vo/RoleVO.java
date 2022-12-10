package io.github.wslxm.springbootplus2.manage.sys.model.vo;

import io.github.wslxm.springbootplus2.core.base.model.BaseVo;


import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 角色
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/14 20:48
 */
@Data
@ToString(callSuper = true)
public class RoleVO extends BaseVo {

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 角色名
     */
    private String name;
    /**
     * 描叙
     */
    private String desc;
    /**
     * 禁用（0-否，1-是）
     */
    private Integer disable;
    /**
     * 查询code
     */
    private String code;
    /**
     * 角色分配, 当前用户拥有角色为true
     */
    private Boolean isChecked;

}

