package com.ws.ldy.config.auth.util;

import com.ws.ldy.common.utils.JsonUtil;
import com.ws.ldy.config.auth.entity.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.List;

/***
 *   jwt 工具类
 * @author 王松
 * @mail 1720696548@qq.com
 * @date 2020/7/5 0005 19:13
 */
@SuppressWarnings("ALL")
public class JwtUtil {

    private static final String SUBJECT = "xijia";         // 主题
    private static final String APPSECRET_KEY = "xijia";   // 加密key（黑客没有该值无法篡改token内容）
    private static final String AUTH_CLAIMS = "auth";      // 用户权限key
    private static final String AUTH_USER = "user";        // 用户信息key
    /**
     * jwt的token有效期
     *
     * 1分钟  [ 1000L * 60 * 1 ]
     * 5分钟  [ 1000L * 60 * 5 ]
     * 1小时  [ 1000L * 60 * 60 ]
     * 24小时 [ 1000L * 60 * 60 * 24]
     */
    private static final long EXPIRITION = 1000L * 60 * 60;

    /**
     *   生成管理端的 token
     *
     * @param user 用户信息
     * @param auth 用户权限
     * @return java.lang.String
     * @date 2020/7/6 0006 9:26
     */
    public static String createToken(JwtUser jwtUser) {
        // 获取权限数据,单独出来,前端可解析出来用于按钮控制
        List<String> authList = jwtUser.getAuthList();
        // 清除权限数据
        jwtUser.setAuthList(null);
        // 生成jwt
        String token = Jwts
                .builder()
                // 主题
                .setSubject(SUBJECT)
                // 添加jwt自定义值  // TODO 用户信息加密
                .claim(AUTH_CLAIMS, authList)
                .claim(AUTH_USER, JsonUtil.toJSONStringNoNull(jwtUser))
                .setIssuedAt(new Date())
                // 过期时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRITION))
                // 加密方式,加密key
                .signWith(SignatureAlgorithm.HS256, APPSECRET_KEY).compact();
        return token;
    }

    /**
     * 获取用户信息（jwtUser的type 区分登录类型--生成jwt时指定）
     *
     * @param token
     * @return
     */
    public static JwtUser getJwtUser(String token) {
        // 获取token内信息
        Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
        // user 信息
        JwtUser jwtUser = JsonUtil.parseEntity(claims.get(AUTH_USER).toString(), JwtUser.class);
        // auth 权限
        Object obj = claims.get(AUTH_CLAIMS);
        List list = obj == null ? null : JsonUtil.parseList(JsonUtil.toJSONString(obj));
        jwtUser.setAuthList(list);
        return jwtUser;
    }

    /**
     * 是否过期
     *
     * @param token
     * @return
     */
    public static boolean isExpiration(String token) {
        Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
        System.out.println("过期时间: " + claims.getExpiration());
        return claims.getExpiration().before(new Date());
    }
}