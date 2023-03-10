package io.github.wslxm.springbootplus2.core.utils.validated;

import io.github.wslxm.springbootplus2.core.config.error.ErrorException;
import io.github.wslxm.springbootplus2.core.result.ResultType;
import org.apache.commons.lang3.StringUtils;

/**
 * 参数校检并返回错误信息给用户端
 * @author wangsong
 * @date 2021/9/30 0030 9:13
 * @return
 * @version 1.0.1
 */
public class ValidUtil {


    /**
     * 条件判断
     * 当判断条件 expression = true, 跳出程序，返回错误信息 errorMsg
     * 当判断条件=false时，不处理
     * @param expression 判断条件结果
     * @param resultType 判断条件为true时返回的错误信息
     */
    public static void isTrue(boolean expression, String errorMsg) {
        if (expression) {
            throw new ErrorException(ResultType.PARAM_ERROR.getValue(), errorMsg);
        }
    }


    /**
     * 条件判断
     * <P>
     *    当判断条件 expression = true, 跳出程序，返回错误枚举 resultType
     *    当判断条件=false时，不处理
     * </P>
     * @author wangsong
     * @param expression 判断条件结果
     * @param resultType 判断条件为true时返回的错误信息
     * @date 2022/9/7 0007 10:41
     * @return void
     * @version 1.0.0
     */
    public static void isTrue(boolean expression, ResultType resultType) {
        if (expression) {
            throw new ErrorException(resultType);
        }
    }


    /**
     * 判空(任意数据类型)
     */
    public static void isNotNull(Object obj, String errorMsg) {
        if (obj == null) {
            throw new ErrorException(ResultType.PARAM_MISSING.getValue(), errorMsg);
        }
    }

    /**
     * 判空或空字符 (string))
     */
    public static void isNotBlank(String str, String errorMsg) {
        if (StringUtils.isBlank(str)) {
            throw new ErrorException(ResultType.PARAM_MISSING.getValue(), errorMsg);
        }
    }


    /**
     * 判断字符串长度
     * @author wangsong
     * @mail 1720696548@qq.com
     * @date 2021/9/30 0030 9:36
     * @version 1.0.1
     */
    public static void isStrLen(String str, Integer min, Integer max, String errorMsg) {
        isNotBlank(str, errorMsg);
        boolean reg = (str.length() >= min && str.length() <= max);
        if (!reg) {
            throw new ErrorException(ResultType.PARAM_ERROR.getValue(), errorMsg);
        }
    }

    //============================================================
    //======================= 指定参数验证 =========================
    //============================================================

    /**
     * 校验手机号
     */
    public static void isPhone(String mobile, String errorMsg) {
        isNotBlank(mobile, errorMsg);
        if (!RegUtil.isPhone(mobile)) {
            throw new ErrorException(ResultType.PARAM_ERROR.getValue(), errorMsg);
        }
    }

    /**
     * 校验邮箱
     */
    public static void isEmail(String email, String errorMsg) {
        isNotBlank(email, errorMsg);
        if (!RegUtil.isEmail(email)) {
            throw new ErrorException(ResultType.PARAM_ERROR.getValue(), errorMsg);
        }
    }


    /**
     * 校验身份证
     */
    public static void isIdCard(String idCard, String errorMsg) {
        isNotBlank(idCard, errorMsg);
        if (!RegUtil.isIDCard(idCard)) {
            throw new ErrorException(ResultType.PARAM_ERROR.getValue(), errorMsg + ":" + RegUtil.ID_CARD_MSG);
        }
    }


    /**
     * 校验汉字
     * @author wangsong
     * @param chinese
     * @param paramName 参数名称描叙
     * @date 2021/9/30 0030 9:49
     * @return void
     * @version 1.0.1
     */
    public static void isChinese(String chinese, String paramName) {
        isNotBlank(chinese, paramName);
        if (!RegUtil.isChinese(chinese)) {
            throw new ErrorException(ResultType.PARAM_ERROR.getValue(), paramName + RegUtil.CHINESE_MSG);
        }
    }
}
