package io.github.wslxm.springbootplus2.manage.sys.model.vo;

import io.github.wslxm.springbootplus2.core.base.model.BaseVo;


import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 代码生成器自动生成，请自定义描叙
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Mon Nov 25 08:02:49 CST 2019
 */
@Data
@ToString(callSuper = true)
public class AuthorityVO extends BaseVo {

    private static final long serialVersionUID = 0L;

    /**
     * 权限类
     */
    private String pid;
    /**
     * 权限url
     */
    private String url;
    /**
     * 权限描叙
     */
    private String desc;
    /**
     * 请求方式
     */
    private String method;
    /**
     * 禁用（0-否，1-是）
     */
    private Integer disable;
    /**
     * 是否选中-是否选中（是否有权限，前台复选框默认选中需要值）
     */
    private Boolean isChecked;
    /**
     * 终端(字典code, 如 0-管理端 1-用户端)
     */
    private Integer type;
    /**
     * 授权状态(字典code  0-无需登录 1-需登录 2-需登录+授权)
     */
    private Integer state;
    /**
     * 是否需要验签
     */
    private Boolean isSign;
    /**
     * 下级数据
     */
    private List<AuthorityVO> authoritys;
}

