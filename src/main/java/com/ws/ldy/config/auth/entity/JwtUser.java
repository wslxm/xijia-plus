package com.ws.ldy.config.auth.entity;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 通过jwt 解析处理的用户信息（不区分用户账号端还是管理账号端登录，统一解析到此）
 * <P>
 *     录入jwt的用户信息将可以直接从jwt中获取, 以下字段为初始字段, 可自定义,  type 必传，区分登录账号端，如果有两个用户表, 用于解析用户信息
 * </P>
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/10/28 0028 15:12
 * @version 1.0.0
 */
@Data
@ToString
public class JwtUser {
    //================== 通用字段列==================
    /**
     * 登录类型 -> 对应： Enums.Admin.AuthorityType.
     */
    private Integer type;
    /**
     * 用户Id
     */
    private String userId;
    /**
     * 姓名
     */
    private String fullName;
    /**
     * 账号/用户名
     */
    private String username;
    /**
     * 手机号/第二账号
     */
    private String phone;
    /**
     * 头像url
     */
    private String head;

    //================== 管理端专属字段 ==================
    /**
     * 用户权限
     */
    private List<String> authList;
    /**
     * 用户权限版本号-用于修改权限后直接刷新正在正常使用中的用户权限
     */
    private Integer authVersion;
}
