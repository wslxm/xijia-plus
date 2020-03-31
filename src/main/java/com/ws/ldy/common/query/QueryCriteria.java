package com.ws.ldy.common.query;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO  JPA 通用service 的findPage 方法查询条件封装 Api
 *
 * @author 王松
 * @mail 1720696548@qq.com
 * @date 2020/1/13 0013 20:45
 * <p>
 * /**
 * 支持时间段，模糊，各种条件，分页(page,size)，排序（sort）等查询，
 * <p>
 * sort 参数说明：
 * -----排序规则：ASC,DESC
 * -----单字段排序：        Sort sort = new Sort(Sort.Direction.ASC, "id");
 * -----多字段排序同规则：   Sort sort = new Sort(Sort.Direction.ASC, "id","openId");
 * -----多字段排序不同规则： Sort sort = new Sort(Sort.Direction.ASC, "id").and(new Sort(Sort.Direction.DESC, "openId"));
 * -----排序api --> orderByDesc(字段名),  orderByAsc(字段名)
 * <p>
 * param 参数外层 Map说明：
 * ----- key = equal                精准搜索 字符串/数字/时间
 * ----- key = like                 模糊搜索 字符串/数字/时间
 * ----- key = between              两者之间， key ：字段名，start：开始 , ent :结束  (数字/时间)
 * ----- key = greaterThanOrEqualTo 大于或等于传入值（字符串/数字/时间）
 * ----- key = lessThanOrEqualTo    小于或等于传入值（字符串/数字/时间）
 * ----- key = greaterThan          大于传入值（字符串/数字/时间）
 * ----- key = lessThan             小于传入值（字符串/数字/时间）
 * ----- 搜索api                     对应key的value值
 * <p>
 * param 内参数内层 Map 说明：
 * ----- key = 查询字段，对应实体类字段
 * ----- value = 查询条件，值为null，为空串（""）时排除该查询条件，查询所有或满足其他条件的
 * <p>
 * <p>
 * 两者之间 between 查询说明， 外层Map key = between
 * ----- 支持时间段查询
 * ----- 支持数字范围查询
 * ----- 内Object 内还有一次 Map。包含固定字段 start,ent，必须添加 start,ent，否则不添加该查询条件
 * ----- 当单向时间段查询时，使用当外层Map key =  4| 5| 6 |7 可满足查询
 */
@Component
public class QueryCriteria {
    /**
     * 最后查询对象集
     */
    private Map<String, Map<String, Object>> params = new HashMap<>();
    /**
     * 最后排序对象
     */
    private Sort sort = null;

    //=====================================================================
    //=====================================================================
    //============================== 排序==================================
    //=====================================================================
    //=====================================================================

    /**
     * TODO   Desc排序
     *
     * @return void
     */
    public QueryCriteria orderByDesc(String property) {
        if (sort == null) {
            sort = new Sort(Sort.Direction.DESC, property);
        } else {
            sort.and(new Sort(Sort.Direction.DESC, property));
        }
        return this;
    }


    /**
     * TODO   Asc排序
     *
     * @return void
     */
    public QueryCriteria orderByAsc(String property) {
        if (sort == null) {
            sort = new Sort(Sort.Direction.ASC, property);
        } else {
            sort.and(new Sort(Sort.Direction.ASC, property));
        }
        return this;
    }

    //=====================================================================
    //=====================================================================
    //======================== 带判断的条件构造器 ===========================
    //=====================================================================
    //=====================================================================

    //TODO   精准搜索 字符串/数字/时间
    public QueryCriteria equal(Boolean decide, String key, Object value) {
        if (decide) {
            return equal(key, value);
        }
        return this;
    }

    // TODO    模糊搜索 字符串/数字/时间
    public QueryCriteria like(Boolean decide, String key, Object value) {
        if (decide) {
            return like(key, value);
        }
        return this;
    }


    // TODO 两者之间， key ：字段名，start：开始 , ent :结束  (数字/时间)
    public QueryCriteria between(Boolean decide, String key, String start, String ent) {
        if (decide) {
            return between(key, start, ent);
        }
        return this;
    }


