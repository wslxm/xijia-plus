package io.github.wslxm.springbootplus2.file.constant;

/**
 * 请求前缀 常量
 *
 * @author wangsong
 * @date 2023/04/16
 */
public interface RequestConst {
    String HTTP = "http://";
    String HTTPS = "https://";

    /**
     * 请求头参数-服务名， 当前上传文件的服务/模块/项目名称, 如果存在会自动拼接服务名到存储路径最前方
     */
    String APPLICATION_NAME = "Application-Name";
    /**
     * 请求头参数-文件名称规则
     */
    String FILENAME_RULE = "Filename-Rule";

}
