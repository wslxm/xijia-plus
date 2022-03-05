package io.github.wslxm.springbootplus2.manage.gc.util;

import io.github.wslxm.springbootplus2.manage.gc.config.GcConfig;

import java.util.Map;

/**
 * 代码生成 动态参数 处理相关
 * @author wangsong
 * @param null
 * @date 2022/3/5 0005 20:58
 * @return
 * @version 1.0.0
 */
public class GcReplacUtil {


    /**
     * 参数替换
     *
     * @param path
     */
    public static String replacParams(GcConfig gcConfig, String param) {
        Map<String, String> defaultTemplateParam = gcConfig.getDefaultTemplateParam();
        for (String key : defaultTemplateParam.keySet()) {
            param = param.replace(key, defaultTemplateParam.get(key));
        }
        Map<String, String> templateParam = gcConfig.getTemplateParam();
        for (String key : templateParam.keySet()) {
            param = param.replace(key, templateParam.get(key));
        }
        return param;
    }
}
