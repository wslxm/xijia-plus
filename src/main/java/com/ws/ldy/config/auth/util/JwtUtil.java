package com.ws.ldy.config.auth.util;

import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.DeflaterUtils;
import com.ws.ldy.common.utils.JsonUtil;
import com.ws.ldy.config.auth.entity.JwtUser;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.enums.Enums;
import com.ws.ldy.modules.sys.admin.service.AdminAuthorityService;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/***
 *   jwt 工具类
 *   <P>
 *      使用Deflater压缩数据后再放到 jwt存储数据，以保证不出现 header大小问题(不能超过8kb) max-http-header-size
 *      jwt工具包会把最后的数据进行base64编码返回
 *   </P>
 * @author 王松
 * @mail 1720696548@qq.com
 * @date 2020/7/5 0005 19:13
 */
@SuppressWarnings("ALL")
@Slf4j
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
        // 权限数据（Deflater压缩）
        String authListZip = null;
        if (jwtUser.getType().equals(Enums.Admin.AuthorityType.AUTHORITY_TYPE_0.getValue())) {
            // 管理端登录权限数据进行压缩
            String authListJsonStr = JsonUtil.toJSONStringNoNull(jwtUser.getAuthList());
            authListZip = DeflaterUtils.zipString(authListJsonStr);
            log.info("authList 压缩后大小：{}  --> {}", authListJsonStr.length(), authListZip.length());
        }
        // 用户数据json, 先清除用户信息中的权限数据
        jwtUser.setAuthList(null);
        String jwtUserJsonStr = JsonUtil.toJSONStringNoNull(jwtUser);
        // 生成jwt
        String token = Jwts
                .builder()
                // 主题
                .setSubject(SUBJECT)
                // 添加jwt自定义值
                .claim(AUTH_CLAIMS, authListZip)
                .claim(AUTH_USER, jwtUserJsonStr)
                .setIssuedAt(new Date())
                // 过期时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRITION))
                // 加密方式,加密key
                .signWith(SignatureAlgorithm.HS256, APPSECRET_KEY).compact();
        return token;
    }


    /**
     * 获取用户信息（自动区分登录类型--生成jwt时指定, jwtUser的type ）
     *  <P>
     *       此方法用于在项目中的任意地方获取用户信息，如果在登录授权验证中未过期， 但在项目使用时过期, 会在这里抛出自定义异常，程序中无需判断
     *  </P>
     * @param token
     * @return
     */
    public static JwtUser getJwtUser(HttpServletRequest request) {
        R<JwtUser> jwtUser2 = getJwtUserR(request);
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
    public static R<JwtUser> getJwtUserR(HttpServletRequest request) {
        String token = request.getHeader(BaseConstant.Sys.TOKEN);
        try {
            if (StringUtils.isNotBlank(token)) {
                // 获取token内信息
                Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
                // user 信息
                String userJson = claims.get(AUTH_USER).toString();
                JwtUser jwtUser = JsonUtil.parseEntity(userJson, JwtUser.class);
                // auth 权限, 先使用Deflater还原成json在转为list数据
                Object objZip = claims.get(AUTH_CLAIMS);
                if (objZip != null) {
                    String objJsonStr = DeflaterUtils.unzipString(objZip.toString());
                    List list = JsonUtil.parseList(objJsonStr);
                    jwtUser.setAuthList(list);
                }
                // 正常就返回,不然就进入异常
                return R.success(jwtUser);
            }
            // 没有token
            return R.error(RType.AUTHORITY_NO_TOKEN);
        } catch (SignatureException ex) {
            // JWT签名与本地计算签名不匹配
            return R.error(RType.AUTHORITY_JWT_SIGN_ERROR);
        } catch (ExpiredJwtException ex) {
            // 登录过期
            return R.error(RType.AUTHORITY_LOGIN_EXPIRED);
        } catch (Exception e) {
            // JWT解析错误
            return R.error(RType.AUTHORITY_JWT_PARSING_ERROR);
        }
    }


    /**
     * 刷新 token, 每次的有效请求接口都将刷新 Token(同时刷新有效期 和 用户当前权限)
     * <p>
     *    用于每次请求后刷新token有效期, 用户登录授权时刷新返回token
     * <p/>
     * @param response
     * @param jwtUser
     * @return
     */
    public static void refreshToken(AdminAuthorityService adminAuthorityService, HttpServletResponse response, JwtUser jwtUser) {
        // 如果是管理端登录,当用户权限数据发生改变,刷新权限
        if (jwtUser.getType().equals(Enums.Admin.AuthorityType.AUTHORITY_TYPE_0.getValue())
                && !BaseConstant.Cache.AUTH_VERSION.equals(jwtUser.getAuthVersion())) {
            //
            List<String> authorityList = adminAuthorityService.findByUserIdaAndDisableFetchAuthority(jwtUser.getUserId());
            // 添加权限 和 权限数据版本号,当权限发生改变时，直接刷新token信息
            // 刷新权限数据
            jwtUser.setAuthList(authorityList);
            // 刷新版本号
            jwtUser.setAuthVersion(BaseConstant.Cache.AUTH_VERSION);
            log.info("用户ID:[{}] 用户名:[{}] 的权限数据已刷新", jwtUser.getUserId(), jwtUser.getFullName());
        }
        // 5、生成jwt
        String jwtToken = JwtUtil.createToken(jwtUser);
        // 放入Header
        response.setHeader(BaseConstant.Sys.TOKEN, jwtToken);
    }
}