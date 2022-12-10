package io.github.wslxm.springbootplus2.manage.sys.model.dto;

import io.github.wslxm.springbootplus2.core.base.model.Convert;
import io.github.wslxm.springbootplus2.core.utils.validated.RegUtil;


import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;


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
public class AuthorityDTO extends Convert {

    private static final long serialVersionUID = 0L;

    /**
     * 权限类Id(方法与类/层级关系展示)"
     */
    @Length(min = 0, max = 32, message = "权限类Id 必须小于32位")
    private String pid;

    /**
     * 请求方式(GET/POST/PUT/DELETE)
     */
    @Length(min = 0, max = 32, message = "请求方式 必须小于32位")
    private String method;

    /**
     * 权限url
     */
    @Length(min = 0, max = 128, message = "权限url 必须小于128位")
    @Pattern(regexp = RegUtil.URL, message = RegUtil.URL_MSG)
    private String url;

    /**
     * 权限备注信息
     */
    @Length(min = 0, max = 128, message = "权限备注信息 必须小于128位")
    private String desc;

    /**
     * 禁用(0-否 1-是)
     */
    @Range(min = 0, max = 9L, message = "禁用 必须小于9")
    private Integer disable;

    /**
     * 终端(字典code, 如 0-管理端 1-用户端)
     */
    @Range(min = 0, max = 9L, message = "终端 必须小于9")
    private Integer type;

    /**
     * 授权状态(字典code  0-无需登录 1-需登录 2-需登录+授权)
     */
    @Range(min = 0, max = 9L, message = "授权状态 必须小于9")
    private Integer state;

    /**
     * 是否需要验签
     */
    private Boolean isSign;

}
