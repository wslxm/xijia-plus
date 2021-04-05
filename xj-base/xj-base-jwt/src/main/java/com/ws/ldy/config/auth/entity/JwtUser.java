package com.ws.ldy.config.auth.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
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
public class JwtUser implements Serializable {

    private static final long serialVersionUID = -5989040987347929030L;

    //================== 通用字段列==================
    /**
     * 登录类型 ->   管理端：JwtUtil.userType[0]   用户端： JwtUtil.userType[0]  | 更多端自行指定和编辑JwtUtil代码
     */
    private Integer type;
    /**
     * 用户Id | 对应指定类型的用户表id
     */
    private String userId;
    /**
     * 真实姓名/昵称
     */
    private String fullName;
    /**
     * 第一账号 --> 账号/用户名
     */
    private String username;
    /**
     * 第二账号 --> 手机号
     */
    private String phone;
    /**
     * 头像url
     */
    private String head;


    //================== 管理端专属字段 ==================
    /**
     * 用户权限(用户可以访问那些url,如果在使用中权限数据被修改，需要重新登录)
     */
    private List<String> authList;

    /**
     * token 刷新时间(单位分) -> 相当于设置生成的token 总有效期
     * 如果expiration 过期了, 该值将会刷新到最新的时间 + refreshTime (分)
     * 如果在 refreshTime 分没登录过, 将返回登录过期给用户
     */
    private Integer refreshTime;

    /**
     * 单次token有效期 -> 相当多久刷新一次 token
     */
    private Integer expiration;
}