    // TODO    大于或等于传入值（字符串/数字/时间）
    public QueryCriteria greaterThanOrEqualTo(Boolean decide, String key, Object value) {
        if (decide) {
            return greaterThanOrEqualTo(key, value);
        }
        return this;
    }

    // TODO    小于或等于传入值（字符串/数字/时间）
    public QueryCriteria lessThanOrEqualTo(Boolean decide, String key, Object value) {
        if (decide) {
            return lessThanOrEqualTo(key, value);
        }
        return this;
    }


    // TODO 大于传入值（字符串/数字/时间）
    public QueryCriteria greaterThan(Boolean decide, String key, Object value) {
        if (decide) {
            return greaterThan(key, value);
        }
        return this;
    }


    // TODO  小于传入值（字符串/数字/时间）
    public QueryCriteria lessThan(Boolean decide, String key, Object value) {
        if (decide) {
            return lessThan(key, value);
        }
        return this;
    }
    //=====================================================================
    //=====================================================================
    //========================= 不带判断的条件构造器 ========================
    //=====================================================================
    //=====================================================================

    // TODO 精准搜索 字符串/数字/时间
    public QueryCriteria equal(String key, Object value) {
        if (params.containsKey("equal")) {
            params.get("equal").put(key, value);
        } else {
            params.put("equal", new HashMap<String, Object>() {{
                put(key, value);
            }});
        }
        return this;
    }

    // TODO    模糊搜索 字符串/数字/时间
    public QueryCriteria like(String key, Object value) {
        if (params.containsKey("like")) {
            params.get("like").put(key, value);
        } else {
            params.put("like", new HashMap<String, Object>() {{
                put(key, value);
            }});
        }
        return this;
    }


    // TODO    两者之间， key ：字段名，start：开始 , ent :结束  (数字/时间)
    public QueryCriteria between(String key, String start, String ent) {
        if (params.containsKey("between")) {
            params.get("between").put(key, new HashMap<String, Object>() {{
                put("start", start);
                put("ent", ent);
            }});
        } else {
            params.put("between", new HashMap<String, Object>() {{
                put(key, new HashMap<String, Object>() {{
                    put("start", start);
                    put("ent", ent);
                }});
            }});
        }
        return this;
    }


    // TODO 大于或等于传入值（字符串/数字/时间）
    public QueryCriteria greaterThanOrEqualTo(String key, Object value) {
        if (params.containsKey("greaterThanOrEqualTo")) {
            params.get("greaterThanOrEqualTo").put(key, value);
        } else {
            params.put("greaterThanOrEqualTo", new HashMap<String, Object>() {{
                put(key, value);
            }});
        }
        return this;
    }

    // TODO     小于或等于传入值（字符串/数字/时间）
    public QueryCriteria lessThanOrEqualTo(String key, Object value) {
        if (params.containsKey("lessThanOrEqualTo")) {
            params.get("lessThanOrEqualTo").put(key, value);
        } else {
            params.put("lessThanOrEqualTo", new HashMap<String, Object>() {{
                put(key, value);
            }});
        }
        return this;
    }


    // TODO 大于传入值（字符串/数字/时间）
    public QueryCriteria greaterThan(String key, Object value) {
        if (params.containsKey("greaterThan")) {
            params.get("greaterThan").put(key, value);
        } else {
            params.put("greaterThan", new HashMap<String, Object>() {{
                put(key, value);
            }});
        }
        return this;
    }


    // TODO  小于传入值（字符串/数字/时间）
    public QueryCriteria lessThan(String key, Object value) {
        if (params.containsKey("lessThan")) {
            params.get("lessThan").put(key, value);
        } else {
            params.put("lessThan", new HashMap<String, Object>() {{
                put(key, value);
            }});
        }
        return this;
    }


    //=====================================================================
    //=====================================================================
    //========================= 最后获得的数据 =============================
    //=====================================================================
    //=====================================================================

    // TODO 最后获得的 params数据
    public Map<String, Map<String, Object>> build() {
        return this.params;
    }

    // TODO 最后获得的排序数据
    public Sort getSort() {
        return this.sort;
    }
}
