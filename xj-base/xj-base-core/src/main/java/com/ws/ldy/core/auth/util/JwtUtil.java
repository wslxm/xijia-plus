package com.ws.ldy.core.auth.util;

import com.ws.ldy.core.auth.entity.JwtUser;
import com.ws.ldy.core.result.R;
import com.ws.ldy.core.result.RType;
import com.ws.ldy.core.utils.json.JsonUtil;
import com.ws.ldy.core.config.error.ErrorException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/***
 *   jwt 工具类
 *   <P>
 *      使用Deflater 压缩数据后再放到 jwt存储数据，以保证不出现 header 大小问题(不能超过8kb) max-http-header-size
 *      jwt工具包会把最后的数据进行base64编码返回
 *   </P>
 * @author 王松
 * @mail 1720696548@qq.com
 * @date 2020/7/5 0005 19:13
 */
@SuppressWarnings("all")
@Slf4j
public class JwtUtil {

    private static final String SUBJECT = "xijia";              // 主题
    private static final String APPSECRET_KEY = "xijia-!@#$";   // 加密key（黑客没有该值无法篡改token内容）
    //private static final String AUTH_CLAIMS = "auth";         // 用户权限key
    private static final String AUTH_USER = "user";             // 用户信息key


    // TOKEN KEY值
    public static String TOKEN = "TOKEN";
    // TOKEN 有效期 key值, 刷新 token时动态刷新
    public static String EXPIRED_TIME = "EXPIRED_TIME";
    // 登录类型 0=管理端 | 1=用户端 | 2-扩展端1 | 3-扩展端2 | 4-扩展端3
    public static final Integer[] userType = {0, 1, 2, 3, 4};


    /**
     * 生成管理端的 token
     * @param jwtUser 用户信息
     * @param refreshTime  刷新时间(分),相当于 token的有效期
     * @param response
     * @return java.lang.String
     * @date 2020/7/6 0006 9:26
     */
    public static String createToken(JwtUser jwtUser, HttpServletResponse response) {
        // 压缩数据
        String deflaterJwtUser = DeflaterUtils.zipString(JsonUtil.toJSONStringNoNull(jwtUser));
        // 生成jwt
        String jwtToken = Jwts
                .builder()
                // 主题
                .setSubject(SUBJECT)
                // 添加jwt自定义参数
                .claim(AUTH_USER, deflaterJwtUser)
                // 创建token 时间
                .setIssuedAt(new Date())
                // tokne 真实有效期,在真实有效期内, 如果 jwt-token过期了, 将自动延长有效期设置的总有效期时长, 如果在tokne 真实有效期内未使用过,则token过期
                .claim(EXPIRED_TIME, System.currentTimeMillis() + (1000L * 60 * (jwtUser.getExpiration())))
                // jwt-token 过期时间, jwt-token 如果过期了, 更新jwt-token参数，jwt-token参数有效期为(真实有效期/10)
                .setExpiration(new Date(System.currentTimeMillis() + (1000L * 60 * (jwtUser.getExpiration() / 10))))
                // 加密方式,加密key
                .signWith(SignatureAlgorithm.HS256, APPSECRET_KEY).compact();
        // 放入Header
        response.setHeader(TOKEN, jwtToken);
        return jwtToken;
    }


    /**
     *
     * 获取用户信息（自动区分登录类型--生成jwt时指定, jwtUser的type ）
     *  <P>
     *     此方法用于在项目中的任意地方获取用户信息，如果在登录授权验证中未过期，但在项目使用时过期, 会在这里抛出自定义异常，程序中无需判断
     *  </P>
     * @param token
     * @return
     */
    public static JwtUser getJwtUser(HttpServletRequest request) {
        R<JwtUser> jwtUser2 = getJwtUserR(request, null);
        if (!jwtUser2.getCode().equals(RType.SYS_SUCCESS.getValue())) {
            throw new ErrorException(jwtUser2.getCode(), jwtUser2.getMsg());
        }
        return jwtUser2.getData();
    }


    /**
     * 获取登录信息，如过 token无效过期等，会进入对应的异常信息中返回
     * <p>
     *     此方法用于权限验证, aop中，日志中获取用户信息, 注意返回的 R，如果出现异常,过期等信息不会直接抛出, 将返回到R 中
     * </p>
     * @param request
     * @param response
     * @return
     */
    public static R<JwtUser> getJwtUserR(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader(TOKEN);
        try {
            if (token == null || token == "") {
                return R.error(RType.AUTHORITY_NO_TOKEN);
            }
            Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
            return R.success(getClaimsJwtUser(claims));
        } catch (ExpiredJwtException ex) {
            /**
             * jwt-token (expiration) 过期处理方法(1-超过总有效期未登录过,token过期 2-在总有效期内,刷新jwt-tokne 延长总有效期--需前端更新token)
             */
            Claims claims = ex.getClaims();
            JwtUser jwtUser = getClaimsJwtUser(claims);
            // 只获取用户信息，直接返回
            if (response == null) {
                return R.success(jwtUser);
            }
            // 如果不是只获取用户信息, 判断token是否在真实有效期内,超过了返回token过期提示
            long expiredTime = Long.parseLong(claims.get(EXPIRED_TIME).toString());
            if (System.currentTimeMillis() > expiredTime) {
                return R.error(RType.AUTHORITY_LOGIN_EXPIRED);
            }
            // token在真实有效期内, 刷新token
            createToken(jwtUser, response);
            log.info("token 已刷新");
            return R.success(jwtUser);
        } catch (SignatureException ex) {
            /**
             * JWT签名与本地计算签名不匹配
             */
            return R.error(RType.AUTHORITY_JWT_SIGN_ERROR);
        } catch (Exception e) {
            /**
             * JWT解析错误
             */
            return R.error(RType.AUTHORITY_JWT_PARSING_ERROR);
        }
    }


    /**
     * 获取用户信息的具体方法
     * @param claims
     * @return
     */
    private static JwtUser getClaimsJwtUser(Claims claims) {
        // user 信息
        String deflaterJwtUser = claims.get(AUTH_USER).toString();
        JwtUser jwtUser = JsonUtil.parseEntity(DeflaterUtils.unzipString(deflaterJwtUser), JwtUser.class);
        return jwtUser;
    }
}