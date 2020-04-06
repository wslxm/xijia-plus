package com.ws.ldy.base.query;

import com.google.common.base.Joiner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO  JPA 通用service/dao 的delete 方法删除条件封装 Api构造器
 *
 * @author 王松
 * @mail 1720696548@qq.com
 * @date 2020/1/13 0013 20:45
 * <p>
 * /**
 */
@Component
public class DeleteCriteria<T> {
    /**
     * 删除eq条件
     */
    private Map<String, Object> equals = new HashMap<>();
    /**
     * 删除in条件
     */
    private Map<String, Object> ins = new HashMap<>();
    //=====================================================================
    //=====================================================================
    //=========================  带判断的条件构造器  ========================
    //=====================================================================
    //=====================================================================

    //TODO   eq修改条件 - 字符串/数字/时间
    public DeleteCriteria<T> eq(Boolean b, String key, Object value) {
        if (b) {
            return eq(key, value);
        }
        return this;
    }

    //TODO   in修改条件 - 字符串/数字/时间
    public <E> DeleteCriteria<T> in(Boolean b, String key, List<E> values) {
        if (b) {
            return in(key, values);
        }
        return this;
    }
    //=====================================================================
    //=====================================================================
    //========================= 不带判断的条件构造器 ========================
    //=====================================================================
    //=====================================================================

    // TODO 修改条件 字符串/数字/时间
    public DeleteCriteria<T> eq(String key, Object value) {
        equals.put(key, value);
        return this;
    }

    //TODO   修改条件in - 字符串/数字/时间
    public <E> DeleteCriteria<T> in(String key, List<E> values) {
        ins.put(key, Joiner.on(",").join(values));
        return this;
    }
    //=====================================================================
    //=====================================================================
    //========================= 返回数据 ===================================
    //=====================================================================
    //=====================================================================

    // TODO 获取--eq修改内容
    public Map<String, Object> getEqs() {
        return this.equals;
    }

    // TODO 获取--in修改内容
    public Map<String, Object> getIns() {
        return this.ins;
    }
}
