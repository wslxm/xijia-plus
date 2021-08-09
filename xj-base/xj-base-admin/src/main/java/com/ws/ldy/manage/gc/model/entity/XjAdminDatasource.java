package com.ws.ldy.manage.gc.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ws.ldy.core.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 代码生成数据源维护表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-11-04 20:11:08
 */
@Data
@ToString(callSuper = true)
@TableName("t_xj_admin_datasource")
@ApiModel(value = "XjAdminDatasource 对象", description = "代码生成数据源维护表")
public class XjAdminDatasource extends BaseEntity {

    private static final long serialVersionUID = 0L;
    
    @ApiModelProperty(notes = "数据库标题" ,position = 0)
    @TableField(value = "db_title")
    private String dbTitle;

    @ApiModelProperty(notes = "数据库名" ,position = 1)
    @TableField(value = "db_name")
    private String dbName;

    @ApiModelProperty(notes = "数据库连接" ,position = 2)
    @TableField(value = "db_url")
    private String dbUrl;

    @ApiModelProperty(notes = "数据库账号" ,position = 3)
    @TableField(value = "db_username")
    private String dbUsername;

    @ApiModelProperty(notes = "数据库密码" ,position = 4)
    @TableField(value = "db_password")
    private String dbPassword;

    @ApiModelProperty(notes = "数据表前缀" ,position = 5)
    @TableField(value = "db_prefix")
    private String dbPrefix;

    @ApiModelProperty(notes = "数据字段前缀" ,position = 5)
    @TableField(value = "db_field_prefix")
    private String  dbFieldPrefix;

    @ApiModelProperty(notes = "数据库通用字段,逗号分隔" ,position = 6)
    @TableField(value = "db_general_field")
    private String dbGeneralField;

    @ApiModelProperty(notes = "包根路径" ,position = 7)
    @TableField(value = "pack_path")
    private String packPath;

    @ApiModelProperty(notes = "父模块名" ,position = 8)
    @TableField(value = "modules")
    private String modules;

    @ApiModelProperty(notes = "子模块名" ,position = 9)
    @TableField(value = "modules_name")
    private String modulesName;

}

