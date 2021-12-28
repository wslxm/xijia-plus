package io.github.wslxm.springbootplus2.core.common.function;

import java.io.Serializable;


/**
 * SFunction
 *
 * @author wangsong
 * @version 1.0.0
 * @email 1720696548@qq.com
 * @date 2019-09-25 12:57
 */
@FunctionalInterface
public interface SFunction<T> extends Serializable {
    /**
     * 获取 参数
     *
     * @param source source
     * @return java.lang.Object
     * @version 1.0.0
     */
    Object get(T source);
}