package com.ws.ldy.admincore.query;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO  通用service 的findPage 方法查询条件封装 Api
 *
 * @author 王松
 * @mail 1720696548@qq.com
 * @date 2020/1/13 0013 20:45
 * <p>
 * ----- key = equal                精准搜索 字符串/数字/时间
 * ----- key = like                 模糊搜索 字符串/数字/时间
 * ----- key = between              两者之间， key ：字段名，start：开始 , ent :结束  (数字/时间)
 * ----- key = greaterThanOrEqualTo 大于或等于传入值（字符串/数字/时间）
 * ----- key = lessThanOrEqualTo    小于或等于传入值（字符串/数字/时间）
 * ----- key = greaterThan          大于传入值（字符串/数字/时间）
 * ----- key = lessThan             小于传入值（字符串/数字/时间）
 * <p>
 * ## 使用方法
 * Map<Integer, Map<String, Object>> params = new HashMap<>(2);
 * //参数
 * queryCriteriaImpl.equal(params,key,value)
 * Sort sort = new Sort(Sort.Direction.ASC, "id");
 * Page<UserAdmin> userPages = userAdminServiceImpl.fingPage(1, 100, param, sort);
 * System.out.println(userPages.getContent().toString());
 * 返回参数: return ResponseData.success(userPages.getContent(), userPages.getTotalPages());
 */
public class QueryCriteria  {

    /**
     * TODO    精准搜索 字符串/数字/时间
     *
     * @return void
     */
    public static void equal(Map<String, Map<String, Object>> params, String key, Object value) {
        if (params.containsKey("equal")) {
            params.get("equal").put(key, value);
        } else {
            params.put("equal", new HashMap<String, Object>() {{
                put(key, value);
            }});
        }
    }

    /**
     * TODO    模糊搜索 字符串/数字/时间
     *
     * @return void
     */
    public static void  like(Map<String, Map<String, Object>> params, String key, Object value) {
        if (params.containsKey("like")) {
            params.get("like").put(key, value);
        } else {
            params.put("like", new HashMap<String, Object>() {{
                put(key, value);
            }});
        }
    }


    /**
     * TODO    两者之间， key ：字段名，start：开始 , ent :结束  (数字/时间)
     *
     * @return void
     */
    public static void between(Map<String, Map<String, Object>> params, String key, String start, String ent) {
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
    }


    /**
     * TODO    大于或等于传入值（字符串/数字/时间）
     *
     * @return void
     */
    public static void greaterThanOrEqualTo(Map<String, Map<String, Object>> params, String key, Object value) {
        if (params.containsKey("greaterThanOrEqualTo")) {
            params.get("greaterThanOrEqualTo").put(key, value);
        } else {
            params.put("greaterThanOrEqualTo", new HashMap<String, Object>() {{
                put(key, value);
            }});
        }
    }

    /**
     * TODO     小于或等于传入值（字符串/数字/时间）
     *
     * @return void
     */
    public static void lessThanOrEqualTo(Map<String, Map<String, Object>> params, String key, Object value) {
        if (params.containsKey("lessThanOrEqualTo")) {
            params.get("lessThanOrEqualTo").put(key, value);
        } else {
            params.put("lessThanOrEqualTo", new HashMap<String, Object>() {{
                put(key, value);
            }});
        }
    }


    /**
     * TODO    大于传入值（字符串/数字/时间）
     *
     * @return void
     */
    public static void greaterThan(Map<String, Map<String, Object>> params, String key, Object value) {
        if (params.containsKey("greaterThan")) {
            params.get("greaterThan").put(key, value);
        } else {
            params.put("greaterThan", new HashMap<String, Object>() {{
                put(key, value);
            }});
        }
    }


    /**
     * TODO  小于传入值（字符串/数字/时间）
     *
     * @return void
     */
    public static void lessThan(Map<String, Map<String, Object>> params, String key, Object value) {
        if (params.containsKey("lessThan")) {
            params.get("lessThan").put(key, value);
        } else {
            params.put("lessThan", new HashMap<String, Object>() {{
                put(key, value);
            }});
        }
    }

}
