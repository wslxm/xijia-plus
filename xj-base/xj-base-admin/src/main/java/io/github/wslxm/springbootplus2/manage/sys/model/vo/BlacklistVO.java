package io.github.wslxm.springbootplus2.manage.sys.model.vo;

import io.github.wslxm.springbootplus2.core.base.model.BaseVo;


import lombok.Data;
import lombok.ToString;

/**
 * 黑名单
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 *
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-11-27 22:44:49
 */
@Data
@ToString(callSuper = true)
public class BlacklistVO extends BaseVo {

    private static final long serialVersionUID = 0L;

    /**
     * 1-白名单(* 表示允许除黑名单外的所有ip请求, 否则只允许白名单中的ip请求) 2-黑名单(禁止ip访问)
     */
    private Integer type;

    /**
     * 白名单ip/黑名单ip"
     */
    private String ip;

    /**
     * 备注"
     */
    private String desc;

    /**
     * 禁用(0-启用 1-禁用)"
     */
    private Integer disable;

}

