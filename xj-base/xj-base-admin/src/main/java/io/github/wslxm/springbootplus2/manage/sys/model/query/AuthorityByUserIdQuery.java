package io.github.wslxm.springbootplus2.manage.sys.model.query;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 根据用户查询权限信息
 *
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2022/8/18 9:25
 */
@Data
@ToString(callSuper = true)
public class AuthorityByUserIdQuery implements Serializable {


    /**
     * 用户id  (只查询用户当前存在权限)
     */
    private String userId;
    /**
     * 只查询指定父id下的数据
     */
    private String pid;
    /**
     * 访问端(字典code, 如 0-管理端 1-用户端)
     */
    private Integer type;
    /**
     * 查询状态(字典code, 如 0-无权限 1-需登录 2-需登录+授权
     */
    private Integer state;
    /**
     * 禁用状态(字典code, 如 0-启用 1-禁用)
     */
    private Integer disable;
    /**
     * 正序排序 (默认排序key)
     */
    private String asc;
    /**
     * 倒序排序
     */
    private String desc;

}
