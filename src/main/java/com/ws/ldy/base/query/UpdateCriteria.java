package com.ws.ldy.base.query;

import com.google.common.base.Joiner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO  JPA 通用service 的update 方法修改条件封装 Api构造器
 *
 * @author 王松
 * @mail 1720696548@qq.com
 * @date 2020/1/13 0013 20:45
 * <p>
 * /**
 */
@Component
public class UpdateCriteria<T> {
    /**
     * update修改条件 eq
     */
    private Map<String, Object> eqs = new HashMap<>();
    /**
     * update修改条件 in
     */
    private Map<String, Object> ins = new HashMap<>();
    /**
     * update 修改内容
     */
    private Map<String, Object> sets = new HashMap<>();


    //=====================================================================
    //=====================================================================
    //=========================  带判断的条件构造器  ========================
    //=====================================================================
    //=====================================================================

    //TODO   修改条件eq - 字符串/数字/时间
    public UpdateCriteria<T> eq(Boolean b, String key, Object value) {
        if (b) {
            return eq(key, value);
        }
        return this;
    }

    //TODO   修改条件in - 字符串/数字/时间
    public <E> UpdateCriteria<T> in(Boolean b, String key, List<E> values) {
        if (b) {
            return in(key, values);
        }
        return this;
    }


    //TODO   修改条件 - 字符串/数字/时间
    public UpdateCriteria<T> set(Boolean b, String key, Object value) {
        if (b) {
            return set(key, value);
        }
        return this;
    }
    //=====================================================================
    //=====================================================================
    //========================= 不带判断的条件构造器 ========================
    //=====================================================================
    //=====================================================================

    // TODO 修改条件 字符串/数字/时间
    public UpdateCriteria<T> eq(String key, Object value) {
        eqs.put(key, value);
        return this;
    }

    //TODO   修改条件in - 字符串/数字/时间
    public <E> UpdateCriteria<T> in(String key, List<E> values) {
        ins.put(key, Joiner.on(",").join(values));
        return this;
    }

    // TODO 修改条件 字符串/数字/时间
    public UpdateCriteria<T> set(String key, Object value) {
        sets.put(key, value);
        return this;
    }


    //=====================================================================
    //=====================================================================
    //========================= 返回数据 ===================================
    //=====================================================================
    //=====================================================================

    // TODO 获取--修改条件
    public Map<String, Object> getSet() {
        return this.sets;
    }

    // TODO 获取--修改内容
    public Map<String, Object> getEq() {
        return this.eqs;
    }

    // TODO 获取--修改内容
    public Map<String, Object> getIn() {
        return this.ins;
    }

}
