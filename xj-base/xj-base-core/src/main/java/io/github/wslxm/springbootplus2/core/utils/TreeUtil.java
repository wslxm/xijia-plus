package io.github.wslxm.springbootplus2.core.utils;

import lombok.Data;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 树结构工具类
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2022/11/16 0016 9:41
 * @version 1.0.0
 */
public class TreeUtil {


    /**
     * 获取指定父节点下的子节点(递归遍历) 权限 isChecked = true||false  Tree
     *
     * @param config           配置信息 详见：TreeConfig
     * @param ts               所有节点的列表数据
     * @param pt               父节点值
     * @param hierarchy        递归层级 1-获取下一级 2-获取下2级 ....以此类推, 传 null不限层级直到没有下级
     * @param datas            数据收集存储器（可收集指定属性key的所有下级数据）,传 null不收集
     * @return void
     * @date 2019/11/13 15:20
     */
    public static <T, P> void nextTree(TreeConfig config, List<T> ts, P pt, Integer hierarchy, List<String> datas) {
        // 递归层级控制
        if (hierarchy != null) {
            if (hierarchy == 0) {
                return;
            }
            hierarchy--;
        }
        // 默认子级pid 关联父级id
        String key = config.getKey();
        String parentKey = config.getParentKey();
        String children = config.getChildren();
        try {
            // 父级数据
            Object pKeyVal = getFieldVal(getField(pt.getClass(), key), pt);
            for (T t : ts) {
                // 当前类
                Object tKeyVal = getFieldVal(getField(t.getClass(), key), t);
                Object tParentKeyVal = getFieldVal(getField(t.getClass(), parentKey), t);
                if (tParentKeyVal.toString().equals(pKeyVal.toString())) {
                    Object pChildrenVal = getFieldVal(getField(pt.getClass(), children), pt);
                    if (pChildrenVal == null) {
                        List<T> childrens = new ArrayList<>();
                        childrens.add(t);
                        setFieldVal(getField(pt.getClass(), children), childrens, pt);
                    } else {
                        List<T> childrens = (List<T>) pChildrenVal;
                        childrens.add(t);
                    }
                    // 数据收集器收据数据
                    if (datas != null) {
                        String dataKey = config.getDataKey();
                        Object dataVal = getFieldVal(getField(t.getClass(), dataKey), t);
                        datas.add(dataVal != null ? dataVal.toString() : "");
                    }
                    // 递归
                    nextTree(config, ts, t, hierarchy, datas);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 反射获取字段（子方法）
     *
     * @return java.lang.reflect.Field
     * @author wangsong
     * @date 2022/5/10 14:40
     */
    private static Field getField(Class<?> classIs, String fieldKey) {
        Field fieId = null;
        // 先找当前类
        try {
            fieId = classIs.getDeclaredField(fieldKey);
        } catch (NoSuchFieldException e) {
            //e.printStackTrace();
        }

        // 当前类没有找父类
        if (fieId == null) {
            try {
                fieId = classIs.getSuperclass().getDeclaredField(fieldKey);
            } catch (NoSuchFieldException e) {
                // e.printStackTrace();
            }
        }
        return fieId;
    }

    /**
     * 反射获取字段值
     *
     * @return java.lang.reflect.Field
     * @author wangsong
     * @date 2022/5/10 14:40
     */
    private static <T> Object getFieldVal(Field fieId, T t) throws IllegalAccessException {
        fieId.setAccessible(true);
        Object obj = fieId.get(t);
        fieId.setAccessible(false);
        return obj;
    }

    /**
     * 反射设置字段值
     *
     * @return java.lang.reflect.Field
     * @author wangsong
     * @date 2022/5/10 14:40
     */
    private static <T> boolean setFieldVal(Field fieId, Object obj, T t) throws IllegalAccessException {
        fieId.setAccessible(true);
        fieId.set(t, obj);
        fieId.setAccessible(false);
        return true;
    }


    /**
     * 配置
     * @author wangsong
     * @mail 1720696548@qq.com
     * @date 2022/11/16 0016 10:07
     * @version 1.0.0
     */
    @Data
    public static class TreeConfig {
        /**
         * 数据key
         */
        private String key = "id";
        /**
         * 数据关联父级的key （pid 关联 父级数据的 id）
         */
        private String parentKey = "pid";
        /**
         * 生成树结构后 下级数据储存到那个字段中 （如菜单可以指定为 menus 为下级list key）
         */
        private String children = "children";
        /**
         * 收集数据 key (默认收集指定父级下的 ids （数据收集 不包括父级数据)
         */
        private String dataKey = "id";
    }
}
