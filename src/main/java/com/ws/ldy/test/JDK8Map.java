package com.ws.ldy.test;

import java.util.HashMap;
import java.util.Map;

/**
 * jd8 Map 新功能
 */
public class JDK8Map {


    public static void main(String[] args) {


        System.out.println("=============== getOrDefault（获取默认值） ========");
        //TODO 获取key对应的value，当key不存在时，返回默认值
        Map<String, Object> map = new HashMap<>();
        Object value = map.getOrDefault("key", "Default");
        System.out.println("value: " + value);
        // 输出 value=Default


        System.out.println("=============== forEach(循环)====================");
        // TODO 循环
        // 现在forEach简单很多
        Map<String, Object> map2 = new HashMap<>();
        map2.put("a", 1);
        map2.forEach((k, v) -> {
            System.out.println(k + "=" + v);
        });
        // 输出 a=1
        map2.forEach((k, v) ->  System.out.println(k + "=" + v));

        System.out.println("=============== replaceAll（替换所有value） ====================");
        // TODO 按照指定的逻辑替换所有value
        Map<String, Object> map3 = new HashMap<>();
        map3.put("a", 1);
        map3.put("b", 2);
        map3.put("c", 3);
        map3.replaceAll((k, v) -> v = 100);
        map3.forEach((k, v) -> System.out.println(k + "=" + v));
        // 输出
        // a=100
        // b=100
        // c=100

        System.out.println("=============== putIfAbsent（当key不存在时添加） ====================");
        Map<String, Integer> map4 = new HashMap<>();
        map4.put("a", 1);
        map4.putIfAbsent("a", 10);
        map4.putIfAbsent("b", 10);
        map4.forEach((k, v) -> System.out.println(k + " -> " + v));
        // 输出
        // a -> 1
        // b -> 10

        System.out.println("=========== remove（删除,当key,value 完全相同才删除） ==================");
        Map<String, Integer> map5 = new HashMap<>();
        map5.put("A", 1);
        map5.put("B", 2);
        map5.remove("A", 1);
        map5.remove("B", 1);
        map5.forEach((k, v) -> System.out.println(k + " -> " + v));
        // 输出 B -> 2


        System.out.println("=========== replace（key存在时执行替换） ==================");
        // 当key相等时替换
        Map<String, Integer> map6 = new HashMap<>();
        map6.put("A", 1);
        map6.replace("A", 10);
        map6.forEach((k, v) -> System.out.println(k + " -> " + v));
        // 输出： A -> 10

        // 当key+ value 都相等时替换
        map6.replace("A", 10, 100);
        map6.forEach((k, v) -> System.out.println(k + " -> " + v));
        // 输出： A -> 100


        System.out.println("=========== computeIfAbsent（对不存在的key进行计算） ==================");
        Map<Character, Integer> map7 = new HashMap<>();
        map7.put('A', 65);
        map7.put('B', 66);
        map7.put('C', null);
        map7.computeIfAbsent('B', (key) -> 1000);       //对存在的key无效
        map7.computeIfAbsent('C', (key) -> (int) key);  //获取key的对应的 Ascii值
        map7.computeIfAbsent('D', (key) -> 100);        //value=100
        map7.computeIfAbsent('F', (key) -> 100 * 2);    //value=100*2
        map7.forEach((k, v) -> System.out.println(k + " -> " + v));
        // 输出
        // A -> 65
        // B -> 66
        // C -> 67
        // D -> 100
        // F -> 200

        System.out.println("=========== computeIfPresent（对存在的key进行计算） ==================");
        Map<Character, Integer> map8 = new HashMap<>();
        map8.put('A', 1);
        map8.put('B', 2);
        map8.put('C', null);
        map8.computeIfPresent('A', (key, oldValue) -> oldValue + 10); // 1+10 ：正常计算
        map8.computeIfPresent('B', (key, oldValue) -> null);          // null：将从map中移除
        map8.computeIfPresent('C', (key, oldValue) -> oldValue + 10); // null+100：value=null
        map8.forEach((k, v) -> System.out.println(k + " -> " + v));
        // 输出
        // A -> 11
        // C -> null


        System.out.println("=========== compute（指定key进行计算） ==================");
        Map<Character, Integer> map9 = new HashMap<>();
        map9.put('A', 1);
        map9.put('B', 2);
        //map9.compute('A', (key, oldValue) ->  oldValue + 100); // 1+100(注意: 当oldValue=null,会抛出 NullPointerException的空指针异常)
        map9.compute('A', (key, oldValue) -> oldValue == null ? 100 : oldValue + 100); // 1+100
        map9.compute('B', (key, oldValue) -> null);           // 为null时从map删除
        map9.compute('C', (key, oldValue) -> 100);            // 不存在key时在map中添加
        map9.forEach((k, v) -> System.out.println(k + " -> " + v));
        // 输出
        // A -> 101
        // C -> 100


        System.out.println("=========== merge（合并新值和旧值） ==================");
        Map<String, Object> map10 = new HashMap<>();
        map10.put("A", 10);
        map10.put("B", "10");
        map10.merge("A", 1, (oldValue, newValue) -> Integer.parseInt(oldValue + "") + Integer.parseInt((newValue + ""))); // 数字运算: 10+1  其他说明: map的value类型为Integer可简写为: map10.merge('A', 1, Integer::sum);
        map10.merge("B", 2, (oldValue, newValue) -> oldValue.toString() + newValue); // 字符串运算: 10+2
        map10.merge("C", 3, (oldValue, newValue) -> newValue);  // map不存在key时在map中添加
        map10.forEach((k, v) -> System.out.println(k + " -> " + v));
        // 输出结果
        // A -> 11
        // B -> 102
        // C -> 3
    }

}
