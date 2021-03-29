package com.ws.ldy.common.utils;

import cn.hutool.core.codec.Base64;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * 核心参数加密工具类
 * @author wangsong
 * @date 2021/3/29 0029 9:32
 * @return
 * @version 1.0.0
 */
@Slf4j
public class Base64Util {

    private static final Integer PWD_NUM = 3;                   // 加密次数
    private static final Integer START_INDEX = 1;               // 插入随机数默认开始位置索引
    private static final Integer RANDOM_NUM = 1;                // 随机数默认数量
    private static final Integer START_INDEX_INCREMENT = 1;     // 插入随机数索引开始位置 根据加密次数递增数
    private static final Integer RANDOM_INCREMENT = 1;          // 插入随机随机数数量 根据加密次数递增数

    /**
     * 加密
     * <P>
     *    当前配置：
     *      - 加密次数=3
     *      - 插入随机数默认开始位置索引=1
     *      - 随机数默认数量=1
     *      - 插入随机数索引开始位置 根据加密次数递增数=1
     *      - 插入随机随机数数量 根据加密次数递增数=1
     *      ------------------------------------
     *    1： 配置默认随机数数量 和 随机数插入索引位置
     *    2： 对入参 根据第一步的配置加入随机数, 并对 随机数数量和插入索引位置进行递增
     *    3： 进行base64加密, 入参参数重置为base64加密后的数据
     *    4： 循环对重置后的数据进行反复加密
     *    示例值：123456  加密后为：VkZab1VtVldXbU1RTXpXTFNZcHdwT0hGRlJHSklhR051VDFCV01tUktWRmh3VWsxVk5XNVFWREE5
     * </P>
     */
    public static String encode(String s1) {
        // 随机字符串数量
        int n = RANDOM_NUM;
        // 从索引几开始插入
        int i = START_INDEX;
        //当前base64字符串
        String base64Str = null;
        for (int j = 0; j < PWD_NUM; j++) {
            String randomStr = randomStr(n);
            s1 = s1.substring(0, i) + randomStr + s1.substring(i);
            base64Str = Base64.encode(s1);
            s1 = base64Str;
            n += RANDOM_INCREMENT;
            i += START_INDEX_INCREMENT;
            System.out.println(base64Str);
        }
        return base64Str;
    }

    /**
     * 解密
     */
    public static String decrypt(String s1) {
        // 随机字符串数量
        int n = RANDOM_NUM + (RANDOM_INCREMENT * (PWD_NUM - 1));
        // 从索引几开始插入
        int i = START_INDEX + (START_INDEX_INCREMENT * (PWD_NUM - 1));
        //当前base64字符串
        String str = null;
        for (int j = 0; j < PWD_NUM; j++) {
            String decodeStr = Base64.decodeStr(s1);
            str = decodeStr.substring(0, i) + decodeStr.substring(i + n);
            s1 = str;
            n -= RANDOM_INCREMENT;
            i -= START_INDEX_INCREMENT;
        }
        return str;
    }


    /**
     * 获取指定位数的随机字符串
     * @throws Exception
     * @return
     */
    public static String randomStr(Integer len) {
        String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            int index = random.nextInt(s.length());
            sb.append(s.charAt(index));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = randomStr(6);
        log.info("随机字符:" + s);

        String pwd = encode("10000");
        String decrypt = decrypt(pwd);
        log.info("key:" + pwd);
        log.info("val:" + decrypt);
    }
}
