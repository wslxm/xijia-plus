package com.ws.ldy.test;import java.util.List;/**  * TODO  forEach 测试  * @author ws  * @mail  1720696548@qq.com  * @date  2020/4/19 0019 20:42  */public class ForEachTest {    public static void main(String[] args) {        start();    }     private static void start(){         List<String> list = null;         list.forEach(item -> {             System.out.println("测试参数list为空是否会抛出异常");         });    }}