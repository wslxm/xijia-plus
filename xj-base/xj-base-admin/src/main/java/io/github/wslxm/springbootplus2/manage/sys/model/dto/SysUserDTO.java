package io.github.wslxm.springbootplus2.manage.sys.model.dto;


import io.github.wslxm.springbootplus2.core.base.model.Convert;


import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 用户
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/14 21:06
 */
@Data
@ToString(callSuper = true)
public class SysUserDTO extends Convert {

    private static final long serialVersionUID = 4934650100711613453L;

    /**
     * 头像
     */
    private String headPic;
    /**
     * 账号/用户名
     */
    private String username;
    /**
     * 手机号/第二账号
     */
    private String phone;
    /**
     * 昵称
     */
    private String fullName;
    /**
     * 密码(编辑无法进行修改)
     */
    private String password;
    /**
     * 地址
     */
    private String address;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 性别（1-男，0-女）
     */
    private Integer gender;
    /**
     * 禁用（0-否，1-是）
     */
    private Integer disable;
    /**
     * 职位（字典code）
     */
    private Integer position;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 角色Id, 必须存在一条数据才能触发重分配,没有数据时数据无变化
     */
    private List<String> roleIds;
    /**
     * 公司/部门 ids
     */
    private String depIds;
    /**
     * 扩展字段 1
     */
    private String ext1;
    /**
     * 扩展字段 2
     */
    private String ext2;
    /**
     * 扩展字段 3
     */
    private String ext3;
}
