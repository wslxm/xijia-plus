package io.github.wslxm.springbootplus2.manage.sys.model.vo;


import io.github.wslxm.springbootplus2.core.base.model.BaseVo;


import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
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
public class SysUserVO extends BaseVo {

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
     * 地址
     */
    private String address;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 性别（1男，0女）
     */
    private Integer gender;
    /**
     * 禁用（0-否，1-是）
     */
    private Integer disable;
    /**
     * 注册时间
     */
    private LocalDateTime regTime;
    /**
     * 最后登录时间
     */
    private LocalDateTime endTime;
    /**
     * 公司/部门Id
     */
    private String depIds;
    /**
     * 职位（字典code）
     */
    private Integer position;
    /**
     * 微信OpenId
     */
    private Integer wxOpenId;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 用户角色ids, id 查询存在
     */
    private List<String> roleIds;
    /**
     * 用户角色信息,id 查询存在/列表查询都返回(只返回id/name)
     */
    private List<RoleVO> roles;
    /**
     * 公司/部门信息,存在下级关联数据
     */
    private SysUserDepVO dep;
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
