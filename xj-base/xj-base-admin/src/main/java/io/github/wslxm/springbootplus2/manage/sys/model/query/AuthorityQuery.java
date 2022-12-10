package io.github.wslxm.springbootplus2.manage.sys.model.query;

import io.github.wslxm.springbootplus2.core.base.model.BaseQuery;
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
public class AuthorityQuery extends BaseQuery {

    private static final long serialVersionUID = 0L;

    /**
     * 查询参数--父Id(方法与类/层级关系展示)
     */
    private String pid;

    /**
     * 查询参数--访问端(字典code, 如 0-管理端 1-用户端)
     */
    private Integer type;

    /**
     * 查询参数--查询状态(字典code, 如 0-无权限 1-需登录 2-需登录+授权)
     */
    private Integer state;

    /**
     * 查询参数--禁用状态(字典code, 如 0-启用 1-禁用)
     */
    private Integer disable;

    /**
     * 控制参数--是否只查询当前用户登录存在的权限(默认false)
     */
    private Boolean isLoginUser;

    /**
     * 控制参数--是否返回Tree结构数据(true=Tree结构数据  false=返回list结构数据(默认))
     */
    private Boolean isTree;

    /**
     * 指定字段正序查询 asc (如asc和desc 同时存在,则优先使用 asc)
     */
    private String asc;

    /**
     * 指定字段倒序查询 desc
     */
    private String desc;

}
