package com.ws.ldy.core.utils.validated;

import com.ws.ldy.core.config.error.ErrorException;
import com.ws.ldy.core.result.RType;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * 参数校检并返回错误信息给用户端
 * @author wangsong
 * @param null
 * @date 2021/9/30 0030 9:13
 * @return
 * @version 1.0.0
 */
public class ValidUtil {

    /**
     * 判空(任意数据类型)
     */
    public static void isNotNull(Object obj, String paramName) {
        if (obj == null) {
            throw new ErrorException(RType.PARAM_MISSING.getValue(), RType.PARAM_MISSING.getMsg() + ":" + paramName);
        }
    }

    /**
     * 判空或空字符 (string))
     */
    public static void isNotBlank(String str, String paramName) {
        if (StringUtils.isBlank(str)) {
            throw new ErrorException(RType.PARAM_MISSING.getValue(), RType.PARAM_MISSING.getMsg() + ":" + paramName);
        }
    }


    /**
     * 判断字符串长度
     * @author wangsong
     * @mail 1720696548@qq.com
     * @date 2021/9/30 0030 9:36
     * @version 1.0.0
     */
    public static void isStrLen(String str, Integer min, Integer max, String paramName) {
        isNotBlank(str, paramName);
        boolean reg = (str.length() >= min && str.length() <= max);
        if (!reg) {
            throw new ErrorException(RType.PARAM_ERROR.getValue(), paramName + "必须在" + min + "-" + max + "位之间");
        }
    }

    //============================================================
    //======================= 指定参数验证 =========================
    //============================================================

    /**
     * 校验手机号
     */
    public static void isPhone(String mobile, String paramName) {
        isNotBlank(mobile, paramName);
        if (!RegUtil.isPhone(mobile)) {
            throw new ErrorException(RType.PARAM_ERROR.getValue(), paramName + ":" + RegUtil.PHONE_MSG);
        }
    }

    /**
     * 校验邮箱
     */
    public static void isEmail(String email, String paramName) {
        isNotBlank(email, paramName);
        if (!RegUtil.isEmail(email)) {
            throw new ErrorException(RType.PARAM_ERROR.getValue(), paramName + ":" + RegUtil.EMAIL_MSG);
        }
    }


    /**
     * 校验身份证
     */
    public static void isIDCard(String idCard, String paramName) {
        isNotBlank(idCard, paramName);
        if (!RegUtil.isIDCard(idCard)) {
            throw new ErrorException(RType.PARAM_ERROR.getValue(), paramName + ":" + RegUtil.ID_CARD_MSG);
        }
    }


    /**
     * 校验汉字
     * @author wangsong
     * @param chinese
     * @param paramName 参数名称描叙
     * @date 2021/9/30 0030 9:49
     * @return void
     * @version 1.0.0
     */
    public static void isChinese(String chinese, String paramName) {
        isNotBlank(chinese, paramName);
        if (!RegUtil.isChinese(chinese)) {
            throw new ErrorException(RType.PARAM_ERROR.getValue(), paramName + RegUtil.CHINESE_MSG);
        }
    }
}
