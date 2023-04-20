package io.github.wslxm.springbootplus2.file.util;


import io.github.wslxm.springbootplus2.file.constant.RequestConst;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求头参数设置
 * ThreadLocal 只会存在于当前线程中，不会存在于其他线程
 * @author wangsong
 * @date 2023/04/20
 */
public class FileGlobalHeader {

    private final static ThreadLocal<String> HEADER_APPLICATION_NAME = new ThreadLocal<>();
    private final static ThreadLocal<String> HEADER_FILENAME_RULE = new ThreadLocal<>();

    public static void setApplicationName(String value) {
        HEADER_APPLICATION_NAME.set(value);
    }

    public static void setFilenameRule(String value) {
        HEADER_FILENAME_RULE.set(value);
    }

    public static String getApplicationName() {
        return HEADER_APPLICATION_NAME.get();
    }

    public static String getFilenameRule() {
        return HEADER_FILENAME_RULE.get();
    }

    public static void delApplicationName() {
        HEADER_APPLICATION_NAME.remove();
    }

    public static void delFilenameRule() {
        HEADER_FILENAME_RULE.remove();
    }

    /**
     * 设置标题
     *     // 请求头参数设置
     *         // 服务名： 文件路径增加服务名(如果没有传递.默认使用当前服务名, 如果还没有设置当前服务名, 默认使用 test)
     *         // 文件名重命名规则:
     * @param request 请求
     * @param defaultApplicationName 默认应用程序名称 (如果req 中未设置)
     * @param defaultFilenameRule 默认文件名规则  (如果req 中未设置)
     */
    public static void setHeaders(HttpServletRequest request, String defaultApplicationName, String defaultFilenameRule) {
        String applicationName = request.getHeader(RequestConst.APPLICATION_NAME);
        if (StringUtils.isNotBlank(applicationName)) {
            setApplicationName(applicationName);
        } else {
            setApplicationName(defaultApplicationName);
        }
        String filenameRule = request.getHeader(RequestConst.FILENAME_RULE);
        if (StringUtils.isNotBlank(filenameRule)) {
            setFilenameRule(filenameRule);
        } else {
            setFilenameRule(defaultFilenameRule);
        }
    }
}
