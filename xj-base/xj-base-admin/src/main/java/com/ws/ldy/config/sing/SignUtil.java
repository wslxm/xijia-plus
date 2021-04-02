package com.ws.ldy.config.sing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * 参数验证签名
 */
public class SignUtil {

    private static Logger logger = LoggerFactory.getLogger(SignUtil.class);

    /** 加密密钥 */
    private final static String APP_KEY = "xijia";
    /** 加密Secret */
    public final static String SECRET_KEY = "xijia@qwer";
    /** 字符编码 */
    private final static String INPUT_CHARSET = "UTF-8";
    /** 验签 key */
    public final static String SIGN = "sign";
    /** 时间戳 key */
    public final static String TIMESTAMP = "timestamp";
    /** body参数的参数key */
    public final static String BODY = "body";
    /** 超时时间(毫秒) */
    private final static Long TIME_OUT = 3000L;


    /**
     * 请求参数Map转换验证Map
     * <P>
     *    SignUtil.toVerifyMap(request.getParameterMap(), false);
     *    参数根据字母大小写排序
     * </P>
     * @param requestParams 请求参数Map
     * @param charset       是否要转utf8编码
     * @return
     */
    public static Map<String, String> toVerifyMap(Map<String, String[]> requestParams, boolean charset) {
        Map<String, String> params = new HashMap<>();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            if (charset) {
                valueStr = getContentString(valueStr, INPUT_CHARSET);
            }

            params.put(name, valueStr);
        }
        return params;
    }

    /**
     * 除去数组中的空值和签名参数
     *
     * @param sArray  签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {
        Map<String, String> result = new HashMap<>();
        if (sArray == null || sArray.size() <= 0) {
            return result;
        }
        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || "".equals(value) || key.equalsIgnoreCase(SIGN)) {
                continue;
            }
            result.put(key, value);
        }
        return result;
    }


    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param params  需要排序并参与字符拼接的参数组
     * @param encode  是否需要UrlEncode
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params, boolean encode) {
        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (encode) {
                value = urlEncode(value, INPUT_CHARSET);
            }
            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr;
    }

    /**
     * 编码转换
     *
     * @param content
     * @param charset
     * @return
     * @throws UnsupportedEncodingException
     */
    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }

    /**
     * 编码转换
     *
     * @param content
     * @param charset
     * @return
     */
    private static String getContentString(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return new String(content.getBytes());
        }
        try {
            return new String(content.getBytes("ISO-8859-1"), charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }

    /**
     * URL转码
     *
     * @param content
     * @param charset
     * @return
     */
    private static String urlEncode(String content, String charset) {
        try {
            return URLEncoder.encode(content, charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }


    //==========================================================================
    //================================= 验证签名 =================================
    //==========================================================================

    /**
     * 根据反馈回来的信息，生成签名结果
     * @param params  通知返回来的参数数组
     * @return 验证结果
     */
    public static boolean verify(Map<String, String> params) {
        // 获取签名参数
        boolean isVerify = false;
        String sign = "";
        if (params.containsKey(SIGN)) {
            sign = params.get(SIGN);
            // 过滤空值、sign
            Map<String, String> sParaNew = paraFilter(params);
            // 获取待签名字符串
            String preSignStr = createLinkString(sParaNew,false);
            logger.info(preSignStr);
            // 获得签名验证结果
            String mysign = MD5SignUtil.MD5(APP_KEY + preSignStr + SECRET_KEY);
            if (mysign.equals(sign)) {
                isVerify = true;
            }
        }
        return isVerify;
    }

    /**
     * 验证是否超时
     * timestamp = 请求时间(秒)
     * @return 验证结果
     */
    public static boolean isTimeVerify(Long timestamp) {
        // 判断请求是否超时
        long currentTimeMillis = System.currentTimeMillis();
        if (timestamp != null && (currentTimeMillis - Long.parseLong(timestamp.toString())) < TIME_OUT) {
            return true;
        }
        return false;

    }
}
