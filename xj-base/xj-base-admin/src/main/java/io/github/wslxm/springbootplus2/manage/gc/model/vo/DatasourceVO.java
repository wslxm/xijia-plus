package io.github.wslxm.springbootplus2.manage.gc.model.vo;

import io.github.wslxm.springbootplus2.core.base.model.BaseVo;


import lombok.Data;
import lombok.ToString;

/**
 * 代码生成数据源维护表
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 *
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-11-04 20:11:08
 */
@Data
@ToString(callSuper = true)
public class DatasourceVO extends BaseVo {

    private static final long serialVersionUID = 0L;

    /**
     * db -标题
     */
    private String dbTitle;

    /**
     * db 库名
     */
    private String dbName;

    /**
     * db 连接地址
     */
    private String dbUrl;

    /**
     * db 账号
     */
    private String dbUsername;

    /**
     * db 密码
     */
    private String dbPassword;

    /**
     * 作者
     */
    private String author;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 描述信息
     */
    private String describe;

    /**
     * 项目名/路径，如：xj-server/xj-test-server
     */
    private String projectName;

    /**
     * 包路径 (如: io.github.wslxm)
     */
    private String packPath;

    /**
     * 根模块 (固定为：modules(管理端), 用户端为：client)
     */
    private String rootModule;

    /**
     * 子模块 (业务分类,如用户管理,订单管理模块拆分，也可以统一一个名称放在一起)
     */
    private String modulesName;

    /**
     * db 表前缀 (生成的类名会过滤掉前缀)
     */
    private String dbTablePrefix;

    /**
     * db 字段前缀 (生成的字段名会过滤掉前缀)
     */
    private String dbFieldPrefix;

    /**
     * 实体类是否使用swagger注释 (false情况下使用doc注释)
     */
    private Boolean entitySwagger;

    /**
     * 是否过滤crud方法- 默认生成 (controller/service/mapper/xml)
     */
    private Boolean filterCrud;

    /**
     * 生成路径(不填默认当前项目跟目录,可指定绝对路径)
     */
    private String fatherPath;

    /**
     * 排除vue字段类型 (字典code值，参考字典生成字段类型，如: 18=富文本 19=md编辑器 )
     */
    private String vueFieldTypes;

    /**
     * 数据库通用字段
     */
    private String baseFields;

    /**
     * 数据库关键字
     */
    private String keywordArray;
}

