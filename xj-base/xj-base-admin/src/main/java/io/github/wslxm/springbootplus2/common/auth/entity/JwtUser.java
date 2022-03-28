package io.github.wslxm.springbootplus2.common.auth.entity;

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
 * @version 1.0.1
 */
@Data
@ToString
public class JwtUser implements Serializable {

    private static final long serialVersionUID = -5989040987347929030L;
    /**
     * 登录类型 ->  默认管理端：JwtUtil.userType[0] 默认用户端：JwtUtil.userType[1]  | 更多端查看JwtUtil.userType
     */
    private Integer type;

    /**
     * 用户Id | 对应指定类型的用户表id
     */
    private String userId;

    /**
     * 终端(后台分多端时-登录传递,对应字典code)
     */
    private Integer terminal;

    /**
     * 真实姓名/昵称
     */
    private String fullName;
    /**
     * jwt-token有效期  (单位-分)
     */
    private Integer expiration;

    //================== 管理端专属字段 ==================

    /**
     * 用户权限(用户可以访问那些url,如果在使用中权限数据被修改，需要重新登录)
     * (已移动至缓存处理)
     */
    // private List<String> authList;

}
